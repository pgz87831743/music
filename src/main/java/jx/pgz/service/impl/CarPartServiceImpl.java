package jx.pgz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.entity.CarPart;
import jx.pgz.mapper.CarPartMapper;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.service.CarPartService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 汽车零件关联表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class CarPartServiceImpl extends ServiceImpl<CarPartMapper, CarPart> implements CarPartService {
    @Override
    public Page<CarPart> page(PageDTO pageDTO) {
        return lambdaQuery().page(pageDTO.getMybatisPage());
    }

    @Override
    public List<CarPart> listAll() {
        return list();
    }

    @Override
    public CarPart getCarPartById(Long id) {
        return getById(id);
    }

    @Override
    public boolean deleteCarPartById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean addCarPart(CarPart obj) {
        return save(obj);
    }

    @Override
    public boolean updateCarPartById(CarPart obj) {
        return updateById(obj);
    }
}
