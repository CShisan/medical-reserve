package com.cshisan.reserve.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CShisan
 * @date 2022-4-1 0:13
 */
@Getter
@AllArgsConstructor
public enum VacationStatusEnum {
    /**
     * 调休状态
     */
    NOT_ALLOWED(0, "未通过"),
    PENDING(1, "待审批"),
    ALLOWED(2, "已通过");


    private final int code;
    private final String value;

    public static VacationStatusEnum codeOf(int code) {
        for (VacationStatusEnum value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
