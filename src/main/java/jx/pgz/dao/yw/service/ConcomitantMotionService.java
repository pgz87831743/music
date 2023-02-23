package jx.pgz.dao.yw.service;

import jx.pgz.dao.yw.entity.ConcomitantMotion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 相约运动 服务类
 * </p>

 */
public interface ConcomitantMotionService extends IService<ConcomitantMotion> {

    List<ConcomitantMotion> listQuery();

    /**
     * 报名运动
     * @param id
     * @return
     */
    Boolean enroll(Long id);
}
