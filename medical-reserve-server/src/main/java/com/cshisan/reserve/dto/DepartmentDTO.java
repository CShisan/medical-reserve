package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuanbai
 * @date 2022/2/24 15:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentDTO extends BaseDTO {
    private Long deptId;

    private String deptName;

}
