package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author CShisan
 * @date 2022-3-4 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorScheduleVO  extends BaseVO {
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date date;
    private Integer interval;
    private Integer period;
    private String periodText;
    private String week;
    private Integer count;
    private Boolean disable;
}
