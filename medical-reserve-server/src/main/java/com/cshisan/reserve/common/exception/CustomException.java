package com.cshisan.reserve.common.exception;

import com.cshisan.reserve.common.enums.CodeEnum;

import java.util.Objects;

/**
 * 自定义异常
 *
 * @author CShisan
 * @date 2022-2-19 0:17
 */
public class CustomException extends RuntimeException {
    private CodeEnum codeEnum;
    private Integer code;

    public CustomException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CodeEnum getCodeEnum() {
        return codeEnum;
    }

    public Integer getCode() {
        return Objects.isNull(code) ? codeEnum.getCode() : code;
    }

    public String getMsg() {
        return codeEnum.getMessage();
    }
}
