package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("新闻分页")
    public Result<Page<NewsManagement>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(newsManagementService.pageQuery(pageDTO));
    }

    @GetMapping("list")
    @ApiOperation("新闻列表")
    public Result<List<NewsManagement>> list() {
        return Result.ok(newsManagementService.listQuery());
    }


    @GetMapping("queryById/{id}")
    @ApiOperation("新闻查看且（更新浏览量）")
    public Result<Boolean> page(@PathVariable("id") Long id) {
        NewsManagement byId = newsManagementService.getById(id);
        if (byId.getTimes()!=null){
            byId.setTimes(byId.getTimes()+1);
        }
        else{
            byId.setTimes(1L);
        }
        return Result.ok( newsManagementService.updateById(byId));
    }


    @DeleteMapping("deleteById/{id}")
    @ApiOperation("新闻删除根据ID")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(newsManagementService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }

    @PostMapping("add")
    @ApiOperation("新闻新增")
    public Result<Boolean> add(@RequestBody NewsManagement obj) {
        return Result.ok(newsManagementService.saveData(obj));
    }


    @PostMapping("update")
    @ApiOperation("新闻更新")
    public Result<Boolean> update(@RequestBody NewsManagement obj) {
        return Result.ok(newsManagementService.updateById(obj));
    }



}
