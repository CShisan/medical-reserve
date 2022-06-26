package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/24 10:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReserveDTO extends BaseDTO {
    private Long reserveId;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private Integer status;

    private Integer timeInterval;

    private Integer period;

    private Integer charge;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reserveDate;

}
