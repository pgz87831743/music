package jx.pgz.dao.yw.service.impl;

import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.yw.entity.Running;
import jx.pgz.dao.yw.mapper.RunningMapper;
import jx.pgz.dao.yw.service.RunningService;
import jx.pgz.execptions.MyRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2023-02-22
 */
@Service
public class RunningServiceImpl extends ServiceImpl<RunningMapper, Running> implements RunningService {

    @Override
    public boolean updateCsv(MultipartFile[] files) {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                try (InputStream inputStream = file.getInputStream()) {
                    CsvImportParams csvImportParams = new CsvImportParams();
                    List<Running> objects = CsvImportUtil.importCsv(inputStream, Running.class, csvImportParams);
                    return saveOrUpdateBatch(objects);
                } catch (IOException e) {
                    throw new MyRuntimeException("更新失败");
                }
            }
        }
        return false;
    }
}
