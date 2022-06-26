package com.cshisan.reserve.common.valid;

/**
 * 验证分组类
 *
 * @author yuanbai
 * @date 2022/2/18 10:22
 */
public interface ValidGroup {
    /**
     * 保存组
     */
    interface Save{}

    /**
     * 删除组
     */
    interface Delete{}

    /**
     * 更新组
     */
    interface Update{}

    /**
     * 查询组
     */
    interface Query{}
}
