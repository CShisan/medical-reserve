package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 13:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RotationDTO extends BaseDTO {
    private Long rotationId;

    private String rotationKey;

}
