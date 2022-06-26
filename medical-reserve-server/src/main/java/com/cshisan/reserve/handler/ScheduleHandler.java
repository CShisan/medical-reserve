package com.cshisan.reserve.handler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-24 20:56
 */
public abstract class ScheduleHandler {
    protected final String[] TEAM;

    protected ScheduleHandler(String[] team) {
        this.TEAM = team;
    }

    /**
     * 根据传入类型自动创建对应排班算法
     *
     * @param team 班次
     * @param rule 排班规则
     * @return 对应规则Handler
     */
    public static ScheduleHandler getScheduleHandler(String[] team, String rule) {
        ScheduleHandler scheduleHandler = null;
        switch (rule) {
            case "oodDays":
                scheduleHandler = new OodDaysScheduleHandler(team);
                break;
            case "everyDay":
                scheduleHandler = new EveryDayScheduleHandler(team);
                break;
            default:
        }
        return scheduleHandler;
    }

    /**
     * 自动排班
     *
     * @param month month
     * @param team 上个月最后一个班次
     * @param lastWeekTeam 上个月最后周日班次
     * @param dateList 需要排班的日期
     * @return 排班表
     */
    public abstract Map<Date, List<String>> rotation(Date month, String team, String lastWeekTeam, List<Integer> dateList);

    protected int getPoint(String team) {
        return Arrays.asList(TEAM).indexOf(team) + 1;
    }
}
