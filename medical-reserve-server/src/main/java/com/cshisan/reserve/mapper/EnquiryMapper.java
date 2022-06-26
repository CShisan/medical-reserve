package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cshisan.reserve.dto.EnquiryDTO;
import com.cshisan.reserve.entity.Enquiry;
import com.cshisan.reserve.vo.EnquiryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author CShisan
 * @date 2022-2-19 10:36
 */
@Mapper
public interface EnquiryMapper extends BaseMapper<Enquiry> {
    /**
     * 根据参数查询分页
     *
     * @param page    分页参数
     * @param wrapper 请求参数
     * @return list
     */
    @Select("select e.id, e.enquiry_id, e.patient_id, " +
            "u1.real_name as patientName, e.doctor_id, u2.real_name as doctorName, " +
            "e.remark, e.create_time, e.update_time " +
            "from enquiry as e " +
            "join user as u1 on e.patient_id = u1.uid " +
            "join user as u2 on e.doctor_id = u2.uid " +
            "${ew.customSqlSegment}")
    IPage<Map<String, Object>> listPage(@Param("page") IPage<Map<String, Object>> page, @Param(Constants.WRAPPER) QueryWrapper<Enquiry> wrapper);

    /**
     * 根据表id查询单个实体
     *
     * @param id 表id
     * @return entity
     */
    @Select("select e.id, e.enquiry_id, e.patient_id, " +
            "u1.real_name as patientName, e.doctor_id, u2.real_name as doctorName, " +
            "e.remark, e.create_time, e.update_time " +
            "from enquiry as e, user as u1, user as u2 " +
            "where e.id = #{id} " +
            "and e.patient_id = u1.uid " +
            "and e.doctor_id = u2.uid " +
            "and e.del_flag = 0 and u1.del_flag = 0 and u2.del_flag = 0")
    EnquiryVO getOneById(Long id);

    /**
     * 根据参数查询list
     *
     * @param req 请求参数
     * @return list
     */
    @Select("select e.id, e.create_time, e.remark, dept.dept_name, " +
            "u1.real_name as patientName, u2.real_name as doctorName " +
            "from enquiry as e, user as u1, user as u2, doctor as d, department as dept " +
            "where e.patient_id = #{patientId} " +
            "and e.patient_id = u1.uid " +
            "and e.doctor_id = u2.uid " +
            "and e.doctor_id = d.doctor_id " +
            "and d.dept_id = dept.dept_id " +
            "and e.del_flag = 0 and u1.del_flag = 0 and u2.del_flag = 0 and dept.del_flag=0 " +
            "order by e.create_time desc")
    List<EnquiryVO> listBy(EnquiryDTO req);
}
