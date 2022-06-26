package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cshisan.reserve.entity.Role;
import com.cshisan.reserve.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-19 18:44
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 根据uid获取roles
     * @param uid uid
     * @return roles
     */
    @Select("select ur.role_id, r.role_key, r.role_name from user_role as ur, role as r " +
            "where uid = #{uid} and ur.role_id = r.role_id and ur.del_flag = 0 and r.del_flag = 0")
    List<Role> getRolesByUid(Long uid);
}
