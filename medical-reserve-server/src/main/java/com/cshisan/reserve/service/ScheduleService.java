package com.cshisan.reserve.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.BiMap;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.ReserveIntervalEnum;
import com.cshisan.reserve.common.enums.ReservePeriodEnum;
import com.cshisan.reserve.common.enums.ReserveStatusEnum;
import com.cshisan.reserve.common.enums.VacationIntervalEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.DateSupplyUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.dto.ScheduleDTO;
import com.cshisan.reserve.entity.Reserve;
import com.cshisan.reserve.entity.Rotation;
import com.cshisan.reserve.entity.Schedule;
import com.cshisan.reserve.entity.Vacation;
import com.cshisan.reserve.handler.ScheduleHandler;
import com.cshisan.reserve.mapper.ScheduleMapper;
import com.cshisan.reserve.vo.DoctorScheduleVO;
import com.cshisan.reserve.vo.ScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author yuanbai
 * @date 2022/2/25 11:53
 */
@Service
public class ScheduleService extends ServiceImpl<ScheduleMapper, Schedule> {
    private final ScheduleMapper mapper;
    private final RotationService rotationService;
    private final VacationService vacationService;
    private final ReserveService reserveService;

    @Autowired
    public ScheduleService(ScheduleMapper mapper, RotationService rotationService, VacationService vacationService, ReserveService reserveService) {
        this.mapper = mapper;
        this.rotationService = rotationService;
        this.vacationService = vacationService;
        this.reserveService = reserveService;
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @see ScheduleService#schedulesOfMonth
     */
    public List<ScheduleVO> getList(Date that) {
        List<Schedule> list = schedulesOfMonth(that);
        List<ScheduleVO> result = new ArrayList<>();
        list.forEach(schedule -> result.add(BeanUtil.convert(schedule, new ScheduleVO())));
        return result;
    }

    /**
     * ?????????????????????
     *
     * @param req ????????????
     * @return status
     */
    @Transactional(rollbackFor = CustomException.class)
    public boolean save(ScheduleDTO req) {
        Date that = req.getScheduleDate();
        String rule = req.getRule();
        List<Integer> dateList = req.getDateList();

        // ????????????????????????????????????????????????
        if (that.before(DateUtil.beginOfMonth(new Date()))) {
            return false;
        }

        List<Schedule> exist = schedulesOfMonth(that);
        // ??????????????????????????????????????????
        if (ObjectUtils.isNotEmpty(exist)) {
            this.removeBatchByIds(exist);
        }

        // ??????????????????????????????key2Id??????
        List<Rotation> rotations = rotationService.list();
        String[] team = rotations.stream().map(Rotation::getRotationKey).toArray(String[]::new);
        BiMap<String, Long> key2Id = new BiMap<>(new HashMap<>(16));
        for (Rotation rotation : rotations) {
            key2Id.put(rotation.getRotationKey(), rotation.getRotationId());
        }

        // ??????????????????????????????
        Schedule lastTeam = tryGetSchedule(rotations, that, false);
        Schedule lastSundayTeam = tryGetSchedule(rotations, that, true);

        // ??????????????????key
        String lastTeamKey = key2Id.getKey(lastTeam.getRotationId());
        String lastSundayTeamKey = key2Id.getKey(lastSundayTeam.getRotationId());

        // ??????????????????????????????
        ScheduleHandler sHandler = ScheduleHandler.getScheduleHandler(team, rule);
        Map<Date, List<String>> table = sHandler.rotation(that, lastTeamKey, lastSundayTeamKey, dateList);

        // ????????????
        List<Schedule> schedules = new ArrayList<>();
        table.forEach((date, list) -> {
            list.forEach(key -> {
                // ??????schedule
                schedules.add(Schedule.builder()
                        .scheduleId(IDUtil.businessId())
                        .rotationId(key2Id.get(key))
                        .scheduleDate(date)
                        .build());
            });
        });
        return this.saveBatch(schedules);
    }

    private Schedule getSchedule(Schedule req) {
        return this.getOne(new QueryWrapper<>(req));
    }

    /**
     * ??????????????????????????????schedule
     *
     * @param rotations  ???????????????
     * @param that       ????????????
     * @param onlySunday ??????????????????
     * @return schedule
     */
    private Schedule tryGetSchedule(List<Rotation> rotations, Date that, boolean onlySunday) {
        Schedule schedule = null;
        Date endDayLastMonth = DateSupplyUtil.endDayLastMonth(that);
        for (int i = 0; Objects.isNull(schedule); i++) {
            // ?????????????????????????????????????????????????????????????????????????????????
            if (DateSupplyUtil.daysOfLastMonth(that) == i) {
                schedule = Schedule.builder()
                        .scheduleDate(endDayLastMonth)
                        .rotationId(rotations.get(rotations.size() - 1).getRotationId())
                        .build();
                break;
            }
            Date date = DateUtil.offsetDay(endDayLastMonth, -i);

            // ??????????????????????????????????????????????????????????????????
            if (onlySunday && !DateSupplyUtil.isSunday(date)) {
                continue;
            }
            // ??????????????????????????????
            schedule = Schedule.builder().scheduleDate(date).build();
            schedule = getSchedule(schedule);
        }
        return schedule;
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param that that
     * @return ????????????
     */
    private List<Schedule> schedulesOfMonth(Date that) {
        Date begin = DateUtil.beginOfMonth(that);
        Date end = DateUtil.endOfMonth(that);
        return mapper.schedulesOfMonth(begin, end);
    }

    public List<DoctorScheduleVO> listDoctorSchedule(ScheduleDTO req) {
        Long doctorId = req.getDoctorId();
        Date date = req.getScheduleDate();
        List<DoctorScheduleVO> result = new ArrayList<>();

        // ????????????????????????
        Vacation vacation = new Vacation();
        vacation.setDoctorId(doctorId);
        vacation.setVacationDate(date);
        vacation.setTimeInterval(VacationIntervalEnum.ALL_DAY.getCode());
        boolean isVacation = vacationService.isVacation(vacation);

        boolean isSchedule = mapper.listDoctorSchedule(doctorId, DateUtil.beginOfDay(date)) > 0;
        // ????????????????????????????????????
        if (!isVacation && isSchedule) {
            result.addAll(calcMargin(doctorId, date, ReserveIntervalEnum.AM.getCode()));
            result.addAll(calcMargin(doctorId, date, ReserveIntervalEnum.PM.getCode()));
        }
        return result;
    }

    private List<DoctorScheduleVO> calcMargin(Long doctorId, Date date, int interval) {
        date = DateUtil.beginOfDay(date);
        List<DoctorScheduleVO> result = new ArrayList<>();

        // ?????????????????????????????????????????????
        Vacation vacation = new Vacation();
        vacation.setDoctorId(doctorId);
        vacation.setVacationDate(date);
        vacation.setTimeInterval(interval);
        if (vacationService.isVacation(vacation)) {
            return result;
        }
        
        int periodNum = ReservePeriodEnum.calcPeriodNumBy(interval);
        for (int period = 1; period <= periodNum; period++) {
            QueryWrapper<Reserve> wrapper = new QueryWrapper<>();
            wrapper.eq("doctor_id", doctorId);
            wrapper.eq("reserve_date", date);
            wrapper.ne("status", ReserveStatusEnum.CANCEL.getCode());
            wrapper.eq("time_interval", interval);
            wrapper.eq("period", period);
            // ??????????????????
            long exitNum = reserveService.count(wrapper);
            // ????????????????????????????????????
            boolean disable = ReservePeriodEnum.isExpired(date, interval, period);
            DoctorScheduleVO vo = new DoctorScheduleVO();
            vo.setDate(date);
            vo.setInterval(interval);
            vo.setPeriod(period);
            vo.setPeriodText(Objects.requireNonNull(ReservePeriodEnum.codeOf(interval, period)).getText());
            vo.setWeek(convertWeek(date));
            vo.setDisable(disable);
            vo.setCount((int) (Reserve.PERIOD_NUM - exitNum));
            result.add(vo);
        }
        return result;
    }

    private String convertWeek(Date date) {
        String[] weeks = {"", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????"};
        return weeks[DateUtil.dayOfWeek(date)];
    }
}
