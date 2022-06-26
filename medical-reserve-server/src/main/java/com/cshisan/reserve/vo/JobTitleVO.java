package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-26 13:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobTitleVO extends BaseVO {
    private Long titleId;

    private String titleName;

    private Integer charge;

}
