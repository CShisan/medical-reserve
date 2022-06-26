package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
public class UserRole extends BaseEntity implements Serializable {
    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 角色ID
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserRole)) return false;
        final UserRole other = (UserRole) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$roleId = this.getRoleId();
        final Object other$roleId = other.getRoleId();
        if (this$roleId == null ? other$roleId != null : !this$roleId.equals(other$roleId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserRole;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $roleId = this.getRoleId();
        result = result * PRIME + ($roleId == null ? 43 : $roleId.hashCode());
        return result;
    }
}

