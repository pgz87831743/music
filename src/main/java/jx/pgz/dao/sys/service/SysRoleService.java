package jx.pgz.dao.sys.service;

import jx.pgz.dao.sys.entity.SysRole;
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
        SysRole getSysRoleById(Long id);
        boolean deleteSysRoleById(Long id);
        boolean addSysRole(SysRole obj);
        boolean updateSysRoleById(SysRole obj);
}
