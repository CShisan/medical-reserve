package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
* 
* @author yuanbai
* @date 2022/2/25 10:36
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Rotation extends BaseEntity implements Serializable {
    /**
    * 班次ID
    */
    private Long rotationId;

    /**
    * 班次KEY
    */
    private String rotationKey;

    private static final long serialVersionUID = 1L;

}