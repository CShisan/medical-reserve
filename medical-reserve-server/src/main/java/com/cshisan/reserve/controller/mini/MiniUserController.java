package com.cshisan.reserve.controller.mini;

import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.UserDTO;
import com.cshisan.reserve.service.UserService;
import com.cshisan.reserve.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CShisan
 * @date 2022-2-22 22:38
 */
@RestController
@RequestMapping("/mini/user")
public class MiniUserController {
    private final UserService service;

    @Autowired
    public MiniUserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/info")
    public Result<UserVO> getInfo() {
        return ResultUtil.ok(service.getInfoBy(null));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody UserDTO req){
        return ResultUtil.ok(service.update(req));
    }
}
