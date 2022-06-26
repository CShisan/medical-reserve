package com.cshisan.reserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 微信OPENID
     */
    private String openid;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<Role> roles;

    private static final long serialVersionUID = 1L;

}

