package jx.pgz.dao.service.impl;

import jx.pgz.dao.entity.SysRoleAuthority;
import jx.pgz.dao.mapper.SysRoleAuthorityMapper;
import jx.pgz.dao.service.SysRoleAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import java.util.List;
/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysRoleAuthorityServiceImpl extends ServiceImpl<SysRoleAuthorityMapper, SysRoleAuthority> implements SysRoleAuthorityService {
        @Override
        public Page<SysRoleAuthority> page(PageDTO pageDTO) {
            return lambdaQuery().page(pageDTO.getMybatisPage());
        }
        @Override
        public List<SysRoleAuthority> listAll() {
            return list();
        }
        @Override
        public SysRoleAuthority getSysRoleAuthorityById(String id) {
            return getById(id);
        }
        @Override
        public boolean deleteSysRoleAuthorityById(String id) {
            return removeById(id);
        }
        @Override
        public boolean addSysRoleAuthority(SysRoleAuthority obj) {
            return save(obj);
        }
        @Override
        public boolean updateSysRoleAuthorityById(SysRoleAuthority obj) {
            return updateById(obj);
        }
}
