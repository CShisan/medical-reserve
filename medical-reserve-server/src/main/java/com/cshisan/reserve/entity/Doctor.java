package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Doctor)) return false;
        final Doctor other = (Doctor) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$deptId = this.getDeptId();
        final Object other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !this$deptId.equals(other$deptId)) return false;
        final Object this$jobTitleId = this.getJobTitleId();
        final Object other$jobTitleId = other.getJobTitleId();
        if (this$jobTitleId == null ? other$jobTitleId != null : !this$jobTitleId.equals(other$jobTitleId))
            return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Doctor;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $deptId = this.getDeptId();
        result = result * PRIME + ($deptId == null ? 43 : $deptId.hashCode());
        final Object $jobTitleId = this.getJobTitleId();
        result = result * PRIME + ($jobTitleId == null ? 43 : $jobTitleId.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}

