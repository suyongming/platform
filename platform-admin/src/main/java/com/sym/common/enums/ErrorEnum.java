package com.sym.common.enums;

import lombok.Getter;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/18 10:19
 */
@Getter
public enum ErrorEnum implements BaseEnum{
    SUCCESS(200, "成功"),
    SC_INTERNAL_SERVER_ERROR(500,"未知异常，请联系管理员"),
    SYSTEM_EXCEPTION(-1, "系统异常"),
    UNKNOWN_EXCEPTION(100001, "未知异常"),
    SERVICE_EXCEPTION(100002, "服务异常"),
    BUSINESS_EXCEPTION(100003, "业务异常"),
    PARAM_EXCEPTION(100004, "参数异常"),
    DIGEST_EXCEPTION(100005, "签名异常"),
    DUP_REQUEST_EXCEPTION(100006, "请勿重复提交请求"),
    API_NOT_EXIST(100007, "API服务不存在"),
    ILLEGAL_REQUEST(100008, "非法请求");

    private Integer code;
    private String desc;

    ErrorEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
