package com.cshisan.reserve.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CShisan
 * @date 2022-2-20 11:23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String type;
    private String header;
    private String secret;
    private Long expiration;
    private String tokenKeyPrefix;
    private String tokenValuePrefix;
}
