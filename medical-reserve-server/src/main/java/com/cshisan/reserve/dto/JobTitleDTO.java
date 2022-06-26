package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 13:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobTitleDTO extends BaseDTO {
    private Long titleId;

    private String titleName;

    private Integer charge;

}
