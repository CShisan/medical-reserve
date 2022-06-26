package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.ReserveDTO;
import com.cshisan.reserve.service.ReserveService;
import com.cshisan.reserve.vo.ReserveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/24 10:42
 */
@RestController
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveService service;

    @Autowired
    public ReserveController(ReserveService service) {
        this.service = service;
    }

    @PostMapping("/wait-patients")
    public Result<List<ReserveVO>> getWaitPatients(@RequestBody ReserveDTO req) {
        return ResultUtil.ok(service.getWaitPatients(req));
    }

    @PostMapping("/completed-patients")
    public Result<List<ReserveVO>> getCompletedPatients(@RequestBody ReserveDTO req) {
        return ResultUtil.ok(service.getCompletedPatients(req));
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody ReserveDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @GetMapping("/{id}")
    public Result<ReserveVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

}
