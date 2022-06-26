package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.JobTitleDTO;
import com.cshisan.reserve.service.JobTitleService;
import com.cshisan.reserve.vo.JobTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-26 13:06
 */
@RestController
@RequestMapping("/job-title")
public class JobTitleController {
    private final JobTitleService service;

    @Autowired
    public JobTitleController(JobTitleService service) {
        this.service = service;
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody JobTitleDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @GetMapping("/list/all")
    public Result<List<JobTitleVO>> listAll() {
        return ResultUtil.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public Result<JobTitleVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody JobTitleDTO req) {
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody JobTitleDTO req) {
        return ResultUtil.ok(service.update(req));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return ResultUtil.ok(service.delete(id));
    }
}
