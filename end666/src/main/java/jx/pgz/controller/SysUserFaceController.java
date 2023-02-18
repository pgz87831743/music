package jx.pgz.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.security.IgnoreAuth;
import jx.pgz.server.SysUserServiceFace;
import jx.pgz.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<SysUser> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return Result.ok(sysUserServiceFace.login(username, password));
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    @IgnoreAuth
    public Result<SysUser> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        return Result.ok(sysUserServiceFace.register(username, password));
    }

    @PostMapping("/refreshToken")
    @ApiOperation("刷新token")
    public Result<SysUser> refreshToken(@RequestParam("token") String token) {
        return Result.ok(sysUserServiceFace.refreshToken(token));
    }


}
