package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

/**
 * @author CShisan
 * @date 2022-2-26 19:43
 */
@Data
public class DoctorDTO extends BaseDTO {
    private Long doctorId;

    private String phone;

    private String password;

    private String realName;

    private String idCard;

    private Long deptId;

    private Long jobTitleId;

    private String description;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DoctorDTO)) return false;
        final DoctorDTO other = (DoctorDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$realName = this.getRealName();
        final Object other$realName = other.getRealName();
        if (this$realName == null ? other$realName != null : !this$realName.equals(other$realName)) return false;
        final Object this$idCard = this.getIdCard();
        final Object other$idCard = other.getIdCard();
        if (this$idCard == null ? other$idCard != null : !this$idCard.equals(other$idCard)) return false;
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
        return other instanceof DoctorDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $realName = this.getRealName();
        result = result * PRIME + ($realName == null ? 43 : $realName.hashCode());
        final Object $idCard = this.getIdCard();
        result = result * PRIME + ($idCard == null ? 43 : $idCard.hashCode());
        final Object $deptId = this.getDeptId();
        result = result * PRIME + ($deptId == null ? 43 : $deptId.hashCode());
        final Object $jobTitleId = this.getJobTitleId();
        result = result * PRIME + ($jobTitleId == null ? 43 : $jobTitleId.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}
