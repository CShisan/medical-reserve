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
public class Doctor extends BaseEntity implements Serializable {
    /**
     * 医生ID
     */
    private Long doctorId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 职称ID
     */
    private Long jobTitleId;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}

