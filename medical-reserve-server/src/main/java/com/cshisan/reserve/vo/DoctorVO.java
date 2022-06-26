package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;

/**
 * @author CShisan
 * @date 2022-2-26 19:34
 */
@Data
public class DoctorVO extends BaseVO {
    private Long doctorId;

    private String realName;

    private Long deptId;

    private String deptName;

    private Long jobTitleId;

    private String titleName;

    private Integer charge;

    private String phone;

    private String idCard;

    private String avatar;

    private String description;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DoctorVO)) return false;
        final DoctorVO other = (DoctorVO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$realName = this.getRealName();
        final Object other$realName = other.getRealName();
        if (this$realName == null ? other$realName != null : !this$realName.equals(other$realName)) return false;
        final Object this$deptId = this.getDeptId();
        final Object other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !this$deptId.equals(other$deptId)) return false;
        final Object this$deptName = this.getDeptName();
        final Object other$deptName = other.getDeptName();
        if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) return false;
        final Object this$jobTitleId = this.getJobTitleId();
        final Object other$jobTitleId = other.getJobTitleId();
        if (this$jobTitleId == null ? other$jobTitleId != null : !this$jobTitleId.equals(other$jobTitleId))
            return false;
        final Object this$titleName = this.getTitleName();
        final Object other$titleName = other.getTitleName();
        if (this$titleName == null ? other$titleName != null : !this$titleName.equals(other$titleName)) return false;
        final Object this$charge = this.getCharge();
        final Object other$charge = other.getCharge();
        if (this$charge == null ? other$charge != null : !this$charge.equals(other$charge)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$idCard = this.getIdCard();
        final Object other$idCard = other.getIdCard();
        if (this$idCard == null ? other$idCard != null : !this$idCard.equals(other$idCard)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        if (this$avatar == null ? other$avatar != null : !this$avatar.equals(other$avatar)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DoctorVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $realName = this.getRealName();
        result = result * PRIME + ($realName == null ? 43 : $realName.hashCode());
        final Object $deptId = this.getDeptId();
        result = result * PRIME + ($deptId == null ? 43 : $deptId.hashCode());
        final Object $deptName = this.getDeptName();
        result = result * PRIME + ($deptName == null ? 43 : $deptName.hashCode());
        final Object $jobTitleId = this.getJobTitleId();
        result = result * PRIME + ($jobTitleId == null ? 43 : $jobTitleId.hashCode());
        final Object $titleName = this.getTitleName();
        result = result * PRIME + ($titleName == null ? 43 : $titleName.hashCode());
        final Object $charge = this.getCharge();
        result = result * PRIME + ($charge == null ? 43 : $charge.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $idCard = this.getIdCard();
        result = result * PRIME + ($idCard == null ? 43 : $idCard.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}
