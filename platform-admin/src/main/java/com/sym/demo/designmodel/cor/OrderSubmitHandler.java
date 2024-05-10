package com.sym.demo.designmodel.cor;

import cn.hutool.core.util.ObjectUtil;
import com.sym.common.exception.exception.AssertUtil;
import com.sym.demo.designmodel.cor.context.OrderHandlerContext;
import com.sym.demo.designmodel.cor.context.OrderRes;
import lombok.extern.slf4j.Slf4j;

/**
 * @description 订单提交
 * @Author: sym
 * @Date: 2022/11/1 10:36
 */
@Slf4j
public class OrderSubmitHandler extends OrderBaseHandler {
    @Override
    public void doHandler(OrderHandlerContext context) {
        AssertUtil.isTrue(ObjectUtil.isNotNull(context.getOrderReq()), "order submit req is null");
       log.info("订单提交");

       // nextHandler.doHandler(context) 无法判断下一个责任链

        // todo orderDb
        OrderRes orderDb = OrderRes.builder().orderNo(
                context.getOrderReq().getOrderNo()
        ).build();

        context.setOrderRes(orderDb);

        this.doNextHandler(context);

    }
}
