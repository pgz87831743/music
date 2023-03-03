package jx.pgz.service.impl;

import jx.pgz.entity.Part;
import jx.pgz.mapper.PartMapper;
import jx.pgz.service.PartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import java.util.List;
/**
 * <p>
 * 零件表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class PartServiceImpl extends ServiceImpl<PartMapper, Part> implements PartService {
        @Override
        public Page<Part> page(PageDTO pageDTO) {
            return lambdaQuery().page(pageDTO.getMybatisPage());
        }
        @Override
        public List<Part> listAll() {
            return list();
        }
        @Override
        public Part getPartById(Long id) {
            return getById(id);
        }
        @Override
        public boolean deletePartById(Long id) {
            return removeById(id);
        }
        @Override
        public boolean addPart(Part obj) {
            return save(obj);
        }
        @Override
        public boolean updatePartById(Part obj) {
            return updateById(obj);
        }
}
