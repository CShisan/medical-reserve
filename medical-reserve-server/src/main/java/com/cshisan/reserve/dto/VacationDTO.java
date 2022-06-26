package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author CShisan
 * @date 2022-2-27 3:27
 */
@Data
public class VacationDTO extends BaseDTO {
    private Long vacationId;

    private Long doctorId;

    private String realName;

    private Long deptId;

    private Date vacationDate;

    private Integer timeInterval;

    private Integer status;

    private String description;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof VacationDTO)) return false;
        final VacationDTO other = (VacationDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$vacationId = this.getVacationId();
        final Object other$vacationId = other.getVacationId();
        if (this$vacationId == null ? other$vacationId != null : !this$vacationId.equals(other$vacationId))
            return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$realName = this.getRealName();
        final Object other$realName = other.getRealName();
        if (this$realName == null ? other$realName != null : !this$realName.equals(other$realName)) return false;
        final Object this$deptId = this.getDeptId();
        final Object other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !this$deptId.equals(other$deptId)) return false;
        final Object this$vacationDate = this.getVacationDate();
        final Object other$vacationDate = other.getVacationDate();
        if (this$vacationDate == null ? other$vacationDate != null : !this$vacationDate.equals(other$vacationDate))
            return false;
        final Object this$timeInterval = this.getTimeInterval();
        final Object other$timeInterval = other.getTimeInterval();
        if (this$timeInterval == null ? other$timeInterval != null : !this$timeInterval.equals(other$timeInterval))
            return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof VacationDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $vacationId = this.getVacationId();
        result = result * PRIME + ($vacationId == null ? 43 : $vacationId.hashCode());
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $realName = this.getRealName();
        result = result * PRIME + ($realName == null ? 43 : $realName.hashCode());
        final Object $deptId = this.getDeptId();
        result = result * PRIME + ($deptId == null ? 43 : $deptId.hashCode());
        final Object $vacationDate = this.getVacationDate();
        result = result * PRIME + ($vacationDate == null ? 43 : $vacationDate.hashCode());
        final Object $timeInterval = this.getTimeInterval();
        result = result * PRIME + ($timeInterval == null ? 43 : $timeInterval.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}
