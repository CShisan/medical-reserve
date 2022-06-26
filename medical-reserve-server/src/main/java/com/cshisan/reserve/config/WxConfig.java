package com.cshisan.reserve.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanbai
 * @date 2022/2/28 15:59
 */
@Data
@Configuration
@ConfigurationProperties("wx")
public class WxConfig {
    private String appid;
    private String secret;
}
