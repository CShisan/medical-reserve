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
public class Role extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色KEY
     */
    private String roleKey;

    /**
     * 角色名称
     */
    private String roleName;

    private static final long serialVersionUID = 1L;

}

