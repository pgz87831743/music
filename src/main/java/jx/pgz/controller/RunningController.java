package jx.pgz.controller;

import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jx.pgz.dao.yw.entity.Running;
import jx.pgz.dao.yw.service.RunningService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RequestMapping("/running")
@RestController
@Api(tags = "running操作接口")
public class RunningController {

    @Resource
    private RunningService runningService;


    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public Result<Boolean> list(@ApiParam("文件") MultipartFile[] files) {
        return Result.ok(runningService.updateCsv(files));
    }


    @PostMapping("add")
    @ApiOperation("新增running")
    public Result<Boolean> add(@RequestBody Running running) {
        return Result.ok(runningService.save(running));
    }


    @DeleteMapping("deleteById/{id}")
    @ApiOperation("删除running")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(runningService.removeById(id));
    }


    @GetMapping("page")
    @ApiOperation("running分页数据")
    public Result<Page<Running>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(runningService.page(pageDTO.getMybatisPage()));
    }


    @PostMapping("update")
    @ApiOperation("修改running")
    public Result<Boolean> update(@RequestBody Running running) {
        return Result.ok(runningService.updateById(running));
    }

}
