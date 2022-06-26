package com.cshisan.reserve.controller.platform;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/21 15:35
 */
@RestController
public class SystemController {
    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/statistics")
    public Result<Map<String, Object>> statistics() {
        return ResultUtil.ok(systemService.statistics());
    }
}
