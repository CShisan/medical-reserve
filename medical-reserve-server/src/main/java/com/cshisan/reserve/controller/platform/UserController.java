package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.UserDTO;
import com.cshisan.reserve.service.UserService;
import com.cshisan.reserve.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-22 22:38
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/info")
    public Result<UserVO> getInfo() {
        return ResultUtil.ok(service.getInfoBy(null));
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody UserDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @GetMapping("/{id}")
    public Result<UserVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody UserDTO req){
        return ResultUtil.ok(service.update(req));
    }

    @PostMapping("/update/password")
    public Result<Boolean> updatePassword(@RequestBody UserDTO req){
        return ResultUtil.ok(service.updatePassword(req));
    }
}
