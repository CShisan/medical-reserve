package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-25 20:03
 */
@Data
public class ScheduleDTO extends BaseDTO {
    private Long doctorId;

    private Long scheduleId;

    private Date scheduleDate;

    private Long rotationId;

    private String rotationKey;

    private String rule;

    private List<Integer> dateList;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ScheduleDTO)) return false;
        final ScheduleDTO other = (ScheduleDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$scheduleId = this.getScheduleId();
        final Object other$scheduleId = other.getScheduleId();
        if (this$scheduleId == null ? other$scheduleId != null : !this$scheduleId.equals(other$scheduleId))
            return false;
        final Object this$scheduleDate = this.getScheduleDate();
        final Object other$scheduleDate = other.getScheduleDate();
        if (this$scheduleDate == null ? other$scheduleDate != null : !this$scheduleDate.equals(other$scheduleDate))
            return false;
        final Object this$rotationId = this.getRotationId();
        final Object other$rotationId = other.getRotationId();
        if (this$rotationId == null ? other$rotationId != null : !this$rotationId.equals(other$rotationId))
            return false;
        final Object this$rotationKey = this.getRotationKey();
        final Object other$rotationKey = other.getRotationKey();
        if (this$rotationKey == null ? other$rotationKey != null : !this$rotationKey.equals(other$rotationKey))
            return false;
        final Object this$rule = this.getRule();
        final Object other$rule = other.getRule();
        if (this$rule == null ? other$rule != null : !this$rule.equals(other$rule)) return false;
        final Object this$dateList = this.getDateList();
        final Object other$dateList = other.getDateList();
        if (this$dateList == null ? other$dateList != null : !this$dateList.equals(other$dateList)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ScheduleDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $scheduleId = this.getScheduleId();
        result = result * PRIME + ($scheduleId == null ? 43 : $scheduleId.hashCode());
        final Object $scheduleDate = this.getScheduleDate();
        result = result * PRIME + ($scheduleDate == null ? 43 : $scheduleDate.hashCode());
        final Object $rotationId = this.getRotationId();
        result = result * PRIME + ($rotationId == null ? 43 : $rotationId.hashCode());
        final Object $rotationKey = this.getRotationKey();
        result = result * PRIME + ($rotationKey == null ? 43 : $rotationKey.hashCode());
        final Object $rule = this.getRule();
        result = result * PRIME + ($rule == null ? 43 : $rule.hashCode());
        final Object $dateList = this.getDateList();
        result = result * PRIME + ($dateList == null ? 43 : $dateList.hashCode());
        return result;
    }
}
