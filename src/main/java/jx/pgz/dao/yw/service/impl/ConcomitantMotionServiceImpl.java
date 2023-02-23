package jx.pgz.dao.yw.service.impl;

import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.dao.yw.entity.ConcomitantMotion;
import jx.pgz.dao.yw.entity.NewsManagement;
import jx.pgz.dao.yw.mapper.ConcomitantMotionMapper;
import jx.pgz.dao.yw.service.ConcomitantMotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 相约运动 服务实现类
 * </p>

 */
@Service
@RequiredArgsConstructor
public class ConcomitantMotionServiceImpl extends ServiceImpl<ConcomitantMotionMapper, ConcomitantMotion> implements ConcomitantMotionService {

    private final SysUserService sysUserService;

    @Override
    public List<ConcomitantMotion> listQuery() {
        Set<Long> userIds = new HashSet<>();
        List<ConcomitantMotion> list = lambdaQuery().list();
        if (list.size() > 0) {
            for (ConcomitantMotion record : list) {
                userIds.add(record.getCreateBy());
            }
            Map<Long, SysUser> userMap = sysUserService.lambdaQuery().in(SysUser::getId, userIds).list().stream().collect(Collectors.toMap(SysUser::getId, v -> v));
            for (ConcomitantMotion record : list) {
                record.setCreateByUser(userMap.get(record.getCreateBy()));
            }
        }
        return list;
    }

    @Override
    public Boolean enroll(Long id) {
        ConcomitantMotion byId = getById(id);
        List<String> concomitantPerson = byId.getConcomitantPerson();
        if (concomitantPerson==null){
            concomitantPerson=new ArrayList<>();
        }
        concomitantPerson.add(UserContext.getInstance().getUsername());
        Set<String> set=new HashSet<>(concomitantPerson);
        concomitantPerson.clear();
        concomitantPerson.addAll(set);
        byId.setConcomitantPerson(concomitantPerson);
        return updateById(byId);
    }
}
