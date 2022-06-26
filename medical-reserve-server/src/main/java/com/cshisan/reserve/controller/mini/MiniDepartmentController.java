package com.cshisan.reserve.controller.mini;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.service.DepartmentService;
import com.cshisan.reserve.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/24 15:25
 */
@RestController
@RequestMapping("/mini/department")
public class MiniDepartmentController {
    private final DepartmentService service;

    @Autowired
    public MiniDepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/list/all")
    public Result<List<DepartmentVO>> listAll() {
        return ResultUtil.ok(service.listAll());
    }

}
