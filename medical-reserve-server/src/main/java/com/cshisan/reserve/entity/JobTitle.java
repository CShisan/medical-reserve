package com.cshisan.reserve.entity;

import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CShisan
 * @date 2022-2-19 0:17
 */
@Data
public class JobTitle extends BaseEntity implements Serializable {
    /**
     * 职称ID
     */
    private Long titleId;

    /**
     * 职称名称
     */
    private String titleName;

    /**
     * 预约费用
     */
    private Integer charge;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof JobTitle)) return false;
        final JobTitle other = (JobTitle) o;
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
        return other instanceof JobTitle;
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

