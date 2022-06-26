package com.cshisan.reserve.controller.platform;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.DoctorRotationDTO;
import com.cshisan.reserve.service.DoctorRotationService;
import com.cshisan.reserve.vo.DoctorRotationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CShisan
 * @date 2022-2-26 16:51
 */
@RestController
@RequestMapping("/doctor-rotation")
public class DoctorRotationController {
    private final DoctorRotationService service;

    @Autowired
    public DoctorRotationController(DoctorRotationService service) {
        this.service = service;
    }

    @PostMapping("/list/param")
    public Result<List<DoctorRotationVO>> listBy(@RequestBody DoctorRotationDTO req){
        return ResultUtil.ok(service.listBy(req));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody DoctorRotationDTO req){
        return ResultUtil.ok(service.save(req));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id){
        return ResultUtil.ok(service.delete(id));
    }
}
