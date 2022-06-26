package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author CShisan
 * @date 2022-2-27 3:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VacationDTO extends BaseDTO {
    private Long vacationId;

    private Long doctorId;

    private String realName;

    private Long deptId;

    private Date vacationDate;

    private Integer timeInterval;

    private Integer status;

    private String description;

}
