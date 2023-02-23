package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import jx.pgz.dao.yw.entity.SportsClocking;
import jx.pgz.dao.yw.service.SportsClockingService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 运动打卡 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/yw/sports-clocking")
@RequiredArgsConstructor
@Api(tags = "运动打卡")
public class SportsClockingController {

    private final SportsClockingService sportsClockingService;


    @PostMapping("page")
    public Result<Page<SportsClocking>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(sportsClockingService.page(pageDTO.getMybatisPage()));
    }


    @GetMapping("list")
    public Result<List<SportsClocking>> list() {
        return Result.ok(sportsClockingService.listQuery());
    }


    @PostMapping("queryById/{id}")
    public Result<SportsClocking> page(@PathVariable("id") Long id) {
        return Result.ok(sportsClockingService.getById(id));
    }


    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(sportsClockingService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }

    @PostMapping("add")
    public Result<Boolean> add(@RequestBody SportsClocking obj) {
        return Result.ok(sportsClockingService.save(obj));
    }


    @PostMapping("update")
    public Result<Boolean> update(@RequestBody SportsClocking obj) {
        return Result.ok(sportsClockingService.updateById(obj));
    }

}
