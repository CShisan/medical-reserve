package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cshisan.reserve.entity.DoctorRotation;
import com.cshisan.reserve.vo.DoctorRotationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/25 11:55
 */
@Mapper
public interface DoctorRotationMapper extends BaseMapper<DoctorRotation> {
    /**
     * 根据班次ID和科室ID查询对应医生列表
     *
     * @param rotationId rotationId
     * @param deptId     deptId
     * @return list
     */
    @Select("select dr.id,d.doctor_id,u.phone,u.real_name as doctorName " +
            "from doctor_rotation as dr, doctor as d, user as u " +
            "where dr.rotation_id = #{rotationId} " +
            "and dr.dept_id = #{deptId} " +
            "and dr.doctor_id = d.doctor_id " +
            "and d.doctor_id = u.uid " +
            "and dr.del_flag = 0 and d.del_flag= 0 and u.del_flag = 0")
    List<DoctorRotationVO> listBy(Long rotationId, Long deptId);

    /**
     * 查询该医生是否存在记录
     *
     * @param doctorId doctorId
     * @return count
     */
    @Select("select count(*) from doctor_rotation where doctor_id = #{doctorId} and del_flag = 0")
    int exist(Long doctorId);
}
