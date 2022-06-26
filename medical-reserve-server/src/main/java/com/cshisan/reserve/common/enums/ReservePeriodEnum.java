package com.cshisan.reserve.common.enums;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

/**
 * @author CShisan
 * @date 2022-3-31 22:15
 */
@Getter
@AllArgsConstructor
public enum ReservePeriodEnum {
    /**
     * 时段
     */
    AM_EIGHT(1, 1, "8:00-8:29"),
    AM_EIGHT_THIRTY(1, 2, "8:30-8:59"),
    AM_NINE(1, 3, "9:00-9:29"),
    AM_NINE_THIRTY(1, 4, "9:30-9:59"),
    AM_TEN(1, 5, "10:00-10:29"),
    AM_TEN_THIRTY(1, 6, "10:30-10:59"),
    AM_ELEVEN(1, 7, "11:00-11:29"),
    AM_ELEVEN_THIRTY(1, 8, "11:30-11:59"),

    PM_EIGHT(2, 1, "14:00-14:29"),
    PM_EIGHT_THIRTY(2, 2, "14:30-14:59"),
    PM_NINE(2, 3, "15:00-15:29"),
    PM_NINE_THIRTY(2, 4, "15:30-15:59"),
    PM_TEN(2, 5, "16:00-16:29"),
    PM_TEN_THIRTY(2, 6, "16:30-16:59"),
    PM_ELEVEN(2, 7, "17:00-17:29"),
    PM_ELEVEN_THIRTY(2, 8, "17:30-17:59");

    private final int interval;
    private final int period;
    private final String text;

    public static ReservePeriodEnum codeOf(int interval, int period) {
        for (ReservePeriodEnum value : values()) {
            boolean equalsInterval = Objects.equals(interval, value.interval);
            boolean equalsPeriod = Objects.equals(period, value.period);
            if (equalsInterval && equalsPeriod) {
                return value;
            }
        }
        return null;
    }

    public static int calcPeriodNumBy(int interval) {
        int num = 0;
        for (ReservePeriodEnum value : values()) {
            num = Objects.equals(interval, value.interval) ? num + 1 : num;
        }
        return num;
    }

    /**
     * 当前时间是否已经超过date的时段的临界值
     *
     * @param date     date
     * @param interval interval
     * @param period period
     * @return status
     */
    public static boolean isExpired(Date date, int interval, int period) {
        Date today = DateUtil.beginOfDay(date);
        int threshold = Objects.equals(interval, 1) ? 8 : 14;
        Date thresholdTime = DateUtil.offsetHour(today, threshold);
        thresholdTime = DateUtil.offsetMinute(thresholdTime, period * 30);
        return thresholdTime.before(new Date());
    }
}
