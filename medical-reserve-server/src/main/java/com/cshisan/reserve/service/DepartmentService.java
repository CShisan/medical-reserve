package com.cshisan.reserve.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.DepartmentDTO;
import com.cshisan.reserve.entity.Department;
import com.cshisan.reserve.entity.Doctor;
import com.cshisan.reserve.mapper.DepartmentMapper;
import com.cshisan.reserve.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanbai
 * @date 2022/2/22 14:42
 */
@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, Department> {
    private final DoctorService doctorService;

    @Autowired
    public DepartmentService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * 根据参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(DepartmentDTO req) {
        String deptName = req.getDeptName();
        // 参数为空则全部搜索
        if (Objects.isNull(deptName)) {
            return this.pageMaps(PageUtil.build(req));
        }
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.like("dept_name",deptName);
        return this.pageMaps(PageUtil.build(req), wrapper);
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public DepartmentVO getOneById(Long id) {
        return BeanUtil.convert(this.getById(id), new DepartmentVO());
    }

    /**
     * 获取全部
     *
     * @return list
     */
    public List<DepartmentVO> listAll() {
        List<Department> list = this.list();
        List<DepartmentVO> result = new ArrayList<>();
        if (ObjectUtils.isEmpty(list)) {
            return result;
        }
        list.forEach(dept -> {
            result.add(BeanUtil.convert(dept, new DepartmentVO()));
        });
        return result;
    }

    /**
     * 保存单个实体
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean save(DepartmentDTO req) {
        req.setDeptId(IDUtil.businessId());
        return this.save(BeanUtil.convert(req, new Department()));
    }

    /**
     * 更新
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean update(DepartmentDTO req) {
        return this.updateById(BeanUtil.convert(req, new Department()));
    }

    /**
     * 删除
     *
     * @param id 表id
     * @return status
     */
    public Boolean delete(Long id) {
        Department dept = this.getById(id);
        if (Objects.isNull(dept)){
            throw new CustomException(CodeEnum.NO.getCode(), "获取不到该科室");
        }
        Long deptId = dept.getDeptId();
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        wrapper.eq("dept_id", deptId);
        List<Doctor> doctorList = doctorService.list(wrapper);
        if (!ObjectUtils.isEmpty(doctorList)){
            throw new CustomException(CodeEnum.NO.getCode(), "该科室存在关联医生");
        }
        return this.removeById(id);
    }

}
