package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuanbai
 * @date 2022/2/24 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnquiryVO extends BaseVO {
    private Long enquiryId;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private String deptName;

    private String remark;

}
