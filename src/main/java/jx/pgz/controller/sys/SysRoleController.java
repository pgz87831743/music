package jx.pgz.controller.sys;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.sys.entity.SysRole;
import jx.pgz.dao.sys.service.SysRoleService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sys/sysRole")
public class SysRoleController {

    @Resource
    private  SysRoleService iSysRoleService;


    @PostMapping("page")
    public Result<Page<SysRole>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(iSysRoleService.page(pageDTO));
    }

    @GetMapping("listAll")
    public Result<List<SysRole>> listAll() {
        return Result.ok(iSysRoleService.listAll());
    }

    @GetMapping("getById/{id}")
    public Result<SysRole> getSysRoleById(@PathVariable("id") String id) {
        return Result.ok(iSysRoleService.getSysRoleById(id));
    }

    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteSysRoleById(@PathVariable("id") String id) {
        return Result.ok(iSysRoleService.deleteSysRoleById(id));
    }

    @PostMapping("add")
    public Result<Boolean> addSysRole(@RequestBody SysRole obj) {
        return Result.ok(iSysRoleService.addSysRole(obj));
    }


    @PutMapping("updateById")
    public Result<Boolean> updateSysRoleById(@RequestBody SysRole obj) {
        return Result.ok(iSysRoleService.updateSysRoleById(obj));
    }

}
