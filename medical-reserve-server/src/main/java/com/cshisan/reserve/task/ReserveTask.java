package com.cshisan.reserve.task;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cshisan.reserve.entity.Reserve;
import com.cshisan.reserve.entity.User;
import com.cshisan.reserve.service.ReserveService;
import com.cshisan.reserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author CShisan
 * @date 2022-3-11 23:50
 */
@Component
public class ReserveTask {
    private final ReserveService reserveService;
    private final UserService userService;

    @Autowired
    public ReserveTask(ReserveService reserveService, UserService userService) {
        this.reserveService = reserveService;
        this.userService = userService;
    }

    /**
     * 每天12时取消所有上午时段及之前未报到预约
     */
    @Async
    @Scheduled(cron = "0 0 12 * * ? ")
    public void clearBeforeToday12Reserve() {
        clearReserve(new Date(), 0);
    }

    /**
     * 每天18时取消所有下午时段及之前未报到预约
     */
    @Async
    @Scheduled(cron = "0 0 18 * * ? ")
    public void clearBeforeToday18Reserve() {
        clearReserve(new Date(), 1);
    }

    public void clearReserve(Date date, int interval) {
        Date today = DateUtil.beginOfDay(date);
        UpdateWrapper<Reserve> wrapper = new UpdateWrapper<>();
        wrapper.set("status", 0);
        wrapper.and(w -> {
            w.lt("reserve_date", today)
                    .or().and(subW -> {
                subW.eq("reserve_date", today);
                subW.eq("time_interval", interval);
            });
        });
        wrapper.eq("status", 1);
        reserveService.update(wrapper);
    }

    public void patientReserve() {
        List<User> patients = userService.list();
    }
}
