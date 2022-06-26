package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 13:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RotationVO extends BaseVO {
    private Long rotationId;

    private String rotationKey;

}
