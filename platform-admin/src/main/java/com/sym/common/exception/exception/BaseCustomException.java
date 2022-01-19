package com.sym.common.exception.exception;

import com.sym.common.exception.exception.type.CustomResultType;
import com.sym.common.exception.exception.type.ResultType;
import com.sym.common.exception.exception.type.SystemErrorType;
import lombok.Getter;

@Getter
public class BaseCustomException extends RuntimeException {
    /**
     * 异常对应的错误类型
     */
    private final ResultType errorType;

    /**
     * 默认是系统异常
     */
    private BaseCustomException() {
        super(SystemErrorType.SYSTEM_ERROR.getMsg());
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }
    /**
     * 默认是自定义异常
     */
    private BaseCustomException(String message) {
        super(message);
        this.errorType = new CustomResultType(message);
    }
    /**
     * 默认是自定义异常
     */
    private BaseCustomException(ResultType errorType) {
        super(errorType.getMsg());
        this.errorType = errorType;
    }

    public static BaseCustomException getInstance() {
        return new BaseCustomException();
    }

    public static BaseCustomException getInstance(String message) {
        return new BaseCustomException(message);
    }

    public static BaseCustomException getInstance(ResultType errorType) {
        return new BaseCustomException(errorType);
    }
}
