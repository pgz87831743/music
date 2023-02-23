package jx.pgz.dao.yw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.dao.yw.entity.ConcomitantMotion;
import jx.pgz.dao.yw.mapper.ConcomitantMotionMapper;
import jx.pgz.dao.yw.service.ConcomitantMotionService;
import jx.pgz.enums.RoleTypeEnum;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Page<ConcomitantMotion> pageQuery(PageDTO pageDTO) {
        String role = UserContext.getInstance().getUserRole();
        Set<Long> userIds = new HashSet<>();
        Page<ConcomitantMotion> page;
        if (role.equals(RoleTypeEnum.ADMIN.name())) {
            page = lambdaQuery().
                    like(StringUtils.hasText(pageDTO.getSearch()), ConcomitantMotion::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        } else {
            page = lambdaQuery()
                    .eq(ConcomitantMotion::getCreateBy, UserContext.getInstance().getUserId()).
                    like(StringUtils.hasText(pageDTO.getSearch()), ConcomitantMotion::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        }
        if (page.getTotal() > 0) {
            for (ConcomitantMotion record : page.getRecords()) {
                userIds.add(record.getCreateBy());

            }
            Map<Long, SysUser> userMap = sysUserService.lambdaQuery().in(SysUser::getId, userIds).list().stream().collect(Collectors.toMap(SysUser::getId, v -> v));
            for (ConcomitantMotion record : page.getRecords()) {
                record.setCreateByUser(userMap.get(record.getCreateBy()));
            }
        }
        return page;
    }

    @Override
    public Boolean enroll(Long id) {
        ConcomitantMotion byId = getById(id);
        List<String> concomitantPerson = byId.getConcomitantPerson();
        if (concomitantPerson == null) {
            concomitantPerson = new ArrayList<>();
        }
        concomitantPerson.add(UserContext.getInstance().getUsername());
        Set<String> set = new HashSet<>(concomitantPerson);
        concomitantPerson.clear();
        concomitantPerson.addAll(set);
        byId.setConcomitantPerson(concomitantPerson);
        return updateById(byId);
    }
}
