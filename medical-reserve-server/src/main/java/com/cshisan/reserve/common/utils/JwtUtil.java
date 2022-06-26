package com.cshisan.reserve.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.cshisan.reserve.auth.UserLoginEntity;
import com.cshisan.reserve.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author CShisan
 * @date 2022-2-19 15:46
 */
@Component
public class JwtUtil {
    private final JwtConfig config;
    private final RedisUtil redisUtil;
    private final JWTSigner signer;
    private final String tokenValuePrefix;

    @Autowired
    public JwtUtil(JwtConfig config, RedisUtil redisUtil) {
        this.config = config;
        this.redisUtil = redisUtil;
        this.signer = JWTSignerUtil.hs512(config.getSecret().getBytes());
        this.tokenValuePrefix = config.getTokenValuePrefix() + " ";
    }

    /**
     * 生成token
     *
     * @param user userDetails
     * @return token
     */
    public String create(UserLoginEntity user) {
        if (Objects.isNull(user)) {
            return config.getTokenValuePrefix();
        }
        Date now = new Date();
        return JWT.create()
                .setPayload("id", user.getId())
                .setPayload("uid", user.getUid())
                .setPayload("phone", user.getPhone())
                .setPayload("username", user.getUsername())
                .setPayload("avatarUrl", user.getAvatarUrl())
                .setPayload("delFlag", user.getDelFlag())
                .setPayload("roles", user.getRoles())
                .setIssuedAt(now).setNotBefore(now)
                .setExpiresAt(new Date((now.getTime() + config.getExpiration())))
                .setSigner(signer)
                .sign();
    }

    /**
     * 解析token
     *
     * @param token    token
     * @param attrName attrName
     * @return value
     */
    public Object parse(String token, String attrName) {
        if (Objects.isNull(token)) {
            return null;
        }
        // 若token有前缀要先剪除 不然报错
        if (token.startsWith(tokenValuePrefix)) {
            token = token.replaceFirst(tokenValuePrefix, "");
        }
        return JWTUtil.parseToken(token).getPayload(attrName);
    }

    /**
     * 获取缓存token验证
     *
     * @param token token
     * @param uid   uid
     * @return status
     */
    public boolean valid(String token, String uid) {
        String cacheToken = getCacheToken(uid);
        return token.equals(cacheToken) && isNotExpired(token);
    }

    /**
     * 验证是否过期
     *
     * @param token token
     * @return status
     */
    private boolean isNotExpired(String token) {
        try {
            JWTValidator.of(JWT.of(token)).validateDate(DateUtil.date(), 0L);
            return true;
        } catch (ValidateException var4) {
            return false;
        }
    }

    /**
     * 获取Redis缓存的token
     *
     * @param key key
     * @return cacheToken
     */
    public String getCacheToken(String key) {
        String cacheKey = getFullTokenKey(key);
        String cacheToken = (String) redisUtil.get(cacheKey);
        if (ObjectUtils.isEmpty(cacheToken)) {
            return null;
        }
        return cacheToken;
    }

    /**
     * 添加token到Redis缓存
     *
     * @param user userLoginEntity
     */
    public String putCacheToken(UserLoginEntity user) {
        if (Objects.nonNull(user) && Objects.nonNull(user.getUid())) {
            String uid = user.getUid().toString();
            String cacheToken = getCacheToken(uid);
            if (ObjectUtils.isEmpty(cacheToken)) {
                // 将token更新到缓存
                cacheToken = create(user);
                setToken(getFullTokenKey(uid), cacheToken);
            } else {
                refreshToken(cacheToken);
            }
            return cacheToken;
        }
        return null;
    }

    /**
     * 刷新失效时间
     *
     * @param token fullToken
     */
    public void refreshToken(String token) {
        String key = String.valueOf(parse(token, "uid"));
        if (!ObjectUtils.isEmpty(key)) {
            // 设置新的失效时间并更新到token
            Long current = System.currentTimeMillis();
            Date newExp = new Date(current + config.getExpiration());
            JWTUtil.parseToken(token).getPayload()
                    .setPayload("exp", newExp);

            // 将token更新到缓存
            setToken(getFullTokenKey(key), token);
        }
    }

    /**
     * 获取token的key(包含前缀)
     *
     * @param key key
     * @return fullKey
     */
    public String getFullTokenKey(String key) {
        return config.getTokenKeyPrefix() + key;
    }

    /**
     * 更新token到redis
     *
     * @param key   fullKey
     * @param value fullValue
     */
    private void setToken(String key, String value) {
        Long exp = config.getExpiration();
        redisUtil.set(key, value, exp, TimeUnit.MILLISECONDS);
    }

    public JwtConfig getConfig() {
        return config;
    }

    public String getTokenPrefix() {
        return tokenValuePrefix;
    }
}
