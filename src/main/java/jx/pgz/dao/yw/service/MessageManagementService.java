package jx.pgz.dao.yw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.MessageManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.model.dto.PageDTO;

/**
 * <p>
 * 留言管理 服务类
 * </p>

 */
public interface MessageManagementService extends IService<MessageManagement> {

    Page<MessageManagement> pageQuery(PageDTO pageDTO);

}
