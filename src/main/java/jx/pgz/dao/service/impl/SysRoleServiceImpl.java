package jx.pgz.dao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.entity.SysRole;
import jx.pgz.dao.entity.SysRoleAuthority;
import jx.pgz.dao.mapper.SysRoleMapper;
import jx.pgz.dao.service.SysRoleAuthorityService;
import jx.pgz.dao.service.SysRoleService;
import jx.pgz.model.dto.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Resource
    private SysRoleAuthorityService sysRoleAuthorityService;




    @Override
    public Page<SysRole> page(PageDTO pageDTO) {
        return lambdaQuery().page(pageDTO.getMybatisPage());
    }

    @Override
    public List<SysRole> listAll() {
        return list();
    }

    @Override
    public SysRole getSysRoleById(String id) {
        List<String> authority = sysRoleAuthorityService.lambdaQuery()
                .eq(SysRoleAuthority::getRoleId, id)
                .list().stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toList());
        SysRole sysRole = getById(id);
        sysRole.setAuthorities(authority);
        return sysRole;
    }

    @Override
    public boolean deleteSysRoleById(String id) {
        return removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean addSysRole(SysRole obj) {
        boolean save = save(obj);
        if (obj.getAuthorities() != null && !obj.getAuthorities().isEmpty()) {
            List<SysRoleAuthority> sysUserRoles = obj.getAuthorities().stream().map(s -> {
                SysRoleAuthority sysRoleAuthority = new SysRoleAuthority();
                sysRoleAuthority.setRoleId(obj.getId());
                sysRoleAuthority.setAuthorityId(s);
                return sysRoleAuthority;
            }).collect(Collectors.toList());
            return sysRoleAuthorityService.saveBatch(sysUserRoles);
        }
        return save;
    }

    @Override
    public boolean updateSysRoleById(SysRole obj) {
        updateById(obj);
        sysRoleAuthorityService.lambdaUpdate()
                .eq(SysRoleAuthority::getRoleId, obj.getId())
                .remove();
        if (obj.getAuthorities() != null && !obj.getAuthorities().isEmpty()) {
            List<SysRoleAuthority> sysUserRoles = obj.getAuthorities().stream().map(s -> {
                SysRoleAuthority sysRoleAuthority = new SysRoleAuthority();
                sysRoleAuthority.setRoleId(obj.getId());
                sysRoleAuthority.setAuthorityId(s);
                return sysRoleAuthority;
            }).collect(Collectors.toList());
            return sysRoleAuthorityService.saveBatch(sysUserRoles);
        }
        return updateById(obj);
    }
}
