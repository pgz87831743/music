package jx.pgz.server;

import jx.pgz.dao.sys.entity.SysAuthority;
import jx.pgz.dao.sys.entity.SysFile;
import jx.pgz.dao.sys.entity.SysRole;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.enums.FileTypeEnum;
import jx.pgz.model.dto.AssignAuthorityDTO;
import jx.pgz.model.dto.AssignRolesDTO;
import org.springframework.web.multipart.MultipartFile;

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
