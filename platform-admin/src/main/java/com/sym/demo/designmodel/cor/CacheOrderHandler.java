package com.sym.demo.designmodel.cor;

import cn.hutool.core.util.ObjectUtil;
import com.sym.common.exception.exception.AssertUtil;
import com.sym.demo.designmodel.cor.context.OrderHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @description 缓存订单
 * @Author: sym
 * @Date: 2022/11/1 10:36
 */
@Slf4j
public class CacheOrderHandler extends OrderBaseHandler {
    @Override
    public void doHandler(OrderHandlerContext context) {
        AssertUtil.isTrue(ObjectUtil.isNotNull(context.getOrderRes()), "缓存失败，订单信息为空。");
       log.info("缓存订单");


       this.doNextHandler(context);
    }
}
