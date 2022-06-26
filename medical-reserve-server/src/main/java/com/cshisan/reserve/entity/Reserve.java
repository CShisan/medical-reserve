package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Reserve extends BaseEntity implements Serializable {
    /**
     * 预约记录ID
     */
    private Long reserveId;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 医生ID
     */
    private Long doctorId;

    /**
     * 预约状态  0：已取消  1：待报到  2：已完成
     */
    private Integer status;

    /**
     * 预约时段  0：上午  1：下午
     */
    private Integer timeInterval;

    /**
     * 预约具体时段 {@link com.cshisan.reserve.common.enums.ReservePeriodEnum}
     */
    private Integer period;

    /**
     * 费用
     */
    private Integer charge;

    /**
     * 预约的日期
     */
    private Date reserveDate;

    /**
     * 具体时段可预约数量
     */
    public static final int PERIOD_NUM = 40;

    private static final long serialVersionUID = 1L;

}

