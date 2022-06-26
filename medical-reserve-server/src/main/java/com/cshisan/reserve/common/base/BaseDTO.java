package com.cshisan.reserve.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/21 15:43
 */
@Data
public class BaseDTO extends BasePage{
    private Long id;
    private Boolean delFlag;
    private Long editor;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseDTO)) return false;
        final BaseDTO other = (BaseDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$delFlag = this.getDelFlag();
        final Object other$delFlag = other.getDelFlag();
        if (this$delFlag == null ? other$delFlag != null : !this$delFlag.equals(other$delFlag)) return false;
        final Object this$editor = this.getEditor();
        final Object other$editor = other.getEditor();
        if (this$editor == null ? other$editor != null : !this$editor.equals(other$editor)) return false;
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !this$createTime.equals(other$createTime))
            return false;
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        if (this$updateTime == null ? other$updateTime != null : !this$updateTime.equals(other$updateTime))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $delFlag = this.getDelFlag();
        result = result * PRIME + ($delFlag == null ? 43 : $delFlag.hashCode());
        final Object $editor = this.getEditor();
        result = result * PRIME + ($editor == null ? 43 : $editor.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * PRIME + ($createTime == null ? 43 : $createTime.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * PRIME + ($updateTime == null ? 43 : $updateTime.hashCode());
        return result;
    }
}
