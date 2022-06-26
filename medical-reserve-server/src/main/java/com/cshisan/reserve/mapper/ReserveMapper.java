package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cshisan.reserve.dto.ReserveDTO;
import com.cshisan.reserve.entity.Reserve;
import com.cshisan.reserve.vo.ReserveVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-19 10:37
 */
@Mapper
public interface ReserveMapper extends BaseMapper<Reserve> {
    /**
     * 根据参数查询分页
     *
     * @param page    分页参数
     * @param wrapper 请求参数
     * @return list
     */
    @Select("select r.id, r.reserve_id, r.patient_id, " +
            "u1.real_name as patientName, r.doctor_id, u2.real_name as doctorName, " +
            "r.status, r.create_time, r.update_time " +
            "from reserve as r " +
            "join user as u1 on r.patient_id = u1.uid " +
            "join user as u2 on r.doctor_id = u2.uid " +
            "${ew.customSqlSegment}")
    IPage<Map<String, Object>> listPage(@Param("page") IPage<Map<String, Object>> page, @Param(Constants.WRAPPER) QueryWrapper<Reserve> wrapper);

    /**
     * 根据参数获取列表
     *
     * @param wrapper 请求参数
     * @return list
     */
    @Select("select u1.uid as patientId, u1.real_name as patientName, u1.birthday, u1.sex, " +
            "u2.uid as doctorId, u2.real_name as doctorName, dept.dept_name, " +
            "r.id, r.reserve_id, r.reserve_date, r.time_interval, r.period, r.status, r.charge " +
            "from reserve as r " +
            "join user as u1 on r.patient_id = u1.uid " +
            "join doctor as d on r.doctor_id = d.doctor_id " +
            "join user as u2 on r.doctor_id = u2.uid j" +
            "oin department as dept on d.dept_id = dept.dept_id " +
            "${ew.customSqlSegment}")
    List<ReserveVO> listBy(@Param(Constants.WRAPPER) QueryWrapper<ReserveDTO> wrapper);

    /**
     * 根据表id查询单个实体
     *
     * @param id 表id
     * @return entity
     */
    @Select("select r.id, r.reserve_id, r.patient_id, " +
            "u1.real_name as patientName, r.doctor_id, u2.real_name as doctorName, " +
            "r.status, r.create_time, r.update_time " +
            "from reserve as r, user as u1, user as u2 " +
            "where r.id = #{id} " +
            "and r.patient_id = u1.uid " +
            "and r.doctor_id = u2.uid " +
            "and r.del_flag = 0 and u1.del_flag = 0 and u2.del_flag = 0")
    ReserveVO getOneById(Long id);
}
