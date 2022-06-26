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
public class Department extends BaseEntity implements Serializable {
    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    private static final long serialVersionUID = 1L;

}
