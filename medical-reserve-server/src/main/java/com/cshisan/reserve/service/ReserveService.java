package com.cshisan.reserve.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.enums.ReserveIntervalEnum;
import com.cshisan.reserve.common.enums.ReservePeriodEnum;
import com.cshisan.reserve.common.enums.ReserveStatusEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.DateSupplyUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.ReserveDTO;
import com.cshisan.reserve.entity.Reserve;
import com.cshisan.reserve.mapper.ReserveMapper;
import com.cshisan.reserve.vo.ReserveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanbai
 * @date 2022/2/22 14:19
 */
@Service
public class ReserveService extends ServiceImpl<ReserveMapper, Reserve> {
    private final ReserveMapper mapper;

    @Autowired
    public ReserveService(ReserveMapper mapper) {
        this.mapper = mapper;
    }

    public List<ReserveVO> getWaitPatients(ReserveDTO req) {
        Date date = new Date();
        Date today12 = DateUtil.offsetHour(DateUtil.beginOfDay(date), 12);
        // 设置对应时段
        if (date.before(today12)) {
            req.setTimeInterval(ReserveIntervalEnum.AM.getCode());
        } else {
            req.setTimeInterval(ReserveIntervalEnum.PM.getCode());
        }
        Date reserveDate = DateUtil.beginOfDay(date);
        QueryWrapper<ReserveDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("r.doctor_id", req.getDoctorId());
        wrapper.eq("r.reserve_date", new java.sql.Date(reserveDate.getTime()));
        wrapper.eq("r.time_interval", req.getTimeInterval());
        wrapper.eq("r.status", ReserveStatusEnum.PENDING.getCode());
        String patientName = req.getPatientName();
        if (!ObjectUtils.isEmpty(patientName)) {
            wrapper.like("u1.real_name", patientName);
        }

        return mapper.listBy(wrapper);
    }

    public List<ReserveVO> getCompletedPatients(ReserveDTO req) {
        Date date = new Date();
        Date reserveDate = DateUtil.beginOfDay(date);
        QueryWrapper<ReserveDTO> wrapper = new QueryWrapper<>();
        wrapper.eq("r.doctor_id", req.getDoctorId());
        wrapper.eq("r.reserve_date", new java.sql.Date(reserveDate.getTime()));
        wrapper.eq("r.status", ReserveStatusEnum.DONE.getCode());
        return mapper.listBy(wrapper);
    }

    /**
     * 根据参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(ReserveDTO req) {
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        // 参数不为空则参数搜索
        if (!ObjectUtils.isEmpty(req.getPatientName())) {
            wrapper.like("u1.real_name", req.getPatientName());
        }
        if (!ObjectUtils.isEmpty(req.getDoctorName())) {
            wrapper.like("u2.real_name", req.getDoctorName());
        }
        wrapper.eq("r.del_flag", 0);
        wrapper.eq("u1.del_flag", 0);
        wrapper.eq("u2.del_flag", 0);
        return mapper.listPage(PageUtil.build(req), wrapper);
    }

    /**
     * 根据参数获取list
     *
     * @param req 请求参数
     * @return list
     */
    public List<ReserveVO> listBy(ReserveDTO req) {
        QueryWrapper<ReserveDTO> wrapper = new QueryWrapper<>(req);
        wrapper.orderByDesc("r.create_time");
        List<ReserveVO> reserves = mapper.listBy(wrapper);
        reserves.forEach(reserve -> {
            Integer timeInterval = reserve.getTimeInterval();
            reserve.setIntervalText(Objects.requireNonNull(ReserveIntervalEnum.codeOf(timeInterval)).getText());
            Integer period = reserve.getPeriod();
            reserve.setPeriodText(Objects.requireNonNull(ReservePeriodEnum.codeOf(timeInterval,period)).getText());
            Integer status = reserve.getStatus();
            reserve.setStatusText(Objects.requireNonNull(ReserveStatusEnum.codeOf(status)).getValue());
        });
        return reserves;
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public ReserveVO getOneById(Long id) {
        return BeanUtil.convert(this.getById(id), new ReserveVO());
    }

    /**
     * 保存
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean save(ReserveDTO req) {
        req.setStatus(ReserveStatusEnum.PENDING.getCode());
        Reserve reserve = BeanUtil.convert(req, new Reserve());
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>(reserve);
        reserve = this.getOne(wrapper);
        if (Objects.nonNull(reserve)) {
            return false;
        }
        req.setReserveId(IDUtil.businessId());
        return super.save(BeanUtil.convert(req, new Reserve()));
    }

    /**
     * 更新
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean update(ReserveDTO req) {
        return super.updateById(BeanUtil.convert(req, new Reserve()));
    }

    /**
     * 根据ReserveId更新
     *
     * @see ReserveService#updateByReserveId(Reserve)
     */
    public boolean updateByReserveId(ReserveDTO req) {
        return updateByReserveId(BeanUtil.convert(req, new Reserve()));
    }

    /**
     * 根据ReserveId更新
     *
     * @param req 请求参数
     * @return status
     */
    public boolean updateByReserveId(Reserve req) {
        Long reserveId = req.getReserveId();
        if (Objects.isNull(reserveId)) {
            throw new CustomException(CodeEnum.FAIL);
        }
        UpdateWrapper<Reserve> wrapper = new UpdateWrapper<>();
        wrapper.eq("reserve_id", req.getReserveId());
        return this.update(req, wrapper);
    }

    /**
     * 统计本日待接诊人数
     *
     * @return count
     */
    public long countWaitReserve() {
        QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
        wrapper.eq("reserve_date", DateSupplyUtil.j2sDate(new Date()));
        return this.count(wrapper);
    }
}
