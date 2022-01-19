package com.sym.common.exception.exception.type;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ResultType {

    FLOW_EXCEPTION(429, "当前访问人数过多，请稍后再试！"),
    SYSTEM_ERROR(-1, "服务器开小差了，请稍后重试。");

    /**
     * 错误类型码
     */
    private final Integer code;
    /**
     * 错误类型描述信息
     */
    private final String msg;

    SystemErrorType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
