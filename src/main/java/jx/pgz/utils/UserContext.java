package jx.pgz.utils;


import jx.pgz.dao.entity.SysRole;
import jx.pgz.dao.entity.SysUser;
import jx.pgz.dao.service.SysUserService;
import jx.pgz.server.UserServiceFace;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserContext implements ApplicationContextAware {

    private static SysUserService sysUserService;
    private static UserServiceFace userServiceFace;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UserContext.sysUserService = applicationContext.getBean(SysUserService.class);
        UserContext.userServiceFace = applicationContext.getBean(UserServiceFace.class);
    }


    public static SysUser getUser() {
        return sysUserService.lambdaQuery().eq(SysUser::getUsername, SecurityUtils.getSubject().getPrincipal()).one();
    }

    public static List<String> getRole() {
        return userServiceFace.getRoleByUsername(SecurityUtils.getSubject().getPrincipal().toString()).stream().map(SysRole::getName).collect(Collectors.toList());
    }


}
