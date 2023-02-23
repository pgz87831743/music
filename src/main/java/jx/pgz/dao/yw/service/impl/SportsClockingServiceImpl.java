package jx.pgz.dao.yw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.dao.yw.entity.SportsClocking;
import jx.pgz.dao.yw.mapper.SportsClockingMapper;
import jx.pgz.dao.yw.service.SportsClockingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 运动打卡 服务实现类
 * </p>
 */
@Service
@RequiredArgsConstructor
public class SportsClockingServiceImpl extends ServiceImpl<SportsClockingMapper, SportsClocking> implements SportsClockingService {

    private final SysUserService sysUserService;


    @Override
    public List<SportsClocking> listQuery() {
        Set<Long> userIds = new HashSet<>();
        List<SportsClocking> list = lambdaQuery().list();
        if (list.size() > 0) {
            for (SportsClocking record : list) {
                userIds.add(record.getCreateBy());
            }
            Map<Long, SysUser> userMap = sysUserService.lambdaQuery().in(SysUser::getId, userIds).list().stream().collect(Collectors.toMap(SysUser::getId, v -> v));
            for (SportsClocking record : list) {
                record.setCreateByUser(userMap.get(record.getCreateBy()));
            }
        }
        return list;
    }
}
