package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

/**
 * @author CShisan
 * @date 2022-2-26 13:57
 */
@Data
public class RotationDTO extends BaseDTO {
    private Long rotationId;

    private String rotationKey;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RotationDTO)) return false;
        final RotationDTO other = (RotationDTO) o;
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
        return other instanceof RotationDTO;
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
