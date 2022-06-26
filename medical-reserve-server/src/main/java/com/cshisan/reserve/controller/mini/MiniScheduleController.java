package com.cshisan.reserve.controller.mini;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.ScheduleDTO;
import com.cshisan.reserve.service.ScheduleService;
import com.cshisan.reserve.vo.DoctorScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-25 20:01
 */
@RestController
@RequestMapping("/mini/schedule")
public class MiniScheduleController {
    private final ScheduleService service;

    @Autowired
    public MiniScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping("/doctor-schedule")
    public Result<List<DoctorScheduleVO>> listDoctorSchedule(@RequestBody ScheduleDTO req){
        return ResultUtil.ok(service.listDoctorSchedule(req));
    }
}
