package jx.pgz.dao.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysRole;
import jx.pgz.dao.sys.entity.SysRoleAuthority;
import jx.pgz.dao.sys.mapper.SysRoleMapper;
import jx.pgz.dao.sys.service.SysRoleAuthorityService;
import jx.pgz.dao.sys.service.SysRoleService;
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

    @Resource
    private SysRoleService sysRoleService;


    @Override
    public Page<SysRole> page(PageDTO pageDTO) {
        return sysRoleService.lambdaQuery().page(pageDTO.getMybatisPage());
    }

    @Override
    public List<SysRole> listAll() {
        return sysRoleService.list();
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
        return sysRoleService.removeById(id);
    }

    @Override
    @Transactional
    public boolean addSysRole(SysRole obj) {
        sysRoleService.save(obj);
        if (obj.getAuthorities() != null && !obj.getAuthorities().isEmpty()) {
            List<SysRoleAuthority> sysUserRoles = obj.getAuthorities().stream().map(s -> {
                SysRoleAuthority sysRoleAuthority = new SysRoleAuthority();
                sysRoleAuthority.setRoleId(obj.getId());
                sysRoleAuthority.setAuthorityId(s);
                return sysRoleAuthority;
            }).collect(Collectors.toList());
            return sysRoleAuthorityService.saveBatch(sysUserRoles);
        }
        return sysRoleService.save(obj);
    }

    @Override
    public boolean updateSysRoleById(SysRole obj) {
        sysRoleService.updateById(obj);
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
        return sysRoleService.updateById(obj);
    }
}
