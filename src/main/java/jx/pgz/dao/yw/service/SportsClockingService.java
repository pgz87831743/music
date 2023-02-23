package jx.pgz.dao.yw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.ConcomitantMotion;
import jx.pgz.dao.yw.entity.NewsManagement;
import jx.pgz.dao.yw.entity.SportsClocking;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 运动打卡 服务类
 * </p>

 */
public interface SportsClockingService extends IService<SportsClocking> {
    List<SportsClocking> listQuery();

    Page<SportsClocking> pageQuery(PageDTO pageDTO);
}
