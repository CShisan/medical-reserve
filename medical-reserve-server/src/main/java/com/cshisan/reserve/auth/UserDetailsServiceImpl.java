package com.cshisan.reserve.auth;

import cn.hutool.core.util.ReflectUtil;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.entity.User;
import com.cshisan.reserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author CShisan
 * @date 2022-2-19 17:37
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }


    /**
     * 根据uid查询user并设置userLoginEntity
     *
     * @see UserDetailsServiceImpl#loadUserByUser
     */
    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        UserLoginBean bean = UserLoginBean.toBean(param);
        User user = new User();
        // 将loginKey反射到user实现自动匹配参数
        ReflectUtil.setFieldValue(user,bean.getKey(),bean.getValue());
        return loadUserByUser(user);
    }

    /**
     * 根据uid查询user并设置userLoginEntity
     *
     * @param user uid
     * @return userLoginEntity
     */
    private UserDetails loadUserByUser(User user) throws UsernameNotFoundException {
        user = userService.getUserLoginEntity(user);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserLoginEntity userLoginEntity = BeanUtil.convert(user, new UserLoginEntity());
        userLoginEntity.setAuthorities(userLoginEntity.getAuthorities());

        return userLoginEntity;
    }
}
