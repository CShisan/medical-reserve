package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;

import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/24 15:27
 */
@Data
public class DepartmentVO extends BaseVO {
    private Long deptId;

    private String deptName;

    private List<DoctorVO> doctors;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DepartmentVO)) return false;
        final DepartmentVO other = (DepartmentVO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$deptId = this.getDeptId();
        final Object other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !this$deptId.equals(other$deptId)) return false;
        final Object this$deptName = this.getDeptName();
        final Object other$deptName = other.getDeptName();
        if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) return false;
        final Object this$doctors = this.getDoctors();
        final Object other$doctors = other.getDoctors();
        if (this$doctors == null ? other$doctors != null : !this$doctors.equals(other$doctors)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DepartmentVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $deptId = this.getDeptId();
        result = result * PRIME + ($deptId == null ? 43 : $deptId.hashCode());
        final Object $deptName = this.getDeptName();
        result = result * PRIME + ($deptName == null ? 43 : $deptName.hashCode());
        final Object $doctors = this.getDoctors();
        result = result * PRIME + ($doctors == null ? 43 : $doctors.hashCode());
        return result;
    }
}
