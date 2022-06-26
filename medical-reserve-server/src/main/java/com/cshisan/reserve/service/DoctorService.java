package com.cshisan.reserve.service;

import cn.hutool.core.util.DesensitizedUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.DoctorDTO;
import com.cshisan.reserve.entity.Doctor;
import com.cshisan.reserve.entity.Role;
import com.cshisan.reserve.entity.User;
import com.cshisan.reserve.entity.UserRole;
import com.cshisan.reserve.mapper.DoctorMapper;
import com.cshisan.reserve.vo.DoctorVO;
import com.cshisan.reserve.vo.JobTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/22 14:41
 */
@Service
public class DoctorService extends ServiceImpl<DoctorMapper, Doctor> {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final JobTitleService jobTitleService;

    private final DoctorMapper mapper;

    @Autowired
    public DoctorService(UserService userService, RoleService roleService, UserRoleService userRoleService, JobTitleService jobTitleService, DoctorMapper mapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.jobTitleService = jobTitleService;
        this.mapper = mapper;
    }

    /**
     * 根据参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(DoctorDTO req) {
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        // 参数不为空则条件搜索
        if (!ObjectUtils.isEmpty(req.getRealName())) {
            wrapper.like("u.real_name", req.getRealName());
        }
        if (!ObjectUtils.isEmpty(req.getPhone())) {
            wrapper.like("u.phone", req.getPhone());
        }
        wrapper.eq("d.del_flag", 0);
        wrapper.eq("u.del_flag", 0);
        wrapper.eq("dept.del_flag", 0);
        wrapper.eq("j.del_flag", 0);
        return mapper.listPage(PageUtil.build(req), wrapper);
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public DoctorVO getOneById(Long id) {
        DoctorVO doctor = BeanUtil.convert(this.getById(id), new DoctorVO());

        // 构建user查询
        User user = new User();
        user.setUid(doctor.getDoctorId());
        user = userService.getOne(new QueryWrapper<>(user));

        String idCard = user.getIdCard();
        idCard = DesensitizedUtil.idCardNum(idCard, 1, 2);
        doctor.setIdCard(idCard);
        doctor.setRealName(user.getRealName());
        doctor.setPhone(user.getPhone());

        return doctor;
    }

    /**
     * 根据id查询单个实体
     *
     * @param doctorId id
     * @return 单个实体
     */
    public DoctorVO getOneByDoctorId(Long doctorId) {
        Doctor req = new Doctor();
        req.setDoctorId(doctorId);
        Doctor sourceDoctor = this.getOne(new QueryWrapper<>(req));
        DoctorVO doctor = BeanUtil.convert(sourceDoctor, new DoctorVO());

        // 构建user查询
        User user = new User();
        user.setUid(doctor.getDoctorId());
        user = userService.getOne(new QueryWrapper<>(user));
        doctor.setRealName(user.getRealName());
        doctor.setAvatar(user.getAvatarUrl());
        doctor.setPhone(user.getPhone());
        doctor.setIdCard(user.getIdCard());
        JobTitleVO jobTitle = jobTitleService.getOneByTitleId(doctor.getJobTitleId());
        doctor.setTitleName(jobTitle.getTitleName());
        doctor.setCharge(jobTitle.getCharge());

        return doctor;
    }

    /**
     * 保存单个实体
     *
     * @param req 请求参数
     * @return status
     */
    @Transactional(rollbackFor = CustomException.class)
    public Boolean save(DoctorDTO req) {
        Long doctorId = IDUtil.doctorId();
        // 构建user并存储
        User user = BeanUtil.convert(req,new User());
        user.setUid(doctorId);
        user.setUsername(req.getRealName());
        userService.save(user);

        // 查询全部role并移除不为医生和患者的角色
        List<Role> roles = roleService.list();
        removeRole(roles.iterator());
        roles.forEach(role -> {
            UserRole ur = new UserRole();
            ur.setUid(doctorId);
            ur.setRoleId(role.getRoleId());
            userRoleService.save(ur);
        });

        // 构建doctor并存储
        req.setDoctorId(doctorId);
        return this.save(BeanUtil.convert(req, new Doctor()));
    }

    /**
     * 移除不为医生和患者的角色
     *
     * @param it it
     */
    private void removeRole(Iterator<Role> it) {
        String doctor = IDUtil.IdEnum.DOCTOR.getKey();
        String patient = IDUtil.IdEnum.PATIENT.getKey();
        while (it.hasNext()) {
            Role role = it.next();
            String key = role.getRoleKey();
            if (doctor.equalsIgnoreCase(key) || patient.equalsIgnoreCase(key)) {
                continue;
            }
            it.remove();
        }
    }

    /**
     * 根据参数获取list
     *
     * @param req 请求参数
     * @return list
     */
    public List<DoctorVO> listBy(DoctorDTO req) {
        return mapper.listBy(req.getDeptId());
    }

    /**
     * 更新
     *
     * @param req 请求参数
     * @return status
     */
    @Transactional(rollbackFor = CustomException.class)
    public Boolean update(DoctorDTO req) {
        // 查询数据库数据并更新
        User user = new User();
        user.setUid(req.getDoctorId());
        user = userService.getUserLoginEntity(user);
        user.setPhone(req.getPhone());
        user.setRealName(req.getRealName());
        user.setUsername(req.getRealName());
        user.setIdCard(req.getIdCard());
        userService.update(user);

        return this.updateById(BeanUtil.convert(req, new Doctor()));
    }
}
