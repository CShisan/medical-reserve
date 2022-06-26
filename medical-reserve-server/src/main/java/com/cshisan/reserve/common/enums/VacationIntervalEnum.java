package com.cshisan.reserve.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CShisan
 * @date 2022-4-1 0:19
 */
@Getter
@AllArgsConstructor
public enum VacationIntervalEnum {
    /**
     * 时段
     */
    ALL_DAY(0, "全天"),
    AM(1, "上午"),
    PM(2, "下午");

    private final int code;
    private final String value;

    public static VacationIntervalEnum codeOf(int code) {
        for (VacationIntervalEnum value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
