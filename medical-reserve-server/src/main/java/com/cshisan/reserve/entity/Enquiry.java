package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
public class Enquiry extends BaseEntity implements Serializable {
    /**
     * 就诊记录ID
     */
    private Long enquiryId;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 医生ID
     */
    private Long doctorId;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Enquiry)) return false;
        final Enquiry other = (Enquiry) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$enquiryId = this.getEnquiryId();
        final Object other$enquiryId = other.getEnquiryId();
        if (this$enquiryId == null ? other$enquiryId != null : !this$enquiryId.equals(other$enquiryId)) return false;
        final Object this$patientId = this.getPatientId();
        final Object other$patientId = other.getPatientId();
        if (this$patientId == null ? other$patientId != null : !this$patientId.equals(other$patientId)) return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Enquiry;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $enquiryId = this.getEnquiryId();
        result = result * PRIME + ($enquiryId == null ? 43 : $enquiryId.hashCode());
        final Object $patientId = this.getPatientId();
        result = result * PRIME + ($patientId == null ? 43 : $patientId.hashCode());
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $remark = this.getRemark();
        result = result * PRIME + ($remark == null ? 43 : $remark.hashCode());
        return result;
    }
}

