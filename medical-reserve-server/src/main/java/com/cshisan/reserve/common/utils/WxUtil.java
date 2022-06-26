package com.cshisan.reserve.common.utils;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.cshisan.reserve.auth.WxLoginBean;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.config.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuanbai
 * @date 2022/3/2 12:11
 */
@Component
public class WxUtil {
    private final WxMaService wxMaService;

    @Autowired
    public WxUtil(WxConfig config) {
        // 解密工具注入
        this.wxMaService = new WxMaServiceImpl();
        WxMaDefaultConfigImpl wxMaDefaultConfig = new WxMaDefaultConfigImpl();
        wxMaDefaultConfig.setAppid(config.getAppid());
        wxMaDefaultConfig.setSecret(config.getSecret());
        this.wxMaService.setWxMaConfig(wxMaDefaultConfig);
    }

    /**
     * 获取微信登录对象
     *
     * @param bean bean
     * @return 完整bean
     */
    public WxLoginBean getWxLoginBean(WxLoginBean bean) {
        WxMaJscode2SessionResult session;
        WxMaPhoneNumberInfo phoneNoInfo;
        try {
            session = wxMaService.getUserService().getSessionInfo(bean.getCode());
            phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(session.getSessionKey(), bean.getEncryptedData(), bean.getIv());
        } catch (Exception e) {
            throw new CustomException(CodeEnum.USER_AUTH_ERROR);
        }
        bean.setOpenid(session.getOpenid());
        bean.setSessionKey(session.getSessionKey());
        bean.setPhoneNumber(phoneNoInfo.getPhoneNumber());
        bean.setPurePhoneNumber(phoneNoInfo.getPurePhoneNumber());
        bean.setCountryCode(phoneNoInfo.getCountryCode());
        return bean;
    }
}
