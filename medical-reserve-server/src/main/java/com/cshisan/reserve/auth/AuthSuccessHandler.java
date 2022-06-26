package com.cshisan.reserve.auth;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.HttpUtil;
import com.cshisan.reserve.common.utils.JwtUtil;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-19 19:30
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 获取security上下文中的用户登录对象
        UserLoginEntity userLoginEntity = (UserLoginEntity) authentication.getPrincipal();
        userLoginEntity.setPassword(null);
        // 获取缓存中的token,没有则创建并更新到缓存
        String token = jwtUtil.putCacheToken(userLoginEntity);

        if (ObjectUtils.isEmpty(token)) {
            throw new CustomException(CodeEnum.FAIL);
        }

        UserVO user = BeanUtil.convert(userLoginEntity, new UserVO());

        // 设置返回值
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("user", user);
        resultMap.put("token", token);
        Result<?> result = ResultUtil.ok(resultMap, CodeEnum.OK.getMessage());

        // 写入response
        HttpUtil.writeResponse(response, result);
    }
}
