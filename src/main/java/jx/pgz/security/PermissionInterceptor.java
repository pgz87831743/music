package jx.pgz.security;

import jx.pgz.execptions.MyRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
public class PermissionInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        if (Objects.nonNull(permission)) {
            return true;
        } else {
//            boolean hasAuthority = UserManager.getAuthority(UserContext.getInstance().getUserId())
//                    .stream().anyMatch(s -> s.getName().equals(permission.value()));
//            if (!hasAuthority) {
//                throw new MyRuntimeException("暂无权限");
//            }
            return true;
        }
    }
}