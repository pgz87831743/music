package jx.pgz.service;

import jx.pgz.entity.CarPart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 汽车零件关联表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface CarPartService extends IService<CarPart> {
        Page<CarPart> page(PageDTO pageDTO);
        List<CarPart> listAll();
        CarPart getCarPartById(Long id);
        boolean deleteCarPartById(Long id);
        boolean addCarPart(CarPart obj);
        boolean updateCarPartById(CarPart obj);
}
