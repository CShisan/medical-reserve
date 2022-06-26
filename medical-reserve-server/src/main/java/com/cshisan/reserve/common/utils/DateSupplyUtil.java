package com.cshisan.reserve.common.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author yuanbai
 * @date 2022/2/25 13:57
 */
public class DateSupplyUtil {
    /**
     * 获取一个月天数
     *
     * @param date date
     * @return days
     */
    public static int daysOfMonth(Date date) {
        boolean isLeapYear = DateUtil.isLeapYear(DateUtil.year(date));
        return DateUtil.lengthOfMonth(DateUtil.month(date) + 1, isLeapYear);
    }

    /**
     * 获取相对date的上个月天数
     *
     * @param date date
     * @return days
     */
    public static int daysOfLastMonth(Date date) {
        return daysOfMonth(lastMonth(date));
    }

    /**
     * 获取相对date的上个月最后一天
     *
     * @param date date
     * @return date
     */
    public static Date endDayLastMonth(Date date) {
        return DateUtil.endOfMonth(lastMonth(date));
    }

    /**
     * 获取相对date的上个月第一天
     *
     * @param date date
     * @return date
     */
    public static Date beginDayLastMonth(Date date) {
        return DateUtil.beginOfMonth(lastMonth(date));
    }

    /**
     * 判断是否为周日
     *
     * @param date date
     * @return date
     */
    public static boolean isSunday(Date date) {
        int day = DateUtil.dayOfWeek(date);
        return day == 1;
    }

    /**
     * 相对date的上个月
     *
     * @param date date
     * @return date
     */
    public static Date lastMonth(Date date) {
        return DateUtil.offsetMonth(date, -1);
    }

    /**
     * 相对于date当天的12点
     *
     * @param date date
     * @return date12
     */
    public static Date date12(Date date) {
        return dateOfHour(date, 12);
    }

    /**
     * 相对于date当天的18点
     *
     * @param date date
     * @return date18
     */
    public static Date date18(Date date) {
        return dateOfHour(date, 18);
    }

    /**
     * 相对于date当天的某时
     *
     * @param date date
     * @param hour hour
     * @return dateOfHour
     */
    public static Date dateOfHour(Date date, int hour) {
        return DateUtil.offsetHour(DateUtil.beginOfDay(date), hour);
    }

    /**
     * 将Java date转化为数据库Date
     */
    public static java.sql.Date j2sDate(java.util.Date d) {
        if (null == d) {
            return null;
        }
        return new java.sql.Date(d.getTime());
    }

    /**
     * 将数据库Date转化为java date
     */
    public static java.util.Date s2jDate(java.sql.Date t) {
        if (null == t) {
            return null;
        }
        return new java.util.Date(t.getTime());
    }
}
