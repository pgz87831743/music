package jx.pgz.dao.yw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.DailyRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.model.dto.PageMrjlDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-02-20
 */
public interface DailyRecordService extends IService<DailyRecord> {



    Page<DailyRecord> page(PageMrjlDTO pageDTO);


    boolean saveDailyRecord(DailyRecord dailyRecord);
}
