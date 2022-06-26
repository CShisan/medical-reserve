package com.cshisan.reserve.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.RotationDTO;
import com.cshisan.reserve.entity.DoctorRotation;
import com.cshisan.reserve.entity.Rotation;
import com.cshisan.reserve.mapper.RotationMapper;
import com.cshisan.reserve.vo.RotationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanbai
 * @date 2022/2/25 10:46
 */
@Service
public class RotationService extends ServiceImpl<RotationMapper, Rotation> {
    public final DoctorRotationService doctorRotationService;

    @Autowired
    public RotationService(DoctorRotationService doctorRotationService) {
        this.doctorRotationService = doctorRotationService;
    }

    /**
     * 根据分页参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(RotationDTO req) {
        return this.pageMaps(PageUtil.build(req));
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public RotationVO getOneById(Long id) {
        return BeanUtil.convert(this.getById(id), new RotationVO());
    }

    /**
     * 保存单个实体
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean save(RotationDTO req) {
        req.setRotationId(IDUtil.businessId());
        return this.save(BeanUtil.convert(req, new Rotation()));
    }

    /**
     * 更新单个实体
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean update(RotationDTO req) {
        return this.updateById(BeanUtil.convert(req, new Rotation()));
    }

    /**
     * 根据表id删除记录
     *
     * @param id 表id
     * @return status
     */
    public Boolean delete(Long id){
        Rotation rotation = this.getById(id);
        if (Objects.isNull(rotation)){
            throw new CustomException(CodeEnum.NO.getCode(), "获取不到该班次");
        }
        Long rotationId = rotation.getRotationId();
        QueryWrapper<DoctorRotation> wrapper = new QueryWrapper<>();
        wrapper.eq("rotation_id", rotationId);
        List<DoctorRotation> doctorRotationList = doctorRotationService.list(wrapper);
        if (!ObjectUtils.isEmpty(doctorRotationList)){
            throw new CustomException(CodeEnum.NO.getCode(), "该班次存在关联医生");
        }
        return this.removeById(id);
    }
}
