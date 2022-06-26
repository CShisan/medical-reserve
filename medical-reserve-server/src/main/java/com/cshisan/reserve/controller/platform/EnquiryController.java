package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.EnquiryDTO;
import com.cshisan.reserve.service.EnquiryService;
import com.cshisan.reserve.vo.EnquiryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/24 10:16
 */
@RestController
@RequestMapping("/enquiry")
public class EnquiryController {
    private final EnquiryService service;

    @Autowired
    public EnquiryController(EnquiryService service) {
        this.service = service;
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody EnquiryDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @GetMapping("/{id}")
    public Result<EnquiryVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody EnquiryDTO req) {
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody EnquiryDTO req) {
        return ResultUtil.ok(service.update(req));
    }
}
