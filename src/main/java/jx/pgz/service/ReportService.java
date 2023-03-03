package jx.pgz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.entity.Report;
import jx.pgz.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 检测表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface ReportService extends IService<Report> {
    Page<Report> page(PageDTO pageDTO);

    List<Report> listAll();

    boolean addReport(Report obj);

}
