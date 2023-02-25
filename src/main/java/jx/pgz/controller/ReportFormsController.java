package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.ReportForms;
import jx.pgz.dao.yw.service.ReportFormsService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 培训报表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/yw/report-forms")
@RequiredArgsConstructor
public class ReportFormsController {

    private final ReportFormsService reportFormsService;


    @PostMapping("page")
    public Result<Page<ReportForms>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(reportFormsService.pageQuery(pageDTO));
    }


    @GetMapping("queryById/{id}")
    public Result<Boolean> page(@PathVariable("id") Long id) {
        ReportForms byId = reportFormsService.getById(id);
        return Result.ok(reportFormsService.updateById(byId));
    }


    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(reportFormsService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }

    @PostMapping("add")
    public Result<Boolean> add(@RequestBody ReportForms obj) {
        return Result.ok(reportFormsService.save(obj));
    }


    @PostMapping("update")
    public Result<Boolean> update(@RequestBody ReportForms obj) {
        return Result.ok(reportFormsService.updateById(obj));
    }


}
