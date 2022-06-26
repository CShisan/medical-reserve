package com.cshisan.reserve.handler;

import cn.hutool.core.date.DateUtil;
import com.cshisan.reserve.common.utils.DateSupplyUtil;

import java.util.*;

/**
 * @author CShisan
 * @date 2022-5-6 22:45
 */
public class EveryDayScheduleHandler extends ScheduleHandler {
    protected EveryDayScheduleHandler(String[] team) {
        super(team);
    }

    /**
     * 每天排班规则
     *
     * @param team         上个月最后一个班次
     * @param lastWeekTeam 上次周日排班的班次
     * @return 排班表
     */
    @Override
    public Map<Date, List<String>> rotation(Date month, String team, String lastWeekTeam, List<Integer> dateList) {
        Map<Date, List<String>> schedule = new LinkedHashMap<>(64);
        int days = DateSupplyUtil.daysOfMonth(month);
        Date date = DateUtil.beginOfMonth(month);
        for (int i = 1; i <= days; i++) {
            Date iDate = DateUtil.offsetDay(date, i - 1);
            // 仅生成前端勾选的日期
            if (dateList.contains(i)) {
                schedule.put(iDate, new ArrayList<>(Arrays.asList(TEAM)));
            }
        }
        return schedule;
    }
}
