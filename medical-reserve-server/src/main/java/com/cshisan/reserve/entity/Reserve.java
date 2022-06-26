package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Reserve)) return false;
        final Reserve other = (Reserve) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$reserveId = this.getReserveId();
        final Object other$reserveId = other.getReserveId();
        if (this$reserveId == null ? other$reserveId != null : !this$reserveId.equals(other$reserveId)) return false;
        final Object this$patientId = this.getPatientId();
        final Object other$patientId = other.getPatientId();
        if (this$patientId == null ? other$patientId != null : !this$patientId.equals(other$patientId)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$timeInterval = this.getTimeInterval();
        final Object other$timeInterval = other.getTimeInterval();
        if (this$timeInterval == null ? other$timeInterval != null : !this$timeInterval.equals(other$timeInterval))
            return false;
        final Object this$period = this.getPeriod();
        final Object other$period = other.getPeriod();
        if (this$period == null ? other$period != null : !this$period.equals(other$period)) return false;
        final Object this$charge = this.getCharge();
        final Object other$charge = other.getCharge();
        if (this$charge == null ? other$charge != null : !this$charge.equals(other$charge)) return false;
        final Object this$reserveDate = this.getReserveDate();
        final Object other$reserveDate = other.getReserveDate();
        if (this$reserveDate == null ? other$reserveDate != null : !this$reserveDate.equals(other$reserveDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Reserve;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $reserveId = this.getReserveId();
        result = result * PRIME + ($reserveId == null ? 43 : $reserveId.hashCode());
        final Object $patientId = this.getPatientId();
        result = result * PRIME + ($patientId == null ? 43 : $patientId.hashCode());
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $timeInterval = this.getTimeInterval();
        result = result * PRIME + ($timeInterval == null ? 43 : $timeInterval.hashCode());
        final Object $period = this.getPeriod();
        result = result * PRIME + ($period == null ? 43 : $period.hashCode());
        final Object $charge = this.getCharge();
        result = result * PRIME + ($charge == null ? 43 : $charge.hashCode());
        final Object $reserveDate = this.getReserveDate();
        result = result * PRIME + ($reserveDate == null ? 43 : $reserveDate.hashCode());
        return result;
    }
}

