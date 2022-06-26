package com.cshisan.reserve.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CShisan
 * @date 2022-4-1 0:42
 */
@Getter
@AllArgsConstructor
public enum ReserveStatusEnum {
    /**
     * 预约状态
     */
    CANCEL(0, "已取消"),
    PENDING(1, "待报到"),
    DONE(2, "已完成");


    private final int code;
    private final String value;

    public static ReserveStatusEnum codeOf(int code) {
        for (ReserveStatusEnum value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
