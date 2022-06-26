package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 19:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorDTO extends BaseDTO {
    private Long doctorId;

    private String phone;

    private String password;

    private String realName;

    private String idCard;

    private Long deptId;

    private Long jobTitleId;

    private String description;

}
