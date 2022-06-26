package com.cshisan.reserve.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CShisan
 * @date 2022-3-31 23:04
 */
@Getter
@AllArgsConstructor
public enum ReserveIntervalEnum {
    /**
     * 时段
     */
    ALL_DAY(0, "全天", 0),
    AM(1, "上午", 12),
    PM(2, "下午", 18);

    private final int code;
    private final String text;
    private final int threshold;

    public static ReserveIntervalEnum codeOf(int code) {
        for (ReserveIntervalEnum value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
