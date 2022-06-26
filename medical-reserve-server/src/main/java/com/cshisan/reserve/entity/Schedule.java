package com.cshisan.reserve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cshisan.reserve.common.base.BaseEntity;
import lombok.*;
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
@EqualsAndHashCode(callSuper = true)
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

}