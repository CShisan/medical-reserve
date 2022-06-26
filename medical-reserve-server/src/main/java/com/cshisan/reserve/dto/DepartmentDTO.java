package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

/**
 * @author yuanbai
 * @date 2022/2/24 15:28
 */
@Data
public class DepartmentDTO extends BaseDTO {
    private Long deptId;

    private String deptName;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DepartmentDTO)) return false;
        final DepartmentDTO other = (DepartmentDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$deptId = this.getDeptId();
        final Object other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !this$deptId.equals(other$deptId)) return false;
        final Object this$deptName = this.getDeptName();
        final Object other$deptName = other.getDeptName();
        if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DepartmentDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $deptId = this.getDeptId();
        result = result * PRIME + ($deptId == null ? 43 : $deptId.hashCode());
        final Object $deptName = this.getDeptName();
        result = result * PRIME + ($deptName == null ? 43 : $deptName.hashCode());
        return result;
    }
}
