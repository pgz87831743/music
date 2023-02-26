package jx.pgz.security;

import io.jsonwebtoken.Claims;
import jx.pgz.config.PropertiesConfiguration;
import jx.pgz.execptions.MyRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * yao
 * @date 2021/6/28 11:17
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Resource
    private PropertiesConfiguration propertiesConfiguration;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        IgnoreAuth ignoreAuth = method.getAnnotation(IgnoreAuth.class);
        if (Objects.nonNull(ignoreAuth)) {
            return true;
        } else {
            String authorization = request.getHeader("token");
            if (StringUtils.hasText(authorization)) {
                Claims claims = JWTUtil.parseToken(authorization, propertiesConfiguration.getTokenKey());
                if (claims != null) {
                    UserContext.getInstance().setContext(claims);
                    return true;
                }
            }
            throw new MyRuntimeException("认证失败重新登录");
        }
    }
}