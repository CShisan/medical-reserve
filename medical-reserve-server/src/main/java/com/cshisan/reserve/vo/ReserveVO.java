package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/24 10:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReserveVO extends BaseVO {
    private Long reserveId;

    private Long patientId;

    private String patientName;

    private Integer sex;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    private Long doctorId;

    private String doctorName;

    private String deptName;

    private Integer status;

    private String statusText;

    private Integer timeInterval;

    private String intervalText;

    private Integer period;

    private String periodText;

    private Integer charge;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reserveDate;

}
