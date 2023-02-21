package jx.pgz.server;

import jx.pgz.dao.sys.entity.SysUser;

public interface SysUserServiceFace {

    SysUser login(String username,String password);

    SysUser register(String username,String password);

    SysUser refreshToken(String token);

    SysUser getCurrentUser(Long userId);

    boolean updateUser(SysUser sysUser);
}
