package com.cshisan.reserve.auth;

import cn.hutool.jwt.JWT;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author CShisan
 * @date 2022-2-19 16:26
 */
@Component
public class JwtTokenAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtTokenAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // username为空或者token为空则return
        String token = request.getHeader(jwtUtil.getConfig().getHeader());
        if (ObjectUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 剪去前端传过来的前缀
        token = token.replaceFirst(jwtUtil.getTokenPrefix(), "");

        String uid = String.valueOf(jwtUtil.parse(token, "uid"));
        if (Objects.isNull(uid)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 与缓存的token比较,不同则认证失败
        if (!jwtUtil.valid(token, uid)) {
            throw new CustomException(CodeEnum.USER_AUTH_ERROR);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // auth为空则代表用户名已登录但security上下文被清空,否则代表已登录且存在上下文则更新token失效时间
        if (Objects.isNull(auth)) {
            // 将token解析为UserDetails
            UserDetails userDetails = JWT.of(token).getPayloads().toBean(UserLoginEntity.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            jwtUtil.refreshToken(token);
        }


        filterChain.doFilter(request, response);
    }
}
