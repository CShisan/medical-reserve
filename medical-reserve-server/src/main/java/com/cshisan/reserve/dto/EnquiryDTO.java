package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

/**
 * @author yuanbai
 * @date 2022/2/24 10:18
 */
@Data
public class EnquiryDTO extends BaseDTO {
    private Long enquiryId;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private String remark;

    private Long reserveId;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EnquiryDTO)) return false;
        final EnquiryDTO other = (EnquiryDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$enquiryId = this.getEnquiryId();
        final Object other$enquiryId = other.getEnquiryId();
        if (this$enquiryId == null ? other$enquiryId != null : !this$enquiryId.equals(other$enquiryId)) return false;
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
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) return false;
        final Object this$reserveId = this.getReserveId();
        final Object other$reserveId = other.getReserveId();
        if (this$reserveId == null ? other$reserveId != null : !this$reserveId.equals(other$reserveId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EnquiryDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $enquiryId = this.getEnquiryId();
        result = result * PRIME + ($enquiryId == null ? 43 : $enquiryId.hashCode());
        final Object $patientId = this.getPatientId();
        result = result * PRIME + ($patientId == null ? 43 : $patientId.hashCode());
        final Object $patientName = this.getPatientName();
        result = result * PRIME + ($patientName == null ? 43 : $patientName.hashCode());
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $doctorName = this.getDoctorName();
        result = result * PRIME + ($doctorName == null ? 43 : $doctorName.hashCode());
        final Object $remark = this.getRemark();
        result = result * PRIME + ($remark == null ? 43 : $remark.hashCode());
        final Object $reserveId = this.getReserveId();
        result = result * PRIME + ($reserveId == null ? 43 : $reserveId.hashCode());
        return result;
    }
}
