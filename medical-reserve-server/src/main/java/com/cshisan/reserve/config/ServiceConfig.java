package com.cshisan.reserve.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CShisan
 * @date 2022-2-20 11:52
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceConfig {
    private String avatarUrl;
}
