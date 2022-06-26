package com.cshisan.reserve.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码枚举类
 *
 * @author CShisan
 * @date 2022-2-19 0:19
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {
    /**
     * 系统状态code
     */
    OK(200, "操作成功"),
    NO(400, "操作失败"),
    NOT_FOUND(404, "找不到资源"),
    FAIL(500, "系统异常,请稍后再试"),

    /**
     * 用户状态code
     */
    USER_AUTH_ERROR(10401,"认证失败"),
    USER_NOT_FOUND(10501,"用户不存在"),
    USER_PW_ERROR(10502,"密码错误"),
    USER_IDCARD_ERROR(10503,"身份证错误"),
    USER_PHONE_ERROR(10503,"手机号码错误"),
    USER_ROLES_ERROR(10504,"角色分配错误"),
    USER_WX_LOGIN_ERROR(10505,"微信登录失败"),
    USER_WX_ENCODE_ERROR(10505,"微信加密数据解密失败"),

    /**
     * 业务状态code
     */
    SERVICE_PARAM_ERROR(10901,"参数有误"),
    SERVICE_VACATION_DATE_ERROR(10902,"不可申请/撤销调休啦!!!"),
    SERVICE_VACATION_HAS_REQ_ERROR(10902,"已经有请求在审核啦,不要再催啦!!!"),
    SERVICE_SCHEDULE_NULL(10902,"尚未排班!!!");


    private final int code;
    private final String message;

    public static CodeEnum codeOf(int code) {
        for (CodeEnum value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
