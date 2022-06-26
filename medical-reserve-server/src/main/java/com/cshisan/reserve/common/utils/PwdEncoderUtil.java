package com.cshisan.reserve.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * PasswordEncoder工具类
 *
 * @author Administrator
 */
public class PwdEncoderUtil {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 用BCryptPasswordEncoder加密
     *
     * @param password 明文密码
     * @return 加密密码
     */
    public static String encodePwd(String password) {
        return encoder.encode(password);
    }

    /**
     * 校验密码
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 已加密密码
     * @return status
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (BeanUtil.orIsEmpty(rawPassword, encodedPassword)) {
            return false;
        }
        return encoder.matches(rawPassword, encodedPassword);
    }

}
