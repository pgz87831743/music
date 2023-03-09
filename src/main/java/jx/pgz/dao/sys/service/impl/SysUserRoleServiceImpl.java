package jx.pgz.dao.sys.service.impl;

import jx.pgz.dao.sys.entity.SysUserRole;
import jx.pgz.dao.sys.mapper.SysUserRoleMapper;
import jx.pgz.dao.sys.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import java.util.List;
/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
        @Override
        public Page<SysUserRole> page(PageDTO pageDTO) {
            return lambdaQuery().page(pageDTO.getMybatisPage());
        }
        @Override
        public List<SysUserRole> listAll() {
            return list();
        }
        @Override
        public SysUserRole getSysUserRoleById(String id) {
            return getById(id);
        }
        @Override
        public boolean deleteSysUserRoleById(String id) {
            return removeById(id);
        }
        @Override
        public boolean addSysUserRole(SysUserRole obj) {
            return save(obj);
        }
        @Override
        public boolean updateSysUserRoleById(SysUserRole obj) {
            return updateById(obj);
        }
}
