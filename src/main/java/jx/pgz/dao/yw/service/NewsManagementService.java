package jx.pgz.dao.yw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.dao.yw.entity.MessageManagement;
import jx.pgz.dao.yw.entity.NewsManagement;
import jx.pgz.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 新闻管理 服务类
 * </p>
 */
public interface NewsManagementService extends IService<NewsManagement> {
    boolean saveData(NewsManagement management);

    Page<NewsManagement> pageQuery(PageDTO pageDTO);

    List<NewsManagement> listQuery();
}
