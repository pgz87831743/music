package jx.pgz.dao.yw.service;

import io.swagger.models.auth.In;
import jx.pgz.dao.yw.entity.Running;
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
public interface RunningService extends IService<Running> {

    public boolean updateCsv( MultipartFile[] files);
}
