package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import com.cshisan.reserve.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/21 15:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {
    private Long uid;
    private String username;
    private String password;
    private String newPassword;
    private Date birthday;
    private Integer sex;
    private String phone;
    private String avatarUrl;
    private String realName;
    private String idCard;
    private List<Role> roles;

    /**
     * 以下是微信小程序字段
     */
    private String wxCode;

    private String encryptedData;

    private String iv;

}
