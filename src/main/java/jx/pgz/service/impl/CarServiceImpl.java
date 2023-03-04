package jx.pgz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.entity.Car;
import jx.pgz.entity.Report;
import jx.pgz.mapper.CarMapper;
import jx.pgz.service.CarService;
import jx.pgz.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 汽车表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service

public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Resource
    private  ReportService reportService;


    @Override
    public Car getCarById(Long id) {
        List<Report> reportList = reportService.query()
                .eq("car_id", id)
                .list();
        Car car = getById(id);
        car.setReportList(reportList);
        return car;
    }


}
