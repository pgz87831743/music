package jx.pgz.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.enums.ResultCodeEnum;
import jx.pgz.execptions.MyRuntimeException;
import jx.pgz.model.dto.LoginDTO;
import jx.pgz.security.JwtToken;
import jx.pgz.security.JwtUtil;
import jx.pgz.server.UserServiceFace;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/user")
@RestController
@Api(tags = "用户操作")
@RequiredArgsConstructor
public class SysUserFaceController {

    private final UserServiceFace userServiceFace;

    /**
     * 自定义token登录
     *
     * @return
     */
    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public Result<Object> login(@RequestBody LoginDTO loginDTO) {
        if (!StringUtils.hasText(loginDTO.getUsername()) || !StringUtils.hasText(loginDTO.getPassword())) {
            throw new MyRuntimeException("账号密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtil.createJWT(loginDTO.getUsername(), "back", "user", Duration.ofDays(3).toMillis());
        JwtToken jwtToken = new JwtToken(token, loginDTO.getPassword());
        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            throw new MyRuntimeException("账号不存在");
        } catch (IncorrectCredentialsException e) {
            throw new MyRuntimeException("密码错误");
        }
        SysUser backUser = userServiceFace.getUserByUsername(loginDTO.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("user", backUser);
        map.put("token", token);
        return Result.builder().msg("登录成功").showMsg(true).code(ResultCodeEnum.SUCCESS.getCode()).data(map).build();
    }


    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return new Result().setCode(200).setMsg("成功退出").setShowMsg(true);
    }


}
