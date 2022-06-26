package com.cshisan.reserve.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.cshisan.reserve.auth.UserLoginEntity;
import com.cshisan.reserve.auth.WxLoginBean;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.JwtUtil;
import com.cshisan.reserve.common.utils.WxUtil;
import com.cshisan.reserve.entity.Role;
import com.cshisan.reserve.entity.User;
import com.cshisan.reserve.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author yuanbai
 * @date 2022/2/21 15:46
 */
@Service
public class SystemService {
    private final JwtUtil jwtUtil;
    private final WxUtil wxUtil;
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final EnquiryService enquiryService;
    private final ReserveService reserveService;

    @Autowired
    public SystemService(JwtUtil jwtUtil, WxUtil wxUtil, UserService userService, RoleService roleService, UserRoleService userRoleService, EnquiryService enquiryService, ReserveService reserveService) {
        this.jwtUtil = jwtUtil;
        this.wxUtil = wxUtil;
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.enquiryService = enquiryService;
        this.reserveService = reserveService;
    }

    /**
     * 统计分析
     *
     * @return map
     */
    public Map<String, Object> statistics() {
        Map<String, Object> result = new HashMap<>(16);
        Date now = new Date();
        Date beginWeek = DateUtil.beginOfWeek(now);
        Date endWeek = DateUtil.endOfWeek(now);
        result.put("countWaitReserve", reserveService.countWaitReserve());
        result.put("countAllEnquiry", enquiryService.countAllEnquiry());
        result.put("countWeekAllEnquiry", enquiryService.countEnquiry(beginWeek, endWeek));
        int daysOfWeek = 7;
        List<Integer> weekEnquiry = new ArrayList<>();
        for (int i = 0; i < daysOfWeek; i++) {
            Date date = DateUtil.offsetDay(beginWeek, i);
            long total = enquiryService.countEnquiry(date, DateUtil.endOfDay(date));
            weekEnquiry.add((int) total);
        }
        result.put("countWeekEnquiry", weekEnquiry);
        List<Integer> lastWeekEnquiry = new ArrayList<>();
        Date lastBeginWeek = DateUtil.offsetDay(beginWeek, -7);
        for (int i = 0; i < daysOfWeek; i++) {
            Date date = DateUtil.offsetDay(lastBeginWeek, i);
            long total = enquiryService.countEnquiry(date, DateUtil.endOfDay(date));
            lastWeekEnquiry.add((int) total);
        }
        result.put("countLastWeekEnquiry", lastWeekEnquiry);
        return result;
    }

    public Map<String, Object> wxLogin(WxLoginBean req) {
        // 从微信接口获取手机号码
        req = wxUtil.getWxLoginBean(req);

        User user = new User();
        user.setOpenid(req.getOpenid());
        user = userService.getUserLoginEntity(user);

        // 若查询不到结果则代表未注册
        if (Objects.isNull(user)) {
            user = new User();
            user.setPhone(req.getPhoneNumber());
            user.setEditor(9527L);
            wxRegister(BeanUtil.convert(req, user));
        }

        // 防止密码泄露
        user.setPassword("");

        // 将token加载到缓存
        String token = jwtUtil.putCacheToken(BeanUtil.convert(user, new UserLoginEntity()));

        // 返回结果
        Map<String, Object> result = new HashMap<>(16);
        result.put("isHasIdCard", !ObjectUtils.isEmpty(user.getIdCard()));
        user.setIdCard("");
        result.put("token", token);
        result.put("user", user);
        return result;
    }

    /**
     * 微信注册
     *
     * @param user user
     */
    @Transactional(rollbackFor = CustomException.class)
    public void wxRegister(User user) {
        Long patientId = IDUtil.patientId();

        // 查询全部role并移除不为患者的角色
        List<Role> roles = roleService.list();
        removeRole(roles.iterator());
        roles.forEach(role -> {
            UserRole ur = new UserRole();
            ur.setUid(patientId);
            ur.setRoleId(role.getRoleId());
            ur.setEditor(9527L);
            userRoleService.save(ur);
        });

        // 构建user并存储
        user.setUid(patientId);
        String uuid = String.valueOf(UUID.randomUUID()).replace("-", "");
        user.setUsername("微信用户_" + uuid.substring(1, 13));
        user.setPassword(RandomUtil.randomString(8));
        userService.save(user);
    }

    /**
     * 移除不为患者的角色
     *
     * @param it it
     */
    private void removeRole(Iterator<Role> it) {
        String patient = IDUtil.IdEnum.PATIENT.getKey();
        while (it.hasNext()) {
            Role role = it.next();
            String key = role.getRoleKey();
            if (patient.equalsIgnoreCase(key)) {
                continue;
            }
            it.remove();
        }
    }
}
