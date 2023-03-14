package jx.pgz.security;

import jx.pgz.dao.entity.SysAuthority;
import jx.pgz.dao.entity.SysRole;
import jx.pgz.dao.entity.SysUser;
import jx.pgz.server.UserServiceFace;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyRealm extends AuthorizingRealm {

    private final UserServiceFace userServiceFace;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        String username = (String) principalCollection.iterator().next();
        Set<String> roleNames = userServiceFace.getRoleByUsername(username).stream().map(SysRole::getDescription).collect(Collectors.toSet());
        Set<String> permission = userServiceFace.getAuthorityByUsername(username).stream().map(SysAuthority::getName).collect(Collectors.toSet());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(permission);
        return info;
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
