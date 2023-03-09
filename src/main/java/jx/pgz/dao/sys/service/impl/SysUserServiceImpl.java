package jx.pgz.dao.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysRole;
import jx.pgz.dao.sys.entity.SysRoleAuthority;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.entity.SysUserRole;
import jx.pgz.dao.sys.mapper.SysUserMapper;
import jx.pgz.dao.sys.service.SysUserRoleService;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.model.dto.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author
 * @since 2023-02-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {




    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public Page<SysUser> page(PageDTO pageDTO) {
        return lambdaQuery().page(pageDTO.getMybatisPage());
    }

    @Override
    public List<SysUser> listAll() {
        return list();
    }

    @Override
    public SysUser getSysUserById(String id) {
        List<String> sysRole = sysUserRoleService.lambdaQuery()
                .eq(SysUserRole::getUserId, id)
                .list().stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        SysUser sysUser = getById(id);
        sysUser.setRoles(sysRole);
        return sysUser;
    }

    @Override
    public boolean deleteSysUserById(String id) {
        return removeById(id);
    }

    @Override
    @Transactional
    public boolean addSysUser(SysUser obj) {
        save(obj);
        if (obj.getRoles() != null && !obj.getRoles().isEmpty()) {
            List<SysUserRole> userRoles = obj.getRoles().stream().map(s -> new SysUserRole().setUserId(obj.getId()).setRoleId(s)).collect(Collectors.toList());
            return sysUserRoleService.saveBatch(userRoles);
        }
        return false;
    }

    @Override
    public boolean updateSysUserById(SysUser obj) {
        updateById(obj);
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId, obj.getId()).remove();
        if (obj.getRoles() != null && !obj.getRoles().isEmpty()) {
            List<SysUserRole> userRoles = obj.getRoles().stream().map(s -> new SysUserRole().setUserId(obj.getId()).setRoleId(s)).collect(Collectors.toList());
            return sysUserRoleService.saveBatch(userRoles);
        }
        return false;
    }


}
