package jx.pgz.dao.service;

import jx.pgz.dao.entity.SysRoleAuthority;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysRoleAuthorityService extends IService<SysRoleAuthority> {
        Page<SysRoleAuthority> page(PageDTO pageDTO);
        List<SysRoleAuthority> listAll();
        SysRoleAuthority getSysRoleAuthorityById(String id);
        boolean deleteSysRoleAuthorityById(String id);
        boolean addSysRoleAuthority(SysRoleAuthority obj);
        boolean updateSysRoleAuthorityById(SysRoleAuthority obj);
}
