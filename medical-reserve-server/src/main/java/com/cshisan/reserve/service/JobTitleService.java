package com.cshisan.reserve.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.JobTitleDTO;
import com.cshisan.reserve.entity.Doctor;
import com.cshisan.reserve.entity.JobTitle;
import com.cshisan.reserve.mapper.JobTitleMapper;
import com.cshisan.reserve.vo.JobTitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanbai
 * @date 2022/2/22 14:39
 */
@Service
public class JobTitleService extends ServiceImpl<JobTitleMapper, JobTitle> {

    private final DoctorService doctorService;

    @Autowired
    @Lazy
    public JobTitleService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * 根据分页参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(JobTitleDTO req) {
        return this.pageMaps(PageUtil.build(req));
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public JobTitleVO getOneById(Long id) {
        return BeanUtil.convert(this.getById(id), new JobTitleVO());
    }

    /**
     * 根据titleId查询单个实体
     *
     * @param titleId titleId
     * @return 单个实体
     */
    public JobTitleVO getOneByTitleId(Long titleId) {
        JobTitle jobTitle = new JobTitle();
        jobTitle.setTitleId(titleId);
        return BeanUtil.convert(this.getOne(new QueryWrapper<>(jobTitle)), new JobTitleVO());
    }

    /**
     * 保存单个实体
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean save(JobTitleDTO req) {
        req.setTitleId(IDUtil.businessId());
        return this.save(BeanUtil.convert(req, new JobTitle()));
    }

    /**
     * 获取全部
     *
     * @return list
     */
    public List<JobTitleVO> listAll() {
        List<JobTitle> list = this.list();
        List<JobTitleVO> result = new ArrayList<>();
        if (ObjectUtils.isEmpty(list)) {
            return result;
        }
        list.forEach(dept -> {
            result.add(BeanUtil.convert(dept, new JobTitleVO()));
        });
        return result;
    }

    /**
     * 更新
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean update(JobTitleDTO req) {
        return this.updateById(BeanUtil.convert(req, new JobTitle()));
    }

    /**
     * 删除
     *
     * @param id 表id
     * @return status
     */
    public Boolean delete(Long id) {
        JobTitle title = this.getById(id);
        if (Objects.isNull(title)) {
            throw new CustomException(CodeEnum.NO.getCode(), "获取不到该职称");
        }
        Long titleId = title.getTitleId();
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        wrapper.eq("job_title_id", titleId);
        List<Doctor> doctorList = doctorService.list(wrapper);
        if (!ObjectUtils.isEmpty(doctorList)) {
            throw new CustomException(CodeEnum.NO.getCode(), "该职称存在关联医生");
        }
        return this.removeById(id);
    }
}
