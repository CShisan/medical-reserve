package com.cshisan.reserve.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.RoleDTO;
import com.cshisan.reserve.entity.Role;
import com.cshisan.reserve.entity.UserRole;
import com.cshisan.reserve.mapper.RoleMapper;
import com.cshisan.reserve.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanbai
 * @date 2022/2/22 14:15
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    private final UserRoleService userRoleService;

    @Autowired
    public RoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 获取全部role
     *
     * @return roles
     */
    public List<RoleVO> getAll() {
        List<RoleVO> list = new ArrayList<>();
        this.list().forEach(role -> {
            list.add(BeanUtil.convert(role, new RoleVO()));
        });
        return list;
    }


    /**
     * 根据参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(RoleDTO req) {
        // 角色名为空则全部搜索
        if (ObjectUtils.isEmpty(req.getRoleName())) {
            return this.pageMaps(PageUtil.build(req));
        }
        Role role = BeanUtil.convert(req, new Role());
        QueryWrapper<Role> wrapper = new QueryWrapper<>(role);
        return this.pageMaps(PageUtil.build(req), wrapper);
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public RoleVO getOneById(Long id) {
        return BeanUtil.convert(this.getById(id), new RoleVO());
    }

    /**
     * 保存单个实体
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean save(RoleDTO req) {
        req.setRoleId(IDUtil.businessId());
        return this.save(BeanUtil.convert(req, new Role()));
    }

    /**
     * 更新
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean update(RoleDTO req) {
        return this.updateById(BeanUtil.convert(req, new Role()));
    }

    /**
     * 删除
     *
     * @param id 表id
     * @return status
     */
    public Boolean delete(Long id) {
        Role role = this.getById(id);
        if (Objects.isNull(role)) {
            throw new CustomException(CodeEnum.NO.getCode(), "获取不到该角色");
        }
        Long roleId = role.getRoleId();
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        List<UserRole> userRoleList = userRoleService.list(wrapper);
        if (!ObjectUtils.isEmpty(userRoleList)) {
            throw new CustomException(CodeEnum.NO.getCode(), "该角色存在关联用户");
        }
        return this.removeById(id);
    }
}
