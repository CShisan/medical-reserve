package com.cshisan.reserve.service;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.*;
import com.cshisan.reserve.config.ServiceConfig;
import com.cshisan.reserve.dto.UserDTO;
import com.cshisan.reserve.entity.Role;
import com.cshisan.reserve.entity.User;
import com.cshisan.reserve.entity.UserRole;
import com.cshisan.reserve.mapper.UserMapper;
import com.cshisan.reserve.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author CShisan
 * @date 2022-2-19 17:39
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    private final UserMapper mapper;
    private final JwtUtil jwtUtil;
    private final ServiceConfig serviceConfig;
    private final UserRoleService userRoleService;


    @Autowired
    public UserService(UserMapper mapper, JwtUtil jwtUtil, ServiceConfig serviceConfig, UserRoleService userRoleService) {
        this.mapper = mapper;
        this.jwtUtil = jwtUtil;
        this.serviceConfig = serviceConfig;
        this.userRoleService = userRoleService;
    }

    /**
     * 获取用户登录实体
     *
     * @param user user
     * @return user
     */
    public User getUserLoginEntity(User user) {
        return mapper.getUserLoginEntity(user);
    }

    /**
     * 根据参数/token获取用户实体
     *
     * @param req 请求参数
     * @return 用户实体
     */
    public UserVO getInfoBy(UserDTO req) {
        User user;
        // 若传入参数为空则根据token查询
        if (Objects.isNull(req)) {
            String token = UserDetailUtil.getCurrentToken();
            // hutool解析长度短的会识别为Integer，强行转换会报错
            Long uid = Long.parseLong(jwtUtil.parse(token, "uid").toString());
            user = new User();
            user.setUid(Long.parseLong(Objects.requireNonNull(uid).toString()));
        } else {
            user = BeanUtil.convert(req, new User());
        }
        user = this.getUserLoginEntity(user);
        obscure(user);
        return BeanUtil.convert(user, new UserVO());
    }

    /**
     * 获取全部用户(患者)
     *
     * @return list
     */
    public List<User> findAll() {
        return this.list();
    }

    /**
     * 根据参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(UserDTO req) {
        IPage<Map<String, Object>> result;
        // uid和手机号码都为空则全部搜索
        if (ObjectUtils.isEmpty(req.getUid()) && ObjectUtils.isEmpty(req.getPhone())) {
            result = this.pageMaps(PageUtil.build(req));
        } else {
            User user = BeanUtil.convert(req, new User());
            QueryWrapper<User> wrapper = new QueryWrapper<>(user);
            result = this.pageMaps(PageUtil.build(req), wrapper);
        }
        // 身份证脱敏,防止密码泄露
        result.getRecords().forEach(item -> {
            String idCard = (String) item.get("idCard");
            idCard = DesensitizedUtil.idCardNum(idCard, 1, 2);
            item.put("idCard", idCard);
            item.put("password", "");
        });
        return result;
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public UserVO getOneById(Long id) {
        User user = this.getById(id);
        obscure(user);
        return BeanUtil.convert(user, new UserVO());
    }

    /**
     * 保存
     *
     * @param user user
     * @return status
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public boolean save(User user) {
        // 验证字段
        validPhone(user);
        validIdCard(user);

        String idCard = user.getIdCard();
        if (!ObjectUtils.isEmpty(idCard)) {
            user.setBirthday(IdcardUtil.getBirthDate(idCard));
            user.setSex(IdcardUtil.getGenderByIdCard(idCard));
        }

        // 加密密码
        user.setPassword(PwdEncoderUtil.encodePwd(user.getPassword()));
        // 设置默认头像
        user.setAvatarUrl(serviceConfig.getAvatarUrl());

        return super.save(user);
    }

    /**
     * 更新密码
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean updatePassword(UserDTO req) {
        QueryWrapper<User> qWrapper = new QueryWrapper<>();
        qWrapper.eq("uid", req.getUid());
        User source = this.getOne(qWrapper);
        if (Objects.isNull(source)) {
            throw new CustomException(CodeEnum.USER_NOT_FOUND);
        }
        String sourcePassword = source.getPassword();
        String password = req.getPassword();
        if (!PwdEncoderUtil.matches(password, sourcePassword)) {
            throw new CustomException(CodeEnum.USER_PW_ERROR);
        }
        UpdateWrapper<User> uWrapper = new UpdateWrapper<>();
        uWrapper.eq("uid", req.getUid());
        uWrapper.set("password", PwdEncoderUtil.encodePwd(req.getNewPassword()));
        return this.update(uWrapper);
    }

    /**
     * 更新
     *
     * @see UserService#update
     */
    @Transactional(rollbackFor = CustomException.class)
    public Boolean update(UserDTO req) {
        return update(BeanUtil.convert(req, new User()));
    }


    /**
     * 更新
     *
     * @param user user
     * @return status
     */
    public boolean update(User user) {
        String idCard = user.getIdCard();
        String idCardKey = "*";
        if (Objects.nonNull(idCard) && idCard.contains(idCardKey)) {
            user.setIdCard(null);
            idCard = null;
        }

        // 验证字段
        validPhone(user);
        validIdCard(user);
        validRoles(user);

        User source = this.getById(user.getId());
        String sIdCard = source.getIdCard();
        // 如果身份证有改动需要更新出生日期和性别
        if (Objects.nonNull(idCard)
                && (Objects.isNull(sIdCard) || !idCard.equals(sIdCard))) {
            user.setBirthday(IdcardUtil.getBirthDate(idCard));
            user.setSex(IdcardUtil.getGenderByIdCard(idCard));
        }

        // 更新的角色列表
        updateRoles(user.getUid(), user.getRoles());

        // 更新User
        return this.updateById(user);
    }

    /**
     * 更新角色列表
     *
     * @param uid      uid
     * @param reqRoles 待更新列表
     */
    @Transactional(rollbackFor = CustomException.class)
    public void updateRoles(Long uid, List<Role> reqRoles) {
        if (!ObjectUtils.isEmpty(reqRoles)) {
            // 更新Roles
            UserRole ur = new UserRole();
            ur.setUid(uid);

            Long[] cacheReq = reqRoles.stream().map(Role::getRoleId).toArray(Long[]::new);
            List<Long> addList = new ArrayList<>(Arrays.asList(cacheReq));
            // 原有角色列表
            List<UserRole> entices = userRoleService.getRolesBy(ur);
            Long[] cacheSource = entices.stream().map(UserRole::getRoleId).toArray(Long[]::new);
            List<Long> removeList = new ArrayList<>(Arrays.asList(cacheSource));


            // 得到需要添加的roles
            addList.removeAll(removeList);
            // 得到需要移除的roles
            removeList.removeAll(Arrays.asList(cacheReq));

            // 移除队列不为空则移除
            if (!ObjectUtils.isEmpty(removeList)) {
                removeList.forEach(roleId -> {
                    UserRole temp = new UserRole();
                    temp.setUid(uid);
                    temp.setRoleId(roleId);
                    QueryWrapper<UserRole> wrapper = new QueryWrapper<>(temp);
                    userRoleService.remove(wrapper);
                });
            }

            // 添加队列不为空则添加
            if (!ObjectUtils.isEmpty(reqRoles)) {
                List<UserRole> list = new ArrayList<>();
                addList.forEach(roleId -> {
                    UserRole temp = new UserRole();
                    temp.setUid(uid);
                    temp.setRoleId(roleId);
                    list.add(temp);
                });
                userRoleService.saveBatch(list);
            }
        }
    }

    /**
     * 身份证脱敏
     *
     * @param user user
     */
    private void obscure(User user) {
        String idCard = user.getIdCard();
        idCard = DesensitizedUtil.idCardNum(idCard, 3, 4);
        user.setIdCard(idCard);
    }

    private void validPhone(User user) {
        // 不为空时验证手机号码
        String phone = user.getPhone();
        if (Objects.nonNull(phone) && !Validator.isMobile(phone)) {
            throw new CustomException(CodeEnum.USER_PHONE_ERROR);
        }
    }

    private void validIdCard(User user) {
        // 不为空时验证身份证
        String idCard = user.getIdCard();
        if (Objects.nonNull(idCard) && !IdcardUtil.isValidCard(idCard)) {
            throw new CustomException(CodeEnum.USER_IDCARD_ERROR);
        }
    }

    private void validRoles(User user) {
        // 不为空时验证角色列表是否为空列表
        List<Role> roles = user.getRoles();
        if (Objects.nonNull(roles) && ObjectUtils.isEmpty(roles)) {
            throw new CustomException(CodeEnum.USER_ROLES_ERROR);
        }
    }


}
