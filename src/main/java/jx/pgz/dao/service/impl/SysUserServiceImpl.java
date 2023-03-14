package jx.pgz.dao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.entity.SysUser;
import jx.pgz.dao.entity.SysUserRole;
import jx.pgz.dao.mapper.SysUserMapper;
import jx.pgz.dao.service.SysUserRoleService;
import jx.pgz.dao.service.SysUserService;
import jx.pgz.execptions.MyRuntimeException;
import jx.pgz.model.dto.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Transactional(rollbackFor = Throwable.class)
    public boolean addSysUser(SysUser obj) {
        save(obj);
        if (obj.getRoles() != null && !obj.getRoles().isEmpty()) {
            List<SysUserRole> userRoles = obj.getRoles().stream().map(s -> new SysUserRole().setUserId(obj.getId()).setRoleId(s)).collect(Collectors.toList());
            return sysUserRoleService.saveBatch(userRoles);
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean updateSysUserById(SysUser obj) {
        updateById(obj);
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId, obj.getId()).remove();
        if (obj.getRoles() != null && !obj.getRoles().isEmpty()) {
            List<SysUserRole> userRoles = obj.getRoles().stream().map(s -> new SysUserRole().setUserId(obj.getId()).setRoleId(s)).collect(Collectors.toList());
            return sysUserRoleService.saveBatch(userRoles);
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Object userRegister(SysUser obj) {
        String username = obj.getUsername();
        String phone = obj.getPhone();
        String password = obj.getPassword();
        if (!StringUtils.hasText(username) || !StringUtils.hasText(phone) || !StringUtils.hasText(password)) {
            throw new MyRuntimeException("请将数据填写完整");
        }
        SysUser one = lambdaQuery().eq(SysUser::getUsername, obj.getUsername()).one();
        if (one != null) {
            throw new MyRuntimeException("用户名已存在");
        }
        save(obj);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(obj.getId());
        sysUserRole.setRoleId("1635499321564758017");
        return sysUserRoleService.save(sysUserRole);
    }
}
