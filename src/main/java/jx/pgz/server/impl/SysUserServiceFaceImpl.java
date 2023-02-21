package jx.pgz.server.impl;

import io.jsonwebtoken.Claims;
import jx.pgz.config.PropertiesConfiguration;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.execptions.MyRuntimeException;
import jx.pgz.server.SysUserServiceFace;
import jx.pgz.utils.JWTUtil;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserServiceFaceImpl implements SysUserServiceFace {

    private final SysUserService sysUserService;
    private final PropertiesConfiguration propertiesConfiguration;

    @Override
    public SysUser login(String username, String password) {
        SysUser user = sysUserService.lambdaQuery().eq(SysUser::getUsername, username)
                .eq(SysUser::getPassword, password).one();
        if (user != null) {
            String token = JWTUtil.createToken(user.getId(), user.getUsername(), propertiesConfiguration.getTokenKey(), propertiesConfiguration.getExpireMinutes());
            user.setToken(token);
            Claims claims = JWTUtil.parseToken(token, propertiesConfiguration.getTokenKey());
            UserContext.getInstance().setContext(claims);
            return user;
        }
        throw new MyRuntimeException("用户名或密码错误");
    }

    @Override
    public SysUser register(String username, String password) {
        SysUser user = sysUserService.lambdaQuery().eq(SysUser::getUsername, username).one();
        if (user != null) {
            throw new MyRuntimeException("用户已存在");
        }
        SysUser newUser = SysUser.builder().username(username).password(password).build();
        sysUserService.save(newUser);
        return newUser;
    }

    @Override
    public SysUser refreshToken(String token) {
        Claims claims = JWTUtil.parseToken(token, propertiesConfiguration.getTokenKey());
        if (claims == null) {
            throw new MyRuntimeException("token 异常");
        }
        Long userId = Long.valueOf(claims.get("userId")+"") ;
        SysUser user = sysUserService.getById(userId);
        String newToken = JWTUtil.createToken(userId, user.getUsername(), propertiesConfiguration.getTokenKey(), propertiesConfiguration.getExpireMinutes());
        user.setToken(newToken);
        Claims newClaims = JWTUtil.parseToken(newToken, propertiesConfiguration.getTokenKey());
        UserContext.getInstance().setContext(newClaims);
        return user;
    }

    @Override
    public SysUser getCurrentUser(Long userId) {
        return sysUserService.getById(userId);
    }

    @Override
    public boolean updateUser(SysUser sysUser) {
        return sysUserService.updateById(sysUser);
    }
}
