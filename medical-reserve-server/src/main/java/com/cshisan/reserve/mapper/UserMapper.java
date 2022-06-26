package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cshisan.reserve.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-19 10:33
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据uid获取登录实体
     *
     * @param user user
     * @return userLoginEntity
     */
    @Select("select id, username, del_flag, uid, phone, id_card, password, avatar_url, sex, birthday, real_name " +
            "from user where (uid = #{uid} or phone = #{phone} or openid = #{openid}) and del_flag = 0")
    @Results(id = "userAllArgMap", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "del_flag", property = "delFlag"),
            @Result(column = "editor", property = "editor"),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "uid", property = "uid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "avatar_url", property = "avatarUrl"),
            @Result(column = "uid", property = "roles", javaType = List.class,
                    many = @Many(select = "com.cshisan.reserve.mapper.UserRoleMapper.getRolesByUid"))
    })
    User getUserLoginEntity(User user);


}
