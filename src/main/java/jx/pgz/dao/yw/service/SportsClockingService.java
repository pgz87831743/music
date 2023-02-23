package jx.pgz.dao.yw.service;

import jx.pgz.dao.yw.entity.NewsManagement;
import jx.pgz.dao.yw.entity.SportsClocking;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 运动打卡 服务类
 * </p>

 */
public interface SportsClockingService extends IService<SportsClocking> {
    List<SportsClocking> listQuery();
}
