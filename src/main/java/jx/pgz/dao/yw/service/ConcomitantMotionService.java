package jx.pgz.dao.yw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.ConcomitantMotion;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.dao.yw.entity.MessageManagement;
import jx.pgz.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 相约运动 服务类
 * </p>

 */
public interface ConcomitantMotionService extends IService<ConcomitantMotion> {

    List<ConcomitantMotion> listQuery();


    Page<ConcomitantMotion> pageQuery(PageDTO pageDTO);

    /**
     * 报名运动
     * @param id
     * @return
     */
    Boolean enroll(Long id);
}
