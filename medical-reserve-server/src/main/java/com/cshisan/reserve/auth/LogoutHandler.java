package com.cshisan.reserve.auth;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.utils.HttpUtil;
import com.cshisan.reserve.common.utils.ResultUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理类
 *
 * @author Administrator
 */
@Component
public class LogoutHandler implements LogoutSuccessHandler {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 删除redis缓存
        UserLoginEntity user = (UserLoginEntity) authentication.getPrincipal();
        String tokenKey = "token_" + user.getUid();
        redisTemplate.delete(tokenKey);
        Result<?> result = ResultUtil.ok(CodeEnum.OK);
        HttpUtil.writeResponse(httpServletResponse, result);
    }
}
