package jx.pgz.dao.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.mapper.SysUserMapper;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.model.dto.PageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author
 * @since 2023-02-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Page<SysUser> page(PageDTO pageDTO) {
        return lambdaQuery().page(pageDTO.getMybatisPage());
    }

    @Override
    public List<SysUser> listAll() {
        return list();
    }

    @Override
    public SysUser getSysUserById(Long id) {
        return getById(id);
    }

    @Override
    public boolean deleteSysUserById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean addSysUser(SysUser obj) {
        return save(obj);
    }

    @Override
    public boolean updateSysUserById(SysUser obj) {
        return updateById(obj);
    }


}
