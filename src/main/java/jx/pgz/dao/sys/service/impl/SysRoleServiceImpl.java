package jx.pgz.dao.sys.service.impl;

import jx.pgz.dao.sys.entity.SysRole;
import jx.pgz.dao.sys.mapper.SysRoleMapper;
import jx.pgz.dao.sys.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import java.util.List;
/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
        @Override
        public Page<SysRole> page(PageDTO pageDTO) {
            return lambdaQuery().page(pageDTO.getMybatisPage());
        }
        @Override
        public List<SysRole> listAll() {
            return list();
        }
        @Override
        public SysRole getSysRoleById(Long id) {
            return getById(id);
        }
        @Override
        public boolean deleteSysRoleById(Long id) {
            return removeById(id);
        }
        @Override
        public boolean addSysRole(SysRole obj) {
            return save(obj);
        }
        @Override
        public boolean updateSysRoleById(SysRole obj) {
            return updateById(obj);
        }
}
