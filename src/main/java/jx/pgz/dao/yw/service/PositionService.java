package jx.pgz.dao.yw.service;

import jx.pgz.dao.yw.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-02-22
 */
public interface PositionService extends IService<Position> {
    public boolean updateCsv( MultipartFile[] files);
}
