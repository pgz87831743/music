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
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Page<DailyRecord> page(PageDTO pageDTO) {

        Page<DailyRecord> page = lambdaQuery()
                .eq(DailyRecord::getCreateBy, UserContext.getInstance().getUserId())
                .page(pageDTO.getMybatisPage());
        if (!page.getRecords().isEmpty()) {
            List<Long> petId = page.getRecords().stream().map(DailyRecord::getPetId).collect(Collectors.toList());
            Map<Long, PetFile> collect = petFileService.lambdaQuery().in(PetFile::getId, petId).list().stream().collect(Collectors.toMap(PetFile::getId, v -> v));
            page.getRecords().forEach(s -> s.setPetFile(collect.get(s.getPetId())));
        }
        return page;
    }

    @Override
    @Transactional
    public boolean saveDailyRecord(DailyRecord dailyRecord) {
        //饮水量
        Float ysl = dailyRecord.getYsl();
        //体重
        Float tz = dailyRecord.getTz();
        //喂食量
        Float wsl = dailyRecord.getWsl();
        if (ysl == null || tz == null || wsl == null) {
            throw new MyRuntimeException("数据不完整");
        }

        boolean b = saveOrUpdate(dailyRecord);


        //推荐饮水数值计算
        float tjys = tz * 60;

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

        List<HealthMonitoring> list = new ArrayList<>();
        if (wsl < tjws) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("喂食量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "喂食量较低，推荐喂食量为：" + tjws+",当前值为"+wsl);
            list.add(healthMonitoring);
        } else if (wsl * 0.7 > tjws) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("喂食量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "喂食量较高，推荐喂食量为：" + tjws+",当前值为"+wsl);
            list.add(healthMonitoring);
        }


        if (ysl < tjys) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("饮水量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "饮水量较低，推荐饮水量为：" + tjys+",当前值为"+ysl);
            list.add(healthMonitoring);
        } else if (ysl * 0.7 > tjys) {
            HealthMonitoring healthMonitoring = new HealthMonitoring();
            healthMonitoring.setType("饮水量");
            healthMonitoring.setPetId(petId);
            healthMonitoring.setRecommend("您的宠物" + petFile.getXm() + "饮水量较高，推荐饮水量为：" + tjys+",当前值为"+ysl);
            list.add(healthMonitoring);
        }

        if (list.size() > 0) {
            return healthMonitoringService.saveBatch(list);
        }

        return false;
    }

}
