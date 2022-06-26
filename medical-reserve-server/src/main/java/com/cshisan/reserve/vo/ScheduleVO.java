package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/25 16:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScheduleVO extends BaseVO {
    private Long scheduleId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date scheduleDate;

    private Long rotationId;

    private String rotationKey;

}

