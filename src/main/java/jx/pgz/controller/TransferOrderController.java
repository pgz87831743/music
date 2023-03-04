package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import jx.pgz.entity.TransferOrder;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.service.TransferOrderService;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 调拨单 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/transferOrder")

public class TransferOrderController {

    @Resource
    private  TransferOrderService iTransferOrderService;


    @PostMapping("page")
    @ApiOperation("调拨单分页")
    public Result<Page<TransferOrder>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(iTransferOrderService.page(pageDTO));
    }

    @GetMapping("listAll")
    @ApiOperation("调拨单列表")
    public Result<List<TransferOrder>> listAll() {
        return Result.ok(iTransferOrderService.listAll());
    }

    @GetMapping("getById/{id}")
    @ApiOperation("查询调拨单")
    public Result<TransferOrder> getTransferOrderById(@PathVariable("id") Long id) {
        return Result.ok(iTransferOrderService.getTransferOrderById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @ApiOperation("删除调拨单")
    public Result<Boolean> deleteTransferOrderById(@PathVariable("id") Long id) {
        return Result.ok(iTransferOrderService.deleteTransferOrderById(id));
    }

    @PostMapping("add")
    @ApiOperation("新增调拨单")
    public Result<Boolean> addTransferOrder(@RequestBody TransferOrder obj) {
        return Result.ok(iTransferOrderService.addTransferOrder(obj));
    }


    @PutMapping("updateById")
    @ApiOperation("更新调拨单")
    public Result<Boolean> updateTransferOrderById(@RequestBody TransferOrder obj) {
        return Result.ok(iTransferOrderService.updateTransferOrderById(obj));
    }

}
