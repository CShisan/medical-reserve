package com.cshisan.reserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cshisan.reserve.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
*
* @author yuanbai
* @date 2022/2/25 10:37
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends BaseEntity implements Serializable {
    // 持久层用了@Builder注解要加上无参和全参构造注解,不然会报错

    /**
    * 排班记录ID
    */
    private Long scheduleId;

    /**
    * 排班日期
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date scheduleDate;

    /**
    * 班次ID
    */
    private Long rotationId;

    /**
     * 班次KEY
     */
    @TableField(exist = false)
    private String rotationKey;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Schedule)) return false;
        final Schedule other = (Schedule) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$scheduleId = this.getScheduleId();
        final Object other$scheduleId = other.getScheduleId();
        if (this$scheduleId == null ? other$scheduleId != null : !this$scheduleId.equals(other$scheduleId))
            return false;
        final Object this$scheduleDate = this.getScheduleDate();
        final Object other$scheduleDate = other.getScheduleDate();
        if (this$scheduleDate == null ? other$scheduleDate != null : !this$scheduleDate.equals(other$scheduleDate))
            return false;
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
        return other instanceof Schedule;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $scheduleId = this.getScheduleId();
        result = result * PRIME + ($scheduleId == null ? 43 : $scheduleId.hashCode());
        final Object $scheduleDate = this.getScheduleDate();
        result = result * PRIME + ($scheduleDate == null ? 43 : $scheduleDate.hashCode());
        final Object $rotationId = this.getRotationId();
        result = result * PRIME + ($rotationId == null ? 43 : $rotationId.hashCode());
        final Object $rotationKey = this.getRotationKey();
        result = result * PRIME + ($rotationKey == null ? 43 : $rotationKey.hashCode());
        return result;
    }
}