package jx.pgz.service;

import jx.pgz.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 汽车表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface CarService extends IService<Car> {

        Car getCarById(Long id);

}
