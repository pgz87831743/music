package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.MessageManagement;
import jx.pgz.dao.yw.service.MessageManagementService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 留言管理 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/yw/message-management")
@RequiredArgsConstructor
public class MessageManagementController {

    private final MessageManagementService messageManagementService;


    @PostMapping("page")
    public Result<Page<MessageManagement>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(messageManagementService.pageQuery(pageDTO));
    }


    @PostMapping("queryById/{id}")
    public Result<MessageManagement> page(@PathVariable("id") Long id) {
        return Result.ok(messageManagementService.getById(id));
    }


    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(messageManagementService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }

    @PostMapping("add")
    public Result<Boolean> add(@RequestBody MessageManagement obj) {
        return Result.ok(messageManagementService.save(obj));
    }


    @PostMapping("update")
    public Result<Boolean> update(@RequestBody MessageManagement obj) {
        return Result.ok(messageManagementService.updateById(obj));
    }
}
