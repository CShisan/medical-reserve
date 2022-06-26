package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 19:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorVO extends BaseVO {
    private Long doctorId;

    private String realName;

    private Long deptId;

    private String deptName;

    private Long jobTitleId;

    private String titleName;

    private Integer charge;

    private String phone;

    private String idCard;

    private String avatar;

    private String description;

}
