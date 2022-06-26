package com.cshisan.reserve.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cshisan.reserve.common.exception.CustomException;
import com.cshisan.reserve.common.utils.BeanUtil;
import com.cshisan.reserve.common.utils.IDUtil;
import com.cshisan.reserve.common.utils.PageUtil;
import com.cshisan.reserve.dto.EnquiryDTO;
import com.cshisan.reserve.entity.Enquiry;
import com.cshisan.reserve.entity.Reserve;
import com.cshisan.reserve.mapper.EnquiryMapper;
import com.cshisan.reserve.vo.EnquiryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/22 14:16
 */
@Service
public class EnquiryService extends ServiceImpl<EnquiryMapper, Enquiry> {
    private final EnquiryMapper mapper;
    private final ReserveService reserveService;

    @Autowired
    public EnquiryService(EnquiryMapper mapper, ReserveService reserveService) {
        this.mapper = mapper;
        this.reserveService = reserveService;
    }

    /**
     * 根据参数获取分页
     *
     * @param req 请求参数
     * @return list
     */
    public IPage<Map<String, Object>> listPage(EnquiryDTO req) {
        QueryWrapper<Enquiry> wrapper = new QueryWrapper<>();
        // 参数不为空则参数搜索
        if (!ObjectUtils.isEmpty(req.getPatientName())) {
            wrapper.like("u1.real_name", req.getPatientName());
        }
        if (!ObjectUtils.isEmpty(req.getDoctorName())) {
            wrapper.like("u2.real_name", req.getDoctorName());
        }
        wrapper.eq("e.del_flag", 0);
        wrapper.eq("u1.del_flag", 0);
        wrapper.eq("u2.del_flag", 0);
        return mapper.listPage(PageUtil.build(req), wrapper);
    }

    /**
     * 根据参数获取list
     *
     * @param req 请求参数
     * @return list
     */
    public List<EnquiryVO> listBy(EnquiryDTO req) {
        return mapper.listBy(req);
    }

    /**
     * 根据表id查询单个实体
     *
     * @param id 表自增id
     * @return 单个实体
     */
    public EnquiryVO getOneById(Long id) {
        return mapper.getOneById(id);
    }

    /**
     * 更新
     *
     * @param req 请求参数
     * @return status
     */
    public Boolean update(EnquiryDTO req) {
        return this.updateById(BeanUtil.convert(req, new Enquiry()));
    }

    /**
     * 保存
     *
     * @param req 请求参数
     * @return status
     */
    @Transactional(rollbackFor = CustomException.class)
    public Boolean save(EnquiryDTO req) {
        Reserve reserve = new Reserve();
        reserve.setReserveId(req.getReserveId());
        reserve.setStatus(2);
        reserveService.updateByReserveId(reserve);

        req.setEnquiryId(IDUtil.businessId());
        return this.save(BeanUtil.convert(req, new Enquiry()));
    }

    /**
     * 统计累积接诊人数
     *
     * @return count
     */
    public long countAllEnquiry() {
        return this.count();
    }

    /**
     * 统计累积接诊人数
     *
     * @return count
     */
    public long countEnquiry(Date beginDate, Date endDate) {
        QueryWrapper<Enquiry> wrapper = new QueryWrapper<>();
        wrapper.ge("create_time", beginDate);
        wrapper.le("create_time", endDate);
        return this.count(wrapper);
    }
}
