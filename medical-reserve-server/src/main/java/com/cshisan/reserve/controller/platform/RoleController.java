package com.cshisan.reserve.controller.platform;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.utils.ResultUtil;
import com.cshisan.reserve.dto.RoleDTO;
import com.cshisan.reserve.service.RoleService;
import com.cshisan.reserve.service.UserRoleService;
import com.cshisan.reserve.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-23 21:46
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService service;
    private final UserRoleService userRoleService;

    @Autowired
    public RoleController(RoleService service, UserRoleService userRoleService) {
        this.service = service;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/all")
    public Result<List<RoleVO>> getAll() {
        return ResultUtil.ok(service.getAll());
    }

    @GetMapping("/uid/{uid}")
    public Result<List<RoleVO>> getRoleByUid(@PathVariable Long uid) {
        return ResultUtil.ok(userRoleService.getRolesByUid(uid));
    }

    @PostMapping("/list/page")
    public Result<IPage<Map<String, Object>>> listPage(@RequestBody RoleDTO req) {
        return ResultUtil.ok(service.listPage(req));
    }

    @GetMapping("/{id}")
    public Result<RoleVO> getOneById(@PathVariable Long id) {
        return ResultUtil.ok(service.getOneById(id));
    }

    @PostMapping("/")
    public Result<Boolean> save(@RequestBody RoleDTO req) {
        return ResultUtil.ok(service.save(req));
    }

    @PutMapping("/")
    public Result<Boolean> update(@RequestBody RoleDTO req) {
        return ResultUtil.ok(service.update(req));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return ResultUtil.ok(service.delete(id));
    }
}
