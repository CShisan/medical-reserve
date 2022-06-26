package com.cshisan.reserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cshisan.reserve.entity.Rotation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author yuanbai
 * @date 2022/2/25 10:45
 */
@Mapper
public interface RotationMapper extends BaseMapper<Rotation> {
    /**
     * 根据班次ID获取对应key
     *
     * @param rotationId 班次ID
     * @return 对应key
     */
    @Select("select rotation_key from rotation where rotation_id = #{rotationId} and del_flag = 0")
    String getKey(Long rotationId);
}
