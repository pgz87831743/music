package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.NewsManagement;
import jx.pgz.dao.yw.service.NewsManagementService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 新闻管理 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/yw/news-management")
@RequiredArgsConstructor
public class NewsManagementController {


    private final NewsManagementService newsManagementService;


    @PostMapping("page")
    public Result<Page<NewsManagement>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(newsManagementService.pageQuery(pageDTO));
    }

    @GetMapping("list")
    public Result<List<NewsManagement>> list() {
        return Result.ok(newsManagementService.listQuery());
    }


    @PostMapping("queryById/{id}")
    public Result<NewsManagement> page(@PathVariable("id") Long id) {
        return Result.ok(newsManagementService.getById(id));
    }


    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(newsManagementService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }

    @PostMapping("add")
    public Result<Boolean> add(@RequestBody NewsManagement obj) {
        return Result.ok(newsManagementService.saveData(obj));
    }


    @PostMapping("update")
    public Result<Boolean> update(@RequestBody NewsManagement obj) {
        return Result.ok(newsManagementService.updateById(obj));
    }

}
