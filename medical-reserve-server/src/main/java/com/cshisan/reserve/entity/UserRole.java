package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity implements Serializable {
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 角色ID
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;

}

