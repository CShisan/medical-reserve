package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.RotationDTO;
import com.cshisan.reserve.service.RotationService;
import com.cshisan.reserve.vo.RotationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-26 13:58
 */
@RestController
@RequestMapping("/rotation")
public class RotationController {
    private final RotationService service;

    @Autowired
    public RotationController(RotationService service) {
        this.service = service;
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody RotationDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @GetMapping("/{id}")
    public Result<RotationVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody RotationDTO req) {
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody RotationDTO req){
        return ResultUtil.ok(service.update(req));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id){
        return ResultUtil.ok(service.delete(id));
    }
}
