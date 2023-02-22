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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            tjws = tjys * 1.4;
        } else {
            tjws = tjys * 2.5;
        }

        String dataStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<HealthMonitoring> list = new ArrayList<>();

        //we
        if (wsl < (tjws*0.2)) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("喂食量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setPetName(petFile.getXm());
            healthMonitoring.setDataStr(dataStr);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "喂食量较低，推荐喂食量为：" + tjws + ",当前值为" + wsl);
            list.add(healthMonitoring);
        } else if (wsl > (tjws*1.2)) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("喂食量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setPetName(petFile.getXm());
            healthMonitoring.setDataStr(dataStr);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "喂食量较高，推荐喂食量为：" + tjws + ",当前值为" + wsl);
            list.add(healthMonitoring);
        }


        if (ysl < (tjys*0.5)) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("饮水量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setPetName(petFile.getXm());
            healthMonitoring.setDataStr(dataStr);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "饮水量较低，推荐饮水量为：" + tjys + ",当前值为" + ysl);
            list.add(healthMonitoring);
        } else if (ysl > (tjys*1.5)) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("饮水量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setPetName(petFile.getXm());
            healthMonitoring.setDataStr(dataStr);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "饮水量较高，推荐饮水量为：" + tjys + ",当前值为" + ysl);
            list.add(healthMonitoring);
        }

        dailyRecord.setPetName(petFile.getXm());
        dailyRecord.setDataStr(dataStr);
        boolean b = saveOrUpdate(dailyRecord);
        if (list.size() > 0) {
            return healthMonitoringService.saveBatch(list);
        }

        return false;
    }

}
