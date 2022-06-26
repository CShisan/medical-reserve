package com.cshisan.reserve.auth;

import com.cshisan.reserve.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author CShisan
 * @date 2022-2-19 15:25
 */
@Data
public class UserLoginEntity implements UserDetails {
    private Long id;

    private Long uid;

    private String phone;

    private String username;

    private String password;

    private String avatarUrl;

    private Boolean delFlag;

    private List<Role> roles;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.nonNull(authorities)){
            return authorities;
        }
        if (Objects.isNull(roles)) {
            return new ArrayList<>();
        }
        synchronized (this) {
            if(Objects.isNull(authorities)){
                authorities = roles.stream().map(Role::getRoleKey)
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.nonNull(delFlag) && !delFlag;
    }
}
