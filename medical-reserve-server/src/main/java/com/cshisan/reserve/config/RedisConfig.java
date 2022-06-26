package com.cshisan.reserve.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author yuanbai
 * @date 2022/2/21 12:20
 */
@Data
@Configuration
@ConfigurationProperties("redis")
public class RedisConfig {
    private Long expiration;

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

        // String类型key序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // String类型value序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // Hash类型key序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // Hash类型value序列器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
