package com.cshisan.reserve.task;

import com.cshisan.reserve.dto.ScheduleDTO;
import com.cshisan.reserve.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author CShisan
 * @date 2022-3-12 10:39
 */
@Component
public class ScheduleTask {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleTask(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 每月25号自动排班
     */
    @Async
    @Scheduled(cron = "0 0 0 25 * ?")
    public void autoSchedule() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        ScheduleDTO schedule = new ScheduleDTO();
        schedule.setRule("oodDays");
        schedule.setScheduleDate(new Date());
        schedule.setDateList(list);
        scheduleService.save(schedule);
    }
}
