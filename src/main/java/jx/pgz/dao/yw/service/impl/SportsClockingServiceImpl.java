package jx.pgz.dao.yw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.dao.yw.entity.ConcomitantMotion;
import jx.pgz.dao.yw.entity.SportsClocking;
import jx.pgz.dao.yw.mapper.SportsClockingMapper;
import jx.pgz.dao.yw.service.SportsClockingService;
import jx.pgz.enums.RoleTypeEnum;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Page<SportsClocking> pageQuery(PageDTO pageDTO) {
        String role = UserContext.getInstance().getUserRole();
        Set<Long> userIds = new HashSet<>();
        Page<SportsClocking> page;
        if (role.equals(RoleTypeEnum.ADMIN.name())) {
            page = lambdaQuery().
                    like(StringUtils.hasText(pageDTO.getSearch()), SportsClocking::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        } else {
            page = lambdaQuery()
                    .eq(SportsClocking::getCreateBy, UserContext.getInstance().getUserId()).
                    like(StringUtils.hasText(pageDTO.getSearch()), SportsClocking::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        }
        if (page.getTotal() > 0) {
            for (SportsClocking record : page.getRecords()) {
                userIds.add(record.getCreateBy());
            }
            Map<Long, SysUser> userMap = sysUserService.lambdaQuery().in(SysUser::getId, userIds).list().stream().collect(Collectors.toMap(SysUser::getId, v -> v));
            for (SportsClocking record : page.getRecords()) {
                record.setCreateByUser(userMap.get(record.getCreateBy()));
            }
        }
        return page;
    }
}
