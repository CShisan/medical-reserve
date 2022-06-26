package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-3-4 23:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorScheduleDTO extends BaseDTO {
    private Long doctorId;

}
