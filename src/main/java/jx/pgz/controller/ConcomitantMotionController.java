package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import jx.pgz.dao.yw.entity.ConcomitantMotion;
import jx.pgz.dao.yw.entity.NewsManagement;
import jx.pgz.dao.yw.service.ConcomitantMotionService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 相约运动 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/yw/concomitant-motion")
@RequiredArgsConstructor
public class ConcomitantMotionController {


    private final ConcomitantMotionService concomitantMotionService;


    @PostMapping("page")
    @ApiOperation("相约运动分页")
    public Result<Page<ConcomitantMotion>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(concomitantMotionService.pageQuery(pageDTO));
    }

    @GetMapping("list")
    @ApiOperation("相约运动列表")
    public Result<List<ConcomitantMotion>> list() {
        return Result.ok(concomitantMotionService.listQuery());
    }




    @PostMapping("queryById/{id}")
    @ApiOperation("相约运动详情")
    public Result<ConcomitantMotion> page(@PathVariable("id") Long id) {
        return Result.ok(concomitantMotionService.getById(id));
    }


    @DeleteMapping("deleteById/{id}")
    @ApiOperation("相约运动删除")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(concomitantMotionService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }

    @PostMapping("add")
    @ApiOperation("相约运动新新增")
    public Result<Boolean> add(@RequestBody ConcomitantMotion obj) {
        return Result.ok(concomitantMotionService.save(obj));
    }


    @PostMapping("update")
    @ApiOperation("相约运动更新")
    public Result<Boolean> update(@RequestBody ConcomitantMotion obj) {
        return Result.ok(concomitantMotionService.updateById(obj));
    }

    @GetMapping("enroll/{id}")
    @ApiOperation("相约运动报名")
    public Result<Boolean> enroll(@PathVariable("id")Long id){
        return Result.ok(concomitantMotionService.enroll(id)).setShowMsg(true).setMsg("报名成功");
    }

}
