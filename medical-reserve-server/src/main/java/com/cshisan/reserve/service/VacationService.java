package com.cshisan.reserve.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.enums.VacationStatusEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.DateSupplyUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.VacationDTO;
import com.cshisan.reserve.entity.DoctorRotation;
import com.cshisan.reserve.entity.Schedule;
import com.cshisan.reserve.entity.Vacation;
import com.cshisan.reserve.mapper.VacationMapper;
import com.cshisan.reserve.vo.VacationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author yuanbai
 * @date 2022/2/25 11:57
 */
@Service
public class VacationService extends ServiceImpl<VacationMapper, Vacation> {
    private final VacationMapper mapper;
    private final ScheduleService scheduleService;
    private final DoctorRotationService doctorRotationService;

    @Autowired
    public VacationService(VacationMapper mapper, @Lazy ScheduleService scheduleService, DoctorRotationService doctorRotationService) {
        this.mapper = mapper;
        this.scheduleService = scheduleService;
        this.doctorRotationService = doctorRotationService;
    }

    /**
     * 根据参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(VacationDTO req) {
        QueryWrapper<Vacation> wrapper = new QueryWrapper<>();
        // 参数不为空则条件搜索
        if (!ObjectUtils.isEmpty(req.getRealName())) {
            wrapper.eq("u.real_name", req.getRealName());
        }
        if (Objects.nonNull(req.getDeptId())) {
            wrapper.eq("d.dept_id", req.getDeptId());
        }
        wrapper.eq("v.del_flag", 0);
        wrapper.eq("u.del_flag", 0);
        wrapper.eq("dept.del_flag", 0);
        return mapper.listPage(PageUtil.build(req), wrapper);
    }

    /**
     * 根据参数查询调休记录
     *
     * @param req 请求参数
     * @return list
     */
    public List<VacationVO> listBy(VacationDTO req) {
        Date date = req.getVacationDate();
        QueryWrapper<Vacation> wrapper = new QueryWrapper<>();
        wrapper.eq("doctor_id", req.getDoctorId());
        wrapper.eq("status", 2);
        wrapper.between("vacation_date", DateUtil.beginOfMonth(date), DateUtil.endOfMonth(date));

        List<Vacation> list = this.list(wrapper);
        List<VacationVO> result = new ArrayList<>();
        if (ObjectUtils.isEmpty(list)) {
            return result;
        }
        list.forEach(vacation -> {
            result.add(BeanUtil.convert(vacation, new VacationVO()));
        });
        return result;
    }

    /**
     * 根据参数查询调休记录
     *
     * @param req 请求参数
     * @return list
     */
    public List<Vacation> listByDoctor(Vacation req) {
        Date date = req.getVacationDate();
        QueryWrapper<Vacation> wrapper = new QueryWrapper<>();
        wrapper.eq("doctor_id", req.getDoctorId());
        wrapper.eq("status", 2);
        wrapper.eq("vacation_date", DateUtil.beginOfDay(date));
        return this.list(wrapper);
    }

    /**
     * 判断医生某天的某时段是否休假
     *
     * @param req 请求参数
     * @return status
     */
    public boolean isVacation(Vacation req) {
        QueryWrapper<Vacation> wrapper = new QueryWrapper<>();
        wrapper.eq("doctor_id", req.getDoctorId());
        wrapper.eq("status", VacationStatusEnum.ALLOWED.getCode());
        wrapper.eq("vacation_date", req.getVacationDate());
        wrapper.eq("time_interval", req.getTimeInterval());
        return Objects.nonNull(this.getOne(wrapper));
    }

    /**
     * 保存
     *
     * @see VacationService#save(Vacation)
     */
    public Boolean save(VacationDTO req) {
        Long doctorId = req.getDoctorId();

        // 查询班次
        QueryWrapper<DoctorRotation> doctorRotationWrapper = new QueryWrapper<>();
        doctorRotationWrapper.eq("doctor_id", doctorId);
        DoctorRotation doctorRotation = doctorRotationService.getOne(doctorRotationWrapper);
        Date vacationDate = req.getVacationDate();

        // 查询是否有排版
        QueryWrapper<Schedule> scheduleWrapper = new QueryWrapper<>();
        scheduleWrapper.eq("schedule_date", vacationDate);
        scheduleWrapper.eq("rotation_id",doctorRotation.getRotationId());
        Schedule schedule = scheduleService.getOne(scheduleWrapper);
        if (Objects.isNull(schedule)){
            throw new CustomException(CodeEnum.NO.getCode(), "该天尚未排班");
        }

        return save(BeanUtil.convert(req, new Vacation()));
    }

    /**
     * 保存
     *
     * @param vacation vacation
     * @return status
     */
    @Transactional(rollbackFor = CustomException.class)
    @Override
    public boolean save(Vacation vacation) {
        Date date = vacation.getVacationDate();
        Schedule schedule = new Schedule();
        schedule.setScheduleDate(date);
        // 判断date是否未排班或者已经是过去式
        validVacationDate(date);
        validSchedule(schedule);

        // 判断是否当天已经有请求在审核
        QueryWrapper<Vacation> wrapper = new QueryWrapper<>(vacation);
        Vacation valid = this.getOne(wrapper);
        if (Objects.nonNull(valid)) {
            throw new CustomException(CodeEnum.SERVICE_VACATION_HAS_REQ_ERROR);
        }

        vacation.setVacationId(IDUtil.businessId());
        return super.save(vacation);
    }

    /**
     * 根据参数更新
     *
     * @param req 请求参数
     * @return status
     */
    @Transactional(rollbackFor = CustomException.class)
    public Boolean update(VacationDTO req) {
        Date date = req.getVacationDate();
        Schedule schedule = new Schedule();
        schedule.setScheduleDate(date);
        // 判断date是否未排班或者已经是过去式
        validVacationDate(date);
        validSchedule(schedule);

        // 若表id为空则根据业务id先查询表id
        Vacation vacation = BeanUtil.convert(req, new Vacation());
        QueryWrapper<Vacation> wrapper = new QueryWrapper<>();
        wrapper.eq("vacation_id", vacation.getVacationId());
        Vacation source = this.getOne(wrapper);
        if (Objects.isNull(vacation.getId())) {
            vacation.setId(source.getId());
        }
        return this.updateById(vacation);
    }

    /**
     * 验证date是否已经是过去式
     *
     * @param date date
     */
    private void validVacationDate(Date date) {
        if (DateUtil.beginOfDay(new Date()).isAfter(date)) {
            throw new CustomException(CodeEnum.SERVICE_VACATION_DATE_ERROR);
        }
    }

    /**
     * 验证date是否未排班
     *
     * @param schedule schedule
     */
    private void validSchedule(Schedule schedule) {
        QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
        wrapper.eq("schedule_date", DateSupplyUtil.j2sDate(schedule.getScheduleDate()));
        Schedule source = scheduleService.getOne(wrapper);
        if (Objects.isNull(source)) {
            throw new CustomException(CodeEnum.SERVICE_SCHEDULE_NULL);
        }
    }
}
