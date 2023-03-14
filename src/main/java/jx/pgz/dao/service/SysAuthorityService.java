package jx.pgz.dao.service;

import jx.pgz.dao.entity.SysAuthority;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysAuthorityService extends IService<SysAuthority> {
        Page<SysAuthority> page(PageDTO pageDTO);
        List<SysAuthority> listAll();
        SysAuthority getSysAuthorityById(String id);
        boolean deleteSysAuthorityById(String id);
        boolean addSysAuthority(SysAuthority obj);
        boolean updateSysAuthorityById(SysAuthority obj);
}
