package com.cshisan.reserve.auth;

import cn.hutool.json.JSONUtil;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.HttpUtil;
import com.cshisan.reserve.entity.User;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CShisan
 * @date 2022-2-20 17:19
 */
public class AuthFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher PATH_REQUEST = new AntPathRequestMatcher("/login", "POST");
    private static final String REQUEST_METHOD = "POST";

    public AuthFilter(AuthenticationManager authenticationManager) {
        super(PATH_REQUEST, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(REQUEST_METHOD)) {
            throw new AuthenticationServiceException("不支持该请求方法：" + request.getMethod());
        }
        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("不支持表单请求");
        }

        User user = JSONUtil.toBean(HttpUtil.getBody(request), User.class);
        String loginKey = UserLoginBean.toJson(new UserLoginBean(user));
        if (!loginKey.contains(UserLoginBean.VALUE_NAME)) {
            throw new CustomException(CodeEnum.USER_NOT_FOUND);
        }

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(loginKey, user.getPassword());

        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
