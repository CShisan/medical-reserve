package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuanbai
 * @date 2022/2/24 10:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnquiryDTO extends BaseDTO {
    private Long enquiryId;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private String remark;

    private Long reserveId;

}
