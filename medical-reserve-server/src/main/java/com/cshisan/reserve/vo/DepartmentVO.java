package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/24 15:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentVO extends BaseVO {
    private Long deptId;

    private String deptName;

    private List<DoctorVO> doctors;

}
