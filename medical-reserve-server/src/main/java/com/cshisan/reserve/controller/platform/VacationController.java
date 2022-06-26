package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.VacationDTO;
import com.cshisan.reserve.service.VacationService;
import com.cshisan.reserve.vo.VacationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-27 4:41
 */
@RestController
@RequestMapping("/vacation")
public class VacationController {
    private final VacationService service;

    @Autowired
    public VacationController(VacationService service) {
        this.service = service;
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody VacationDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @PostMapping("/list/param")
    public Result<List<VacationVO>> listBy(@RequestBody VacationDTO req){
        return ResultUtil.ok(service.listBy(req));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody VacationDTO req) {
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody VacationDTO req){
        return ResultUtil.ok(service.update(req));
    }
}
