package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.entity.CarPart;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.service.CarPartService;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 汽车零件关联表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/carPart")
@RequiredArgsConstructor
public class CarPartController {

    private final CarPartService iCarPartService;


    @PostMapping("page")
    public Result<Page<CarPart>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(iCarPartService.page(pageDTO));
    }

    @GetMapping("listAll")
    public Result<List<CarPart>> listAll() {
        return Result.ok(iCarPartService.listAll());
    }

    @GetMapping("getById/{id}")
    public Result<CarPart> getCarPartById(@PathVariable("id") Long id) {
        return Result.ok(iCarPartService.getCarPartById(id));
    }

    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteCarPartById(@PathVariable("id") Long id) {
        return Result.ok(iCarPartService.deleteCarPartById(id));
    }

    @PostMapping("add")
    public Result<Boolean> addCarPart(@RequestBody CarPart obj) {
        return Result.ok(iCarPartService.addCarPart(obj));
    }


    @PutMapping("updateById")
    public Result<Boolean> updateCarPartById(@RequestBody CarPart obj) {
        return Result.ok(iCarPartService.updateCarPartById(obj));
    }

}
