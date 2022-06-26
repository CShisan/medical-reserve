package com.cshisan.reserve.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.dto.DoctorRotationDTO;
import com.cshisan.reserve.entity.DoctorRotation;
import com.cshisan.reserve.mapper.DoctorRotationMapper;
import com.cshisan.reserve.vo.DoctorRotationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/25 11:55
 */
@Service
public class DoctorRotationService extends ServiceImpl<DoctorRotationMapper, DoctorRotation> {
    private final DoctorRotationMapper mapper;

    @Autowired
    public DoctorRotationService(DoctorRotationMapper mapper) {
        this.mapper = mapper;
    }


    public List<DoctorRotationVO> listBy(DoctorRotationDTO req) {
        return mapper.listBy(req.getRotationId(), req.getDeptId());
    }

    /**
     * 保存单个实体
     *
     * @param req 请求参数
     * @return status
     */
    @Transactional(rollbackFor = CustomException.class)
    public Boolean save(DoctorRotationDTO req) {
        if (mapper.exist(req.getDoctorId()) > 0) {
            return false;
        }
        return this.save(BeanUtil.convert(req, new DoctorRotation()));
    }

    /**
     * 根据表id删除记录
     *
     * @param id 表id
     * @return status
     */
    public Boolean delete(Long id) {
        return this.removeById(id);
    }
}
