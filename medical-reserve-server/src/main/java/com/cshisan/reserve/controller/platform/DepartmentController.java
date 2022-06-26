package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.DepartmentDTO;
import com.cshisan.reserve.service.DepartmentService;
import com.cshisan.reserve.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/24 15:25
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody DepartmentDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @GetMapping("/list/all")
    public Result<List<DepartmentVO>> listAll() {
        return ResultUtil.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public Result<DepartmentVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody DepartmentDTO req) {
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody DepartmentDTO req) {
        return ResultUtil.ok(service.update(req));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return ResultUtil.ok(service.delete(id));
    }

}
