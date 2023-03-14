package jx.pgz.dao.service;

import jx.pgz.dao.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysUserRoleService extends IService<SysUserRole> {
        Page<SysUserRole> page(PageDTO pageDTO);
        List<SysUserRole> listAll();
        SysUserRole getSysUserRoleById(String id);
        boolean deleteSysUserRoleById(String id);
        boolean addSysUserRole(SysUserRole obj);
        boolean updateSysUserRoleById(SysUserRole obj);
}
