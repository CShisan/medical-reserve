package com.cshisan.reserve.config;

import com.cshisan.reserve.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author CShisan
 * @date 2022-2-19 15:02
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenAuthFilter jwtTokenAuthFilter;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailHandler authFailHandler;
    private final LogoutHandler logoutHandler;

    @Autowired
    public WebSecurityConfig(
            AuthSuccessHandler authSuccessHandler,
            JwtTokenAuthFilter jwtTokenAuthFilter,
            UserDetailsServiceImpl userDetailsService,
            AuthFailHandler authFailHandler,
            LogoutHandler logoutHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenAuthFilter = jwtTokenAuthFilter;
        this.authSuccessHandler = authSuccessHandler;
        this.authFailHandler = authFailHandler;
        this.logoutHandler = logoutHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户信息处理  设置密码加密方式
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .successHandler(authSuccessHandler)
                .permitAll();

        http
                .logout().permitAll()
                .logoutSuccessHandler(logoutHandler);

        String[] whiteList = {
                "/test/**",
                "/register",
                "/captcha",
                "/mini/wx-login"
        };
        // 使用jwt关闭csrf并设置白名单
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(whiteList)
                .permitAll().anyRequest()
                .authenticated();
    }

    /**
     * 注册自定义的登录处理器
     */
    @Bean
    AuthFilter authFilter() throws Exception {
        AuthFilter filter = new AuthFilter(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationFailureHandler(authFailHandler);
        filter.setFilterProcessesUrl("/login");
        return filter;
    }
}
