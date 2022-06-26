package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

/**
 * @author yuanbai
 * @date 2022/2/24 9:07
 */
@Data
public class RoleDTO extends BaseDTO {
    private Long roleId;

    private String roleKey;

    private String roleName;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RoleDTO)) return false;
        final RoleDTO other = (RoleDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$roleId = this.getRoleId();
        final Object other$roleId = other.getRoleId();
        if (this$roleId == null ? other$roleId != null : !this$roleId.equals(other$roleId)) return false;
        final Object this$roleKey = this.getRoleKey();
        final Object other$roleKey = other.getRoleKey();
        if (this$roleKey == null ? other$roleKey != null : !this$roleKey.equals(other$roleKey)) return false;
        final Object this$roleName = this.getRoleName();
        final Object other$roleName = other.getRoleName();
        if (this$roleName == null ? other$roleName != null : !this$roleName.equals(other$roleName)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RoleDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $roleId = this.getRoleId();
        result = result * PRIME + ($roleId == null ? 43 : $roleId.hashCode());
        final Object $roleKey = this.getRoleKey();
        result = result * PRIME + ($roleKey == null ? 43 : $roleKey.hashCode());
        final Object $roleName = this.getRoleName();
        result = result * PRIME + ($roleName == null ? 43 : $roleName.hashCode());
        return result;
    }
}
