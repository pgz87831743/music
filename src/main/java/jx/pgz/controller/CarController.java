package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import jx.pgz.entity.Car;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.service.CarService;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 汽车表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService iCarService;




    @ApiOperation("汽车详情包含检测报告")
    @GetMapping("getById/{id}")
    public Result<Car> getCarById(@PathVariable("id") Long id) {
        return Result.ok(iCarService.getCarById(id));
    }




}
