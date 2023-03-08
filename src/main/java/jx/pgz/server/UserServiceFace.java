package jx.pgz.server;

import jx.pgz.dao.sys.entity.SysUser;

public interface UserServiceFace {


    SysUser getUserByUsername(String username);


}
