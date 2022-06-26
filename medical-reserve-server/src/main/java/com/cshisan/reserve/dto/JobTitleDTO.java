package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;

/**
 * @author CShisan
 * @date 2022-2-26 13:03
 */
@Data
public class JobTitleDTO extends BaseDTO {
    private Long titleId;

    private String titleName;

    private Integer charge;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof JobTitleDTO)) return false;
        final JobTitleDTO other = (JobTitleDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$titleId = this.getTitleId();
        final Object other$titleId = other.getTitleId();
        if (this$titleId == null ? other$titleId != null : !this$titleId.equals(other$titleId)) return false;
        final Object this$titleName = this.getTitleName();
        final Object other$titleName = other.getTitleName();
        if (this$titleName == null ? other$titleName != null : !this$titleName.equals(other$titleName)) return false;
        final Object this$charge = this.getCharge();
        final Object other$charge = other.getCharge();
        if (this$charge == null ? other$charge != null : !this$charge.equals(other$charge)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof JobTitleDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $titleId = this.getTitleId();
        result = result * PRIME + ($titleId == null ? 43 : $titleId.hashCode());
        final Object $titleName = this.getTitleName();
        result = result * PRIME + ($titleName == null ? 43 : $titleName.hashCode());
        final Object $charge = this.getCharge();
        result = result * PRIME + ($charge == null ? 43 : $charge.hashCode());
        return result;
    }
}
