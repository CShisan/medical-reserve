package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 14:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRotationVO extends BaseVO {
    private Long doctorId;

    private String doctorName;

    private String phone;

    private Long rotationId;

    private Long deptId;

}
