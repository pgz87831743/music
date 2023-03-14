package jx.pgz.dao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.dao.entity.SysUser;
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

    SysUser getSysUserById(String id);

    boolean deleteSysUserById(String id);

    boolean addSysUser(SysUser obj);

    boolean updateSysUserById(SysUser obj);


    Object userRegister(SysUser obj);
}
