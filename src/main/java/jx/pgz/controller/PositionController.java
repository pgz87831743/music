package jx.pgz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jx.pgz.dao.yw.entity.Position;
import jx.pgz.dao.yw.service.PositionService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@RequestMapping("/position")
@RestController
@Api(tags = "Position操作接口")
public class PositionController {

    @Resource
    private PositionService positionService;


    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public Result<Boolean> list(@ApiParam("文件") MultipartFile[] files) {
        return Result.ok(positionService.updateCsv(files));
    }



    @PostMapping("add")
    @ApiOperation("新增position")
    public Result<Boolean> add(@RequestBody Position position) {
        return Result.ok(positionService.save(position));
    }


    @DeleteMapping("deleteById/{id}")
    @ApiOperation("删除position")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(positionService.removeById(id));
    }


    @GetMapping("page")
    @ApiOperation("position分页数据")
    public Result<Page<Position>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(positionService.page(pageDTO.getMybatisPage()));
    }


    @PostMapping("update")
    @ApiOperation("修改position")
    public Result<Boolean> update(@RequestBody Position position) {
        return Result.ok(positionService.updateById(position));
    }

}
