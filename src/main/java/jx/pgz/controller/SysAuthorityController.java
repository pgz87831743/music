package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.entity.SysAuthority;
import jx.pgz.dao.service.SysAuthorityService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sys/sysAuthority")
public class SysAuthorityController {

    @Resource
    private  SysAuthorityService iSysAuthorityService;


    @PostMapping("page")
    public Result<Page<SysAuthority>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(iSysAuthorityService.page(pageDTO));
    }

    @GetMapping("listAll")
    public Result<List<SysAuthority>> listAll() {
        return Result.ok(iSysAuthorityService.listAll());
    }

    @GetMapping("getById/{id}")
    public Result<SysAuthority> getSysAuthorityById(@PathVariable("id") String id) {
        return Result.ok(iSysAuthorityService.getSysAuthorityById(id));
    }

    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteSysAuthorityById(@PathVariable("id") String id) {
        return Result.ok(iSysAuthorityService.deleteSysAuthorityById(id));
    }

    @PostMapping("add")
    public Result<Boolean> addSysAuthority(@RequestBody SysAuthority obj) {
        return Result.ok(iSysAuthorityService.addSysAuthority(obj));
    }


    @PutMapping("updateById")
    public Result<Boolean> updateSysAuthorityById(@RequestBody SysAuthority obj) {
        return Result.ok(iSysAuthorityService.updateSysAuthorityById(obj));
    }

}
