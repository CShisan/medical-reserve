package com.cshisan.reserve.controller.mini;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.DoctorDTO;
import com.cshisan.reserve.service.DoctorService;
import com.cshisan.reserve.vo.DoctorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-26 19:40
 */
@RestController
@RequestMapping("/mini/doctor")
public class MiniDoctorController {
    private final DoctorService service;

    @Autowired
    public MiniDoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping("/list/param")
    public Result<List<DoctorVO>> listBy(@RequestBody DoctorDTO req) {
        return ResultUtil.ok(service.listBy(req));
    }

    @GetMapping("/doctor-id/{doctorId}")
    public Result<DoctorVO> getOneByDoctorId(@PathVariable Long doctorId) {
        return ResultUtil.ok(service.getOneByDoctorId(doctorId));
    }

}
