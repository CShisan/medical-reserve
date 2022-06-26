package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/24 10:35
 */
@Data
public class ReserveDTO extends BaseDTO {
    private Long reserveId;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private Integer status;

    private Integer timeInterval;

    private Integer period;

    private Integer charge;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reserveDate;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReserveDTO)) return false;
        final ReserveDTO other = (ReserveDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$reserveId = this.getReserveId();
        final Object other$reserveId = other.getReserveId();
        if (this$reserveId == null ? other$reserveId != null : !this$reserveId.equals(other$reserveId)) return false;
        final Object this$patientId = this.getPatientId();
        final Object other$patientId = other.getPatientId();
        if (this$patientId == null ? other$patientId != null : !this$patientId.equals(other$patientId)) return false;
        final Object this$patientName = this.getPatientName();
        final Object other$patientName = other.getPatientName();
        if (this$patientName == null ? other$patientName != null : !this$patientName.equals(other$patientName))
            return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$doctorName = this.getDoctorName();
        final Object other$doctorName = other.getDoctorName();
        if (this$doctorName == null ? other$doctorName != null : !this$doctorName.equals(other$doctorName))
            return false;
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
        return other instanceof ReserveDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $reserveId = this.getReserveId();
        result = result * PRIME + ($reserveId == null ? 43 : $reserveId.hashCode());
        final Object $patientId = this.getPatientId();
        result = result * PRIME + ($patientId == null ? 43 : $patientId.hashCode());
        final Object $patientName = this.getPatientName();
        result = result * PRIME + ($patientName == null ? 43 : $patientName.hashCode());
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $doctorName = this.getDoctorName();
        result = result * PRIME + ($doctorName == null ? 43 : $doctorName.hashCode());
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
