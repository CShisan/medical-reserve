package com.cshisan.reserve.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.entity.UserRole;
import com.cshisan.reserve.mapper.UserRoleMapper;
import com.cshisan.reserve.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/22 9:23
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {
    private final UserRoleMapper mapper;

    @Autowired
    public UserRoleService(UserRoleMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 根据uid获取roles
     *
     * @param uid uid
     * @return roles
     */
    public List<RoleVO> getRolesByUid(Long uid) {
        List<RoleVO> list = new ArrayList<>();
        mapper.getRolesByUid(uid).forEach(role->{
            list.add(BeanUtil.convert(role,new RoleVO()));
        });
        return list;
    }

    public List<UserRole> getRolesBy(UserRole req){
        QueryWrapper<UserRole> wrapper =new QueryWrapper<>(req);
        return this.list(wrapper);
    }
}
