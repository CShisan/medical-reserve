package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/24 10:34
 */
@Data
public class ReserveVO extends BaseVO {
    private Long reserveId;

    private Long patientId;

    private String patientName;

    private Integer sex;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    private Long doctorId;

    private String doctorName;

    private String deptName;

    private Integer status;

    private String statusText;

    private Integer timeInterval;

    private String intervalText;

    private Integer period;

    private String periodText;

    private Integer charge;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reserveDate;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReserveVO)) return false;
        final ReserveVO other = (ReserveVO) o;
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
        final Object this$sex = this.getSex();
        final Object other$sex = other.getSex();
        if (this$sex == null ? other$sex != null : !this$sex.equals(other$sex)) return false;
        final Object this$birthday = this.getBirthday();
        final Object other$birthday = other.getBirthday();
        if (this$birthday == null ? other$birthday != null : !this$birthday.equals(other$birthday)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$doctorName = this.getDoctorName();
        final Object other$doctorName = other.getDoctorName();
        if (this$doctorName == null ? other$doctorName != null : !this$doctorName.equals(other$doctorName))
            return false;
        final Object this$deptName = this.getDeptName();
        final Object other$deptName = other.getDeptName();
        if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$statusText = this.getStatusText();
        final Object other$statusText = other.getStatusText();
        if (this$statusText == null ? other$statusText != null : !this$statusText.equals(other$statusText))
            return false;
        final Object this$timeInterval = this.getTimeInterval();
        final Object other$timeInterval = other.getTimeInterval();
        if (this$timeInterval == null ? other$timeInterval != null : !this$timeInterval.equals(other$timeInterval))
            return false;
        final Object this$intervalText = this.getIntervalText();
        final Object other$intervalText = other.getIntervalText();
        if (this$intervalText == null ? other$intervalText != null : !this$intervalText.equals(other$intervalText))
            return false;
        final Object this$period = this.getPeriod();
        final Object other$period = other.getPeriod();
        if (this$period == null ? other$period != null : !this$period.equals(other$period)) return false;
        final Object this$periodText = this.getPeriodText();
        final Object other$periodText = other.getPeriodText();
        if (this$periodText == null ? other$periodText != null : !this$periodText.equals(other$periodText))
            return false;
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
        return other instanceof ReserveVO;
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
        final Object $sex = this.getSex();
        result = result * PRIME + ($sex == null ? 43 : $sex.hashCode());
        final Object $birthday = this.getBirthday();
        result = result * PRIME + ($birthday == null ? 43 : $birthday.hashCode());
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $doctorName = this.getDoctorName();
        result = result * PRIME + ($doctorName == null ? 43 : $doctorName.hashCode());
        final Object $deptName = this.getDeptName();
        result = result * PRIME + ($deptName == null ? 43 : $deptName.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $statusText = this.getStatusText();
        result = result * PRIME + ($statusText == null ? 43 : $statusText.hashCode());
        final Object $timeInterval = this.getTimeInterval();
        result = result * PRIME + ($timeInterval == null ? 43 : $timeInterval.hashCode());
        final Object $intervalText = this.getIntervalText();
        result = result * PRIME + ($intervalText == null ? 43 : $intervalText.hashCode());
        final Object $period = this.getPeriod();
        result = result * PRIME + ($period == null ? 43 : $period.hashCode());
        final Object $periodText = this.getPeriodText();
        result = result * PRIME + ($periodText == null ? 43 : $periodText.hashCode());
        final Object $charge = this.getCharge();
        result = result * PRIME + ($charge == null ? 43 : $charge.hashCode());
        final Object $reserveDate = this.getReserveDate();
        result = result * PRIME + ($reserveDate == null ? 43 : $reserveDate.hashCode());
        return result;
    }
}
