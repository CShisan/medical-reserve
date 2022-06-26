package com.cshisan.reserve.controller.platform;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.ScheduleDTO;
import com.cshisan.reserve.service.ScheduleService;
import com.cshisan.reserve.vo.ScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-25 20:01
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService service;

    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping("/list")
    public Result<List<ScheduleVO>> list(@RequestBody Date date) {
        return ResultUtil.ok(service.getList(date));
    }

    @PostMapping("/auto-schedule")
    public Result<Boolean> autoSchedule(@RequestBody ScheduleDTO req){
        return ResultUtil.ok(service.save(req));
    }

}
