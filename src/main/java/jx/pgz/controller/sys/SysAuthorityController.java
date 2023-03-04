package jx.pgz.controller.sys;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.sys.entity.SysAuthority;
import jx.pgz.dao.sys.service.SysAuthorityService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

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
@RequiredArgsConstructor
    public class SysAuthorityController {

        private final SysAuthorityService iSysAuthorityService;


        @PostMapping("page")
        public Result<Page<SysAuthority>> page(@RequestBody PageDTO pageDTO) {
            return Result.ok(iSysAuthorityService.page(pageDTO));
        }

        @GetMapping("listAll")
        public Result<List<SysAuthority>> listAll() {
            return Result.ok(iSysAuthorityService.listAll());
        }

        @GetMapping("getById/{id}")
        public Result<SysAuthority> getSysAuthorityById(@PathVariable("id") Long id) {
            return Result.ok(iSysAuthorityService.getSysAuthorityById(id));
        }

        @DeleteMapping("deleteById/{id}")
        public Result<Boolean> deleteSysAuthorityById(@PathVariable("id") Long id) {
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
