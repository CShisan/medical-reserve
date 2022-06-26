package com.cshisan.reserve.handler;

import cn.hutool.core.date.DateUtil;
import com.cshisan.reserve.common.utils.DateSupplyUtil;

import java.util.*;

/**
 * @author CShisan
 * @date 2022-2-24 21:01
 */
public class OodDaysScheduleHandler extends ScheduleHandler {
    public OodDaysScheduleHandler(String[] team) {
        super(team);
    }

    /**
     * 单天排班规则
     *
     * @param team         上个月最后一个班次
     * @param lastWeekTeam 上次周日排班的班次
     * @return 排班表
     */
    @Override
    public Map<Date, List<String>> rotation(Date month, String team, String lastWeekTeam, List<Integer> dateList) {
        Map<Date, List<String>> schedule = new LinkedHashMap<>(64);
        // 从上个月最后一班+1开始排班
        int start = getPoint(team) % TEAM.length;
        int days = DateSupplyUtil.daysOfMonth(month);
        Date date = DateUtil.beginOfMonth(month);
        for (int i = 1; i <= days; i++) {
            Date iDate = DateUtil.offsetDay(date, i - 1);
            // 仅生成前端勾选的日期
            if (dateList.contains(i)) {
                // 周日轮流值班
                if (DateUtil.dayOfWeek(iDate) == 1) {
                    int index = Arrays.asList(TEAM).indexOf(lastWeekTeam);
                    lastWeekTeam = TEAM[(index + 1) % TEAM.length];
                    schedule.put(iDate, Collections.singletonList(lastWeekTeam));
                    continue;
                }
                // 周一到六排班
                schedule.put(iDate, Collections.singletonList(TEAM[start++]));
                start = start % TEAM.length;
            }
        }
        return schedule;
    }
}
