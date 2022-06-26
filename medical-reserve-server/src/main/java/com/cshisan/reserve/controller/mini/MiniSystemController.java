package com.cshisan.reserve.controller.mini;

import com.cshisan.reserve.auth.WxLoginBean;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/21 15:35
 */
@RestController
@RequestMapping("/mini")
public class MiniSystemController {
    private final SystemService systemService;

    @Autowired
    public MiniSystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @PostMapping("/wx-login")
    public Result<Map<String, Object>> wxLogin(@RequestBody WxLoginBean req) {
        return ResultUtil.ok(systemService.wxLogin(req));
    }
}
