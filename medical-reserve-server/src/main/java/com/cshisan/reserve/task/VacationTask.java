package com.cshisan.reserve.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cshisan.reserve.entity.Vacation;
import com.cshisan.reserve.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author CShisan
 * @date 2022-3-12 13:08
 */
@Component
public class VacationTask {
    private final VacationService vacationService;

    @Autowired
    public VacationTask(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @Async
    @Scheduled(cron = "0 0 18 * * ?")
    public void clearOvertimeVacation() {
        UpdateWrapper<Vacation> wrapper = new UpdateWrapper<>();
        wrapper.set("status", 0);
        wrapper.eq("status", 1);
        wrapper.lt("vacation_date", new Date());
        vacationService.update(wrapper);
    }
}
