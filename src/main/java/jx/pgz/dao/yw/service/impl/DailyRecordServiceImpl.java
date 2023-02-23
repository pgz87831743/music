package jx.pgz.dao.yw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.yw.entity.DailyRecord;
import jx.pgz.dao.yw.entity.HealthMonitoring;
import jx.pgz.dao.yw.entity.PetFile;
import jx.pgz.dao.yw.mapper.DailyRecordMapper;
import jx.pgz.dao.yw.service.DailyRecordService;
import jx.pgz.dao.yw.service.HealthMonitoringService;
import jx.pgz.dao.yw.service.PetFileService;
import jx.pgz.execptions.MyRuntimeException;
import jx.pgz.model.dto.PageMrjlDTO;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2023-02-20
 */
@Service
@RequiredArgsConstructor
public class DailyRecordServiceImpl extends ServiceImpl<DailyRecordMapper, DailyRecord> implements DailyRecordService {

    private final PetFileService petFileService;
    private final HealthMonitoringService healthMonitoringService;

    @Override
    public Page<DailyRecord> page(PageMrjlDTO pageDTO) {

        Page<DailyRecord> page = lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getName()), DailyRecord::getPetName, pageDTO.getName())
                .eq(StringUtils.hasText(pageDTO.getDataStr()), DailyRecord::getDataStr,pageDTO.getDataStr())
                .eq(DailyRecord::getCreateBy, UserContext.getInstance().getUserId())
                .page(pageDTO.getMybatisPage());
        return page;
    }

    @Override
    @Transactional
    public boolean saveDailyRecord(DailyRecord dailyRecord) {
        String dataStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DailyRecord one = lambdaQuery()
                .eq(DailyRecord::getPetId, dailyRecord.getPetId())
                .eq(DailyRecord::getDataStr, dataStr).one();
        if (one!=null){
            throw new MyRuntimeException(one.getPetName()+"一天只能记录一次");
        }


        //饮水量
        Double ysl = dailyRecord.getYsl();
        //体重
        Double tz = dailyRecord.getTz();
        //喂食量
        Double wsl = dailyRecord.getWsl();
        if (ysl == null || tz == null || wsl == null) {
            throw new MyRuntimeException("数据不完整");
        }


        //推荐饮水数值计算
        double tjys = tz * 60;

        //推荐喂食数值计算
        double tjws = 70 * Math.pow(tz, 0.75);
        Long petId = dailyRecord.getPetId();
        PetFile petFile = petFileService.getById(petId);
        //宠物年龄
        Float nl = petFile.getNl();
        if (nl > 10) {
            tjws = tjws * 1.4;
        } else {
            tjws = tjws * 2.5;
        }

        List<HealthMonitoring> list = new ArrayList<>();

        //we
        if (wsl < (tjws*0.2)) {
            obj(list,petFile,"喂食量过少请增加喂食量至（标准值）"+String.format("%.2f", tjws)+"kcal左右");
        } else if (wsl > (tjws*1.2)) {
            obj(list,petFile,"喂食量过多请减少喂食量至（标准值）"+String.format("%.2f", tjws)+"kcal左右");
        }


        if (ysl < (tjys*0.5)) {
            obj(list,petFile,"饮水量过少，可能是疾病信号，推荐饮水量为（标准值）"+String.format("%.2f", tjys)+"ml");
        } else if (ysl > (tjys*1.5)) {
            obj(list,petFile,"饮水量过多，可能是疾病信号，推荐饮水量为（标准值）"+String.format("%.2f", tjys)+"ml");
        }




        List<DailyRecord> oldList = lambdaQuery().eq(DailyRecord::getPetId, petId)
                .orderByDesc(true, DailyRecord::getCreateTime)
                .last("limit 0,2").list();

        oldList.add(0,dailyRecord);
        boolean qcBoolean=true;
        int pxNum=0;
        int hdNum=0;
        for (DailyRecord record : oldList) {
            if ("小".equals(record.getHdl())){
                hdNum+=1;
            }
            if ("否".equals(record.getPb())){
                pxNum+=1;
            }

        }
        if (pxNum>=3){
            obj(list,petFile,"可能有泌尿或肠冒疾病，推荐内容为：请带您的宠物进行就医");
        }
        if (hdNum>=3){
            obj(list,petFile,"宠物活跃度过低，系统推荐里生成一条推荐(请陪伴并促进您的宠物多加活动)");
        }
        if ("是".equals(oldList.get(0).getQc())){
            if(oldList.size()>=2){
                long l = Duration.between(LocalDateTime.now(), oldList.get(1).getCreateTime()).toDays();
                if (l<30){
                    obj(list,petFile,"驱虫过于频繁，推荐内容为：驱虫时间请最少间隔一个月");
                }
            }

        }



        dailyRecord.setPetName(petFile.getXm());
        dailyRecord.setDataStr(dataStr);
        boolean b = saveOrUpdate(dailyRecord);



        if (list.size() > 0) {
            return healthMonitoringService.saveBatch(list);
        }

        return false;
    }

    public HealthMonitoring obj(List<HealthMonitoring> list,PetFile petFile,String recommend){
        HealthMonitoring healthMonitoring = new HealthMonitoring();
        healthMonitoring.setPetId(petFile.getId());
        healthMonitoring.setPetName(petFile.getXm());
        healthMonitoring.setDataStr(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        healthMonitoring.setRecommend(recommend);
        list.add(healthMonitoring);
        return healthMonitoring;
    }




}
