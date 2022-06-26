package com.cshisan.reserve.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/21 15:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDTO extends BasePage{
    private Long id;
    private Boolean delFlag;
    private Long editor;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
