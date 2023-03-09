package jx.pgz.controller.baisc;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jx.pgz.dao.sys.entity.SysAuthority;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.enums.ResultCodeEnum;
import jx.pgz.execptions.MyRuntimeException;
import jx.pgz.model.dto.AssignAuthorityDTO;
import jx.pgz.model.dto.AssignRolesDTO;
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

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/system")
@RestController
@Api(tags = "用户操作")
public class SysUserFaceController {

    @Resource
    private  UserServiceFace userServiceFace;

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
        List<SysAuthority> authorityByUsername = userServiceFace.getAuthorityByUsername(loginDTO.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("user", backUser);
        map.put("token", token);
        map.put("authority", authorityByUsername);
        return Result.builder().msg("登录成功").showMsg(true).code(ResultCodeEnum.SUCCESS.getCode()).data(map).build();
    }


    @ApiOperation("获取所有角色")
    @GetMapping("/roleList/{username}")
    public Result<Object> roleList(@PathVariable("username") String username) {
        return Result.builder().data(userServiceFace.roleList(username)).build();
    }

    @ApiOperation("权限菜单树")
    @GetMapping("/authorityTree")
    public Result<Object> authorityTree() {
        return Result.builder().data(userServiceFace.authorityTree()).build();
    }

    @ApiOperation("权限菜单树")
    @GetMapping("/authorityTreeByRoleId/{roleId}")
    public Result<Object> authorityTreeByRoleId(@PathVariable("roleId") Long roleId) {
        return Result.builder().data(userServiceFace.authorityTreeByRoleId(roleId)).build();
    }

    @ApiOperation("分配角色")
    @PostMapping("/assignRoles")
    public Result<Object> assignRoles(@RequestBody AssignRolesDTO assignRolesDTO) {
        return Result.ok().setData(userServiceFace.assignRoles(assignRolesDTO)).setMsg("角色分配成功").setShowMsg(true);
    }


    @ApiOperation("分配权限")
    @PostMapping("/assignAuthority")
    public Result<Object> assignAuthority(@RequestBody AssignAuthorityDTO authorityDTO) {
        return Result.ok().setData(userServiceFace.assignAuthority(authorityDTO)).setMsg("权限分配成功").setShowMsg(true);
    }


    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return new Result().setCode(200).setMsg("成功退出").setShowMsg(true);
    }


}
