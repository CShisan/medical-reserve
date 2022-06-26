package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cshisan.reserve.entity.Vacation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/25 11:56
 */
@Mapper
public interface VacationMapper extends BaseMapper<Vacation> {
    /**
     * 根据参数查询分页
     *
     * @param page    分页参数
     * @param wrapper 请求参数
     * @return list
     */
    @Select("select v.id, v.vacation_id, v.doctor_id, u.real_name, " +
            "dept.dept_name, v.vacation_date , v.status, v.time_interval, v.description " +
            "from vacation as v " +
            "join user as u on v.doctor_id = u.uid " +
            "join doctor as d on v.doctor_id = d.doctor_id " +
            "join department as dept on d.dept_id = dept.dept_id " +
            "${ew.customSqlSegment}")
    IPage<Map<String, Object>> listPage(@Param("page") IPage<Map<String, Object>> page, @Param(Constants.WRAPPER) QueryWrapper<Vacation> wrapper);
}
