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
public class JobTitle extends BaseEntity implements Serializable {
    /**
     * 职称ID
     */
    private Long titleId;

    /**
     * 职称名称
     */
    private String titleName;

    /**
     * 预约费用
     */
    private Integer charge;

    private static final long serialVersionUID = 1L;

}

