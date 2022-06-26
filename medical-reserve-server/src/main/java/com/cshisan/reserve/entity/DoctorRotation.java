package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
* 
* @author yuanbai
* @date 2022/2/25 10:34
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRotation extends BaseEntity implements Serializable {
    /**
    * 医生ID
    */
    private Long doctorId;

    /**
     * 科室ID
     */
    private Long deptId;

    /**
    * 班次ID
    */
    private Long rotationId;

    private static final long serialVersionUID = 1L;

}