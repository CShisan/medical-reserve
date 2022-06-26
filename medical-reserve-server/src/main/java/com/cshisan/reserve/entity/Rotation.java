package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
* 
* @author yuanbai
* @date 2022/2/25 10:36
*/
@Data
public class Rotation extends BaseEntity implements Serializable {
    /**
    * 班次ID
    */
    private Long rotationId;

    /**
    * 班次KEY
    */
    private String rotationKey;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Rotation)) return false;
        final Rotation other = (Rotation) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$rotationId = this.getRotationId();
        final Object other$rotationId = other.getRotationId();
        if (this$rotationId == null ? other$rotationId != null : !this$rotationId.equals(other$rotationId))
            return false;
        final Object this$rotationKey = this.getRotationKey();
        final Object other$rotationKey = other.getRotationKey();
        if (this$rotationKey == null ? other$rotationKey != null : !this$rotationKey.equals(other$rotationKey))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Rotation;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $rotationId = this.getRotationId();
        result = result * PRIME + ($rotationId == null ? 43 : $rotationId.hashCode());
        final Object $rotationKey = this.getRotationKey();
        result = result * PRIME + ($rotationKey == null ? 43 : $rotationKey.hashCode());
        return result;
    }
}