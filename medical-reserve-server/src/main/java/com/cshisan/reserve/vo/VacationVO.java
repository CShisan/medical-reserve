package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author CShisan
 * @date 2022-2-27 3:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VacationVO extends BaseVO {
    private Long vacationId;

    private Long doctorId;

    private String realName;

    private String deptName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date vacationDate;

    private Integer timeInterval;

    private Integer status;

    private String description;

}
