package com.cshisan.reserve.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果类
 *
 * @author CShisan
 * @date 2022-2-18 23:48
 **/
@Data
public class Result<T> implements Serializable {
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;
}
