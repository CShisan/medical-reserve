package com.cshisan.reserve.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yuanbai
 * @date 2022/3/1 15:24
 */
@Data
@AllArgsConstructor
public class WxLoginBean {
    /**
     * 微信接口认证错误标志
     */
    private String errcode;
    private String code;

    /**
     * openid
     */
    private String openid;
    private String sessionKey;

    /**
     * 手机加密数据
     */
    private String encryptedData;
    private String iv;

    /**
     * 手机信息
     */
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;

}
