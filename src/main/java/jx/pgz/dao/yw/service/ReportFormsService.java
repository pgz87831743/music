package jx.pgz.dao.yw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.ReportForms;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.model.dto.PageDTO;

/**
 * <p>
 * 培训报表 服务类
 * </p>

 */
public interface ReportFormsService extends IService<ReportForms> {

    Page<ReportForms> pageQuery(PageDTO pageDTO);
}
