package com.cshisan.reserve.controller.mini;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.ReserveDTO;
import com.cshisan.reserve.service.ReserveService;
import com.cshisan.reserve.vo.ReserveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/24 10:42
 */
@RestController
@RequestMapping("/mini/reserve")
public class MiniReserveController {
    private final ReserveService service;

    @Autowired
    public MiniReserveController(ReserveService service) {
        this.service = service;
    }

    @PostMapping("/list/param")
    public Result<List<ReserveVO>> listBy(@RequestBody ReserveDTO req) {
        return ResultUtil.ok(service.listBy(req));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody ReserveDTO req){
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody ReserveDTO req){
        return ResultUtil.ok(service.update(req));
    }
}
