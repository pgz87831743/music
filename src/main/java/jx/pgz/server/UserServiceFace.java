package jx.pgz.server;

import jx.pgz.dao.entity.SysAuthority;
import jx.pgz.dao.entity.SysRole;
import jx.pgz.dao.entity.SysUser;
import jx.pgz.model.dto.AssignAuthorityDTO;
import jx.pgz.model.dto.AssignRolesDTO;

import java.util.List;

public interface UserServiceFace {


     SysUser getUserByUsername(String username);

     List<SysRole> getRoleByUsername(String username);

     List<SysRole> roleList(String username);

     List<SysAuthority> getAuthorityByUsername(String username);



     List<SysAuthority> authorityTree();
     Object authorityTreeByRoleId(Long roleId);


     boolean assignRoles(AssignRolesDTO assignRolesDTO);

     boolean assignAuthority(AssignAuthorityDTO authorityDTO);

     Long nextId(String tableName);
}
