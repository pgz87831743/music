package jx.pgz.dao.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 
 * @since 2023-02-27
 */
public interface SysUserService extends IService<SysUser> {

    Page<SysUser> page(PageDTO pageDTO);

    List<SysUser> listAll();

    SysUser getSysUserById(Long id);

    boolean deleteSysUserById(Long id);

    boolean addSysUser(SysUser obj);

    boolean updateSysUserById(SysUser obj);
}
