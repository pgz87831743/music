package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import jx.pgz.entity.Report;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.service.CarPartService;
import jx.pgz.service.ReportService;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 检测表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/report")

public class ReportController {


    @Resource
    private ReportService iReportService;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public Result<Page<Report>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(iReportService.page(pageDTO));
    }

    @GetMapping("listAll")
    @ApiOperation("查询所有检测结果")
    public Result<List<Report>> listAll() {
        return Result.ok(iReportService.listAll());
    }


    @PostMapping("add")
    @ApiOperation("上报检测结果")
    public Result<Boolean> addReport(@RequestBody Report obj) {
        return Result.ok(iReportService.addReport(obj));
    }



}
