package jx.pgz.dao.service;

import jx.pgz.dao.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysRoleService extends IService<SysRole> {
        Page<SysRole> page(PageDTO pageDTO);
        List<SysRole> listAll();
        SysRole getSysRoleById(String id);
        boolean deleteSysRoleById(String id);
        boolean addSysRole(SysRole obj);
        boolean updateSysRoleById(SysRole obj);
}
