package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cshisan.reserve.entity.Doctor;
import com.cshisan.reserve.vo.DoctorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-19 10:39
 */
@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {
    /**
     * 根据参数查询分页
     *
     * @param page    分页参数
     * @param wrapper 请求参数
     * @return list
     */
    @Select("select d.id, d.doctor_id, u.real_name, u.phone, dept.dept_name, j.title_name, d.description " +
            "from doctor as d " +
            "join user as u on d.doctor_id = u.uid " +
            "join department as dept on d.dept_id = dept.dept_id " +
            "join job_title as j on d.job_title_id = j.title_id " +
            "${ew.customSqlSegment}")
    IPage<Map<String, Object>> listPage(@Param("page") IPage<Map<String, Object>> page, @Param(Constants.WRAPPER) QueryWrapper<Doctor> wrapper);

    /**
     * 根据参数查询list
     *
     * @param deptId deptId
     * @return list
     */
    @Select("select d.id, d.doctor_id, u.real_name, u.phone, j.title_name, j.charge " +
            "from doctor as d, user as u, job_title as j " +
            "where d.dept_id = #{deptId} " +
            "and d.doctor_id = u.uid " +
            "and d.job_title_id = j.title_id " +
            "and d.del_flag = 0 " +
            "and u.del_flag = 0")
    List<DoctorVO> listBy(Long deptId);
}
