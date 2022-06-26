package com.cshisan.reserve.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author CShisan
 * @date 2022-2-20 0:10
 *
 * {@link com.cshisan.reserve.common.base.BaseEntity}
 */
@Data
public class BaseVO {
    private Long id;
    private Boolean delFlag;
    private Long editor;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
