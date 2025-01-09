package com.sym.demo.designmodel.cor;

import com.sym.demo.designmodel.cor.context.OrderHandlerContext;
import com.sym.demo.designmodel.cor.context.OrderReq;

/**
 * @description 责任链模式运行
 * @Author: sym
 * @Date: 2022/11/1 10:40
 */
public class CorMain {


    private static OrderSubmitHandler orderSubmitHandler = new OrderSubmitHandler();

    private static CacheOrderHandler cacheOrderHandler = new CacheOrderHandler();

    /**
    * @Description: 模拟下单场景
    * @Param: [args]
    * @return: void
    * @Author: sym
    * @Date: 2022/11/1
    */
    public static void main(String[] args) {

        if(false||true) {
            System.out.println(11111);

        } else {
            System.out.println(22222);
        }

//        OrderHandlerContext context = new OrderHandlerContext();
//        context.setOrderReq(OrderReq.builder().orderNo("123").build());
//
//
//        OrderBaseHandler.Builder builder = new OrderBaseHandler.Builder();
//
//
//        builder
//                .addHandler(orderSubmitHandler)
//                .addHandler(cacheOrderHandler)
//                .build().doHandler(context);

    }
}
