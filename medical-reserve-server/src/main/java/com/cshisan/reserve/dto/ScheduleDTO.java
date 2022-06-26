package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-25 20:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScheduleDTO extends BaseDTO {
    private Long doctorId;

    private Long scheduleId;

    private Date scheduleDate;

    private Long rotationId;

    private String rotationKey;

    private String rule;

    private List<Integer> dateList;

}
