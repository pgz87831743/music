package jx.pgz.dao.service.impl;

import jx.pgz.dao.entity.SysAuthority;
import jx.pgz.dao.mapper.SysAuthorityMapper;
import jx.pgz.dao.service.SysAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import java.util.List;
/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysAuthorityServiceImpl extends ServiceImpl<SysAuthorityMapper, SysAuthority> implements SysAuthorityService {
        @Override
        public Page<SysAuthority> page(PageDTO pageDTO) {
            return lambdaQuery().page(pageDTO.getMybatisPage());
        }
        @Override
        public List<SysAuthority> listAll() {
            return list();
        }
        @Override
        public SysAuthority getSysAuthorityById(String id) {
            return getById(id);
        }
        @Override
        public boolean deleteSysAuthorityById(String id) {
            return removeById(id);
        }
        @Override
        public boolean addSysAuthority(SysAuthority obj) {
            return save(obj);
        }
        @Override
        public boolean updateSysAuthorityById(SysAuthority obj) {
            return updateById(obj);
        }
}