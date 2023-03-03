package jx.pgz.service;

import jx.pgz.entity.Part;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 零件表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface PartService extends IService<Part> {
        Page<Part> page(PageDTO pageDTO);
        List<Part> listAll();
        Part getPartById(Long id);
        boolean deletePartById(Long id);
        boolean addPart(Part obj);
        boolean updatePartById(Part obj);
}
