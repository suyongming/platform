package com.sym.common.exception.exception.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 自定义返回错误类型
 * @author: mxt
 * @create: 2021-01-12 11:24
 */
@Data
@AllArgsConstructor
@ToString
public class CustomResultType implements ResultType {

    /** 自定义业务异常 */
    public static final Integer COMMON_CODE = 200;

    private final Integer code = COMMON_CODE;
    private String msg;

}
