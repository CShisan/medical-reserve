package com.cshisan.reserve.common.utils;

import com.cshisan.reserve.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author CShisan
 * @date 2022-2-20 11:13
 */
@Component
public class RedisUtil {
    private final RedisConfig config;
    private ValueOperations<String, Object> valueOperations;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtil(RedisConfig config) {
        this.config = config;
    }


    public ValueOperations<String, Object> getValueOperations() {
        if (Objects.isNull(valueOperations)) {
            synchronized (ValueOperations.class) {
                if (Objects.isNull(valueOperations)) {
                    return redisTemplate.opsForValue();
                }
            }
        }
        return valueOperations;
    }

    public void set(String key, Object value, Long exp, TimeUnit unit) {
        // 设置传入失效时间,若未传入则默认10分钟
        exp = Objects.isNull(exp) ? config.getExpiration() : exp;
        // 设置失效时间单位(毫秒)
        TimeUnit defaultUnit = TimeUnit.valueOf("MILLISECONDS");
        unit = Objects.isNull(unit) ? defaultUnit : unit;

        valueOperations = getValueOperations();
        valueOperations.set(key, value, exp, unit);
    }

    public Object get(String key) {
        valueOperations = getValueOperations();
        return valueOperations.get(key);
    }
}
