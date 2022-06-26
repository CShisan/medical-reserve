package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CShisan
 * @date 2022-2-23 21:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleVO extends BaseVO {
    private Long roleId;

    private String roleKey;

    private String roleName;

}
