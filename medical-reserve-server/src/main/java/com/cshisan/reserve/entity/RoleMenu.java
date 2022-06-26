package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
public class RoleMenu extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RoleMenu)) return false;
        final RoleMenu other = (RoleMenu) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$roleId = this.getRoleId();
        final Object other$roleId = other.getRoleId();
        if (this$roleId == null ? other$roleId != null : !this$roleId.equals(other$roleId)) return false;
        final Object this$menuId = this.getMenuId();
        final Object other$menuId = other.getMenuId();
        if (this$menuId == null ? other$menuId != null : !this$menuId.equals(other$menuId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RoleMenu;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $roleId = this.getRoleId();
        result = result * PRIME + ($roleId == null ? 43 : $roleId.hashCode());
        final Object $menuId = this.getMenuId();
        result = result * PRIME + ($menuId == null ? 43 : $menuId.hashCode());
        return result;
    }
}

