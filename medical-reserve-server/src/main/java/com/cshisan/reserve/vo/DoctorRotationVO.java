package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;

/**
 * @author CShisan
 * @date 2022-2-26 14:46
 */
@Data
public class DoctorRotationVO extends BaseVO {
    private Long doctorId;

    private String doctorName;

    private String phone;

    private Long rotationId;

    private Long deptId;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DoctorRotationVO)) return false;
        final DoctorRotationVO other = (DoctorRotationVO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$doctorName = this.getDoctorName();
        final Object other$doctorName = other.getDoctorName();
        if (this$doctorName == null ? other$doctorName != null : !this$doctorName.equals(other$doctorName))
            return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$rotationId = this.getRotationId();
        final Object other$rotationId = other.getRotationId();
        if (this$rotationId == null ? other$rotationId != null : !this$rotationId.equals(other$rotationId))
            return false;
        final Object this$deptId = this.getDeptId();
        final Object other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !this$deptId.equals(other$deptId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DoctorRotationVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $doctorName = this.getDoctorName();
        result = result * PRIME + ($doctorName == null ? 43 : $doctorName.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $rotationId = this.getRotationId();
        result = result * PRIME + ($rotationId == null ? 43 : $rotationId.hashCode());
        final Object $deptId = this.getDeptId();
        result = result * PRIME + ($deptId == null ? 43 : $deptId.hashCode());
        return result;
    }
}
