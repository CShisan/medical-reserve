package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.DoctorDTO;
import com.cshisan.reserve.service.DoctorService;
import com.cshisan.reserve.vo.DoctorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-26 19:40
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService service;

    @Autowired
    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody DoctorDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @PostMapping("/list/param")
    public Result<List<DoctorVO>> listBy(@RequestBody DoctorDTO req) {
        return ResultUtil.ok(service.listBy(req));
    }

    @GetMapping("/{id}")
    public Result<DoctorVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody DoctorDTO req) {
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody DoctorDTO req) {
        return ResultUtil.ok(service.update(req));
    }

    @GetMapping("/doctor-id/{doctorId}")
    public Result<DoctorVO> getOneByDoctorId(@PathVariable Long doctorId) {
        return ResultUtil.ok(service.getOneByDoctorId(doctorId));
    }
}
