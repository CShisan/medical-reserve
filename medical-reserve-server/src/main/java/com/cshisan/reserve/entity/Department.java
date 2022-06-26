package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
public class Department extends BaseEntity implements Serializable {
    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Department)) return false;
        final Department other = (Department) o;
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
        return other instanceof Department;
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
