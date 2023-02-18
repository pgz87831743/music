package jx.pgz.utils;

import io.jsonwebtoken.Claims;

public class UserContext {
    private ThreadLocal<Claims> threadLocal;

    private UserContext() {
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 创建实例
     *
     * @return
     */
    public static UserContext getInstance() {
        return SingletonContext.sInstance;
    }

    /**
     * 静态内部类单例模式
     * 单例初使化
     */
    private static class SingletonContext {
        private static final UserContext sInstance = new UserContext();
    }

    /**
     * 用户上下文中放入信息
     *
     * @param userInfoDTO
     */
    public void setContext(Claims userInfoDTO) {
        threadLocal.set(userInfoDTO);
    }

    /**
     * 获取上下文中的信息
     *
     * @return
     */
    public Claims getContext() {
        return threadLocal.get();
    }

    /**
     * 获取上下文中的用户名
     *
     * @return
     */
    public String getUsername() {
        if (getContext() != null) {
            return (String) getContext().get("username");
        }
        return null;
    }

    /**
     * 获取上下文中的用户id
     *
     * @return
     */
    public String getUserId() {
        if (getContext() != null) {
            return (String) getContext().get("userId");
        }
        return null;
    }

    public Long getExpirationTime() {
        if (getContext() != null) {
            return (Long) getContext().get("expirationTime");
        }
        return null;
    }

    /**
     * 清空上下文
     */
    public void clear() {
        threadLocal.remove();
    }


}
