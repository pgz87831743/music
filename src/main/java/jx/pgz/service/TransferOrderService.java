package jx.pgz.service;

import jx.pgz.entity.TransferOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 调拨单 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface TransferOrderService extends IService<TransferOrder> {
        Page<TransferOrder> page(PageDTO pageDTO);
        List<TransferOrder> listAll();
        TransferOrder getTransferOrderById(Long id);
        boolean deleteTransferOrderById(Long id);
        boolean addTransferOrder(TransferOrder obj);
        boolean updateTransferOrderById(TransferOrder obj);
}
