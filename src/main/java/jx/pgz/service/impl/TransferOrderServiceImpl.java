package jx.pgz.service.impl;

import jx.pgz.entity.TransferOrder;
import jx.pgz.mapper.TransferOrderMapper;
import jx.pgz.service.TransferOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import java.util.List;
/**
 * <p>
 * 调拨单 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class TransferOrderServiceImpl extends ServiceImpl<TransferOrderMapper, TransferOrder> implements TransferOrderService {
        @Override
        public Page<TransferOrder> page(PageDTO pageDTO) {
            return lambdaQuery().page(pageDTO.getMybatisPage());
        }
        @Override
        public List<TransferOrder> listAll() {
            return list();
        }
        @Override
        public TransferOrder getTransferOrderById(Long id) {
            return getById(id);
        }
        @Override
        public boolean deleteTransferOrderById(Long id) {
            return removeById(id);
        }
        @Override
        public boolean addTransferOrder(TransferOrder obj) {
            return save(obj);
        }
        @Override
        public boolean updateTransferOrderById(TransferOrder obj) {
            return updateById(obj);
        }
}
