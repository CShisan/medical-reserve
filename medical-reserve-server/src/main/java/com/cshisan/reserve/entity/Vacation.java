package com.cshisan.reserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cshisan.reserve.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
* 
* @author yuanbai
* @date 2022/2/25 10:37
*/
@EqualsAndHashCode(callSuper = true)
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

}