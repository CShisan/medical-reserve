package com.cshisan.reserve.common.utils;

import com.cshisan.reserve.entity.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * UserDetail工具类
 *
 * @author CShisan
 * @date 2022-2-20 11:13
 */
public class UserDetailUtil {

    /**
     * security上下文根据获取用户
     *
     * @return username
     */
    public static User getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        boolean userExist = null != securityContext &&
                null != securityContext.getAuthentication() &&
                null != securityContext.getAuthentication().getDetails();

        if (userExist) {
            return (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        }
        return null;
    }

    /**
     * security上下文根据获取用户
     *
     * @return uid
     */
    public static Long getUid() {
        User user = getUser();
        if (null!=user) {
            return user.getUid();
        }
        return null;
    }

    /**
     * 获取用户当前token
     *
     * @return token
     */
    public static String getCurrentToken() {
        return Objects.requireNonNull(HttpUtil.getHeaders()).get("authorization");
    }
}
