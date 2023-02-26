package jx.pgz.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.model.dto.LoginDTO;
import jx.pgz.security.IgnoreAuth;
import jx.pgz.security.UserContext;
import jx.pgz.server.SysUserServiceFace;
import jx.pgz.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RequestMapping("/user")
@RestController
@Api(tags = "用户操作")
public class SysUserFaceController {


    @Resource
    private SysUserServiceFace sysUserServiceFace;


    @PostMapping("/login")
    @ApiOperation("登录")
    @IgnoreAuth
    public Result<SysUser> login(@RequestBody LoginDTO loginDTO) {
        return Result.ok(sysUserServiceFace.login(loginDTO.getUsername(), loginDTO.getPassword()));
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    @IgnoreAuth
    public Result<SysUser> register(@RequestBody LoginDTO loginDTO) {
        return Result.ok(sysUserServiceFace.register(loginDTO.getUsername(), loginDTO.getPassword())).setMsg("注册成功").setShowMsg(true);
    }

    @PostMapping("/refreshToken")
    @ApiOperation("刷新token")
    public Result<SysUser> refreshToken(@RequestParam("token") String token) {
        return Result.ok(sysUserServiceFace.refreshToken(token));
    }


    @PostMapping("/getCurrenUser")
    @ApiOperation("获取user")
    public Result<SysUser> getCurrenUser() {
        return Result.ok(sysUserServiceFace.getCurrentUser(UserContext.getInstance().getUserId()));
    }

    @PostMapping("/updateUser")
    @ApiOperation("获取user")
    public Result<Boolean> updateUser(@RequestBody SysUser sysUser) {
        return Result.ok(sysUserServiceFace.updateUser(sysUser));
    }


}
