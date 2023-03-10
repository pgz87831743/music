package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("留言分页")
    public Result<Page<MessageManagement>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(messageManagementService.pageQuery(pageDTO));
    }


    @PostMapping("queryById/{id}")
    @ApiOperation("留言详情")
    public Result<MessageManagement> page(@PathVariable("id") Long id) {
        return Result.ok(messageManagementService.getById(id));
    }


    @DeleteMapping("deleteById/{id}")
    @ApiOperation("留言删除通过id")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(messageManagementService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }

    @PostMapping("add")
    @ApiOperation("留言新增")
    public Result<Boolean> add(@RequestBody MessageManagement obj) {
        return Result.ok(messageManagementService.save(obj)).setShowMsg(true).setMsg("留言成功");
    }


    @PostMapping("update")
    @ApiOperation("留言更新")
    public Result<Boolean> update(@RequestBody MessageManagement obj) {
        return Result.ok(messageManagementService.updateById(obj));
    }
}
