package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 16:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRotationDTO extends BaseDTO {
    private Long doctorId;

    private Long rotationId;

    private Long deptId;

}
