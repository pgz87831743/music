package jx.pgz.controller.sys;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author
 * @since 2023
 */
@RestController
@RequestMapping("/sys/sysUser")
public class SysUserController {

    @Resource
    private  SysUserService sysUserService;


    @PostMapping("page")
    public Result<Page<SysUser>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(sysUserService.page(pageDTO));
    }

    @GetMapping("listAll")
    public Result<List<SysUser>> listAll() {
        return Result.ok(sysUserService.listAll());
    }

    @GetMapping("getById/{id}")
    public Result<SysUser> getSysUserById(@PathVariable("id") String id) {
        return Result.ok(sysUserService.getSysUserById(id));
    }

    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteSysUserById(@PathVariable("id") String id) {
        return Result.ok(sysUserService.deleteSysUserById(id));
    }

    @PostMapping("add")
    public Result<Boolean> addSysUser(@RequestBody SysUser obj) {
        return Result.ok(sysUserService.addSysUser(obj));
    }


    @PutMapping("updateById")
    public Result<Boolean> updateSysUserById(@RequestBody SysUser obj) {
        return Result.ok(sysUserService.updateSysUserById(obj)).setMsg("修改成功").setShowMsg(true);
    }


}


