package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import com.cshisan.reserve.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


/**
 * @author CShisan
 * @date 2022-2-20 0:05
 *
 * {@link com.cshisan.reserve.entity.User}
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseVO {
    private Long uid;
    private String username;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
    private String phone;
    private String avatarUrl;
    private String realName;
    private String idCard;
    private List<Role> roles;

}
