package com.cshisan.reserve.controller.mini;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.EnquiryDTO;
import com.cshisan.reserve.service.EnquiryService;
import com.cshisan.reserve.vo.EnquiryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/24 10:16
 */
@RestController
@RequestMapping("/mini/enquiry")
public class MiniEnquiryController {
    private final EnquiryService service;

    @Autowired
    public MiniEnquiryController(EnquiryService service) {
        this.service = service;
    }

    @PostMapping("/list/param")
    public Result<List<EnquiryVO>> listBy(@RequestBody EnquiryDTO req) {
        return ResultUtil.ok(service.listBy(req));
    }

}
