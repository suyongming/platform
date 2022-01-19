package com.sym.common.exception.exception.type;

import lombok.Getter;

/**
 * @description: 库存异常类型
 * @author: mxt
 * @create: 2021-01-12 11:24
 */
@Getter
public enum InventoryResultType implements ResultType {

    UNDER_STOCK(205001, "库存不足");

    /**
     * 错误类型码
     */
    private final Integer code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    InventoryResultType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public InventoryResultType setMsg(String msg){
        this.msg = msg;
        return this;
    }

}
