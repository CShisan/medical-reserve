package com.cshisan.reserve.auth;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.utils.HttpUtil;
import com.cshisan.reserve.common.utils.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CShisan
 * @date 2022-2-20 18:32
 */
@Component
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        /*
            如果想要自定义返回就不要再调用一遍父类的onAuthenticationFailure
            不然会报需要完全验证身份才可以访问的错误并覆盖当前自定义返回信息
         */
        Result<?> result = ResultUtil.fail(CodeEnum.USER_AUTH_ERROR.getCode(), exception.getMessage());
        HttpUtil.writeResponse(response, result);
    }
}
