package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cshisan.reserve.entity.Schedule;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/25 11:53
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    /**
     * 根据传入时间查询当月排班表
     *
     * @param begin 当月第一天
     * @param end   当月最后一天
     * @return 排班表
     */
    @Select("select id,schedule_id,schedule_date,rotation_id from schedule " +
            "where schedule_date between #{begin} and #{end} and del_flag = 0")
    @Results(id = "scheduleAllArgMap", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "del_flag", property = "delFlag"),
            @Result(column = "editor", property = "editor"),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "schedule_id", property = "scheduleId"),
            @Result(column = "schedule_date", property = "scheduleDate"),
            @Result(column = "holiday", property = "holiday"),
            @Result(column = "rotation_id", property = "rotationId"),
            @Result(column = "rotation_id", property = "rotationKey", javaType = String.class,
                    many = @Many(select = "com.cshisan.reserve.mapper.RotationMapper.getKey"))
    })
    List<Schedule> schedulesOfMonth(Date begin, Date end);

    /**
     * 根据参数查询医生该日期的排班
     *
     * @param doctorId doctorId
     * @param date     date
     * @return list
     */
    @Select("select count(s.schedule_date) " +
            "from doctor_rotation as dr, schedule as s " +
            "where dr.doctor_id = #{doctorId} " +
            "and dr.rotation_id = s.rotation_id " +
            "and s.schedule_date = #{date} " +
            "and dr.del_flag = 0 and s.del_flag = 0")
    int listDoctorSchedule(Long doctorId, Date date);
}
