package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
public class Role extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色KEY
     */
    private String roleKey;

    /**
     * 角色名称
     */
    private String roleName;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Role)) return false;
        final Role other = (Role) o;
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
        return other instanceof Role;
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

