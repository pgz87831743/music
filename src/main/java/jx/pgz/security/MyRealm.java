package jx.pgz.security;

import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.server.UserServiceFace;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyRealm extends AuthorizingRealm {

    private final UserServiceFace userServiceFace;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //认证
        JwtToken token = (JwtToken) authenticationToken;
        String jwt = (String) token.getPrincipal();
        String username = JwtUtil.parseJWT(jwt).getId();
        SysUser user = userServiceFace.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(
                username,
                user.getPassword(),
                getName()
        );
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
