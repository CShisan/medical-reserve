package com.cshisan.reserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
* 
* @author yuanbai
* @date 2022/2/25 10:37
*/
@Data
public class Vacation extends BaseEntity implements Serializable {
    /**
    * 调休记录ID
    */
    private Long vacationId;

    /**
    * 医生ID
    */
    private Long doctorId;

    /**
    * 调休日期
    */
    @TableField(jdbcType = JdbcType.DATE)
    private Date vacationDate;

    /**
    * 调休状态  0，未通过  1，待审批  2，已通过
    */
    private Integer status;

    /**
     * 时段  0，全天  1，上午  2，下午
     */
    private Integer timeInterval;

    /**
     * 调休备注
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Vacation)) return false;
        final Vacation other = (Vacation) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$vacationId = this.getVacationId();
        final Object other$vacationId = other.getVacationId();
        if (this$vacationId == null ? other$vacationId != null : !this$vacationId.equals(other$vacationId))
            return false;
        final Object this$doctorId = this.getDoctorId();
        final Object other$doctorId = other.getDoctorId();
        if (this$doctorId == null ? other$doctorId != null : !this$doctorId.equals(other$doctorId)) return false;
        final Object this$vacationDate = this.getVacationDate();
        final Object other$vacationDate = other.getVacationDate();
        if (this$vacationDate == null ? other$vacationDate != null : !this$vacationDate.equals(other$vacationDate))
            return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$timeInterval = this.getTimeInterval();
        final Object other$timeInterval = other.getTimeInterval();
        if (this$timeInterval == null ? other$timeInterval != null : !this$timeInterval.equals(other$timeInterval))
            return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Vacation;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $vacationId = this.getVacationId();
        result = result * PRIME + ($vacationId == null ? 43 : $vacationId.hashCode());
        final Object $doctorId = this.getDoctorId();
        result = result * PRIME + ($doctorId == null ? 43 : $doctorId.hashCode());
        final Object $vacationDate = this.getVacationDate();
        result = result * PRIME + ($vacationDate == null ? 43 : $vacationDate.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $timeInterval = this.getTimeInterval();
        result = result * PRIME + ($timeInterval == null ? 43 : $timeInterval.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}