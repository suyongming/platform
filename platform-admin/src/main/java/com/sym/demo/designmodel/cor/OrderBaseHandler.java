package com.sym.demo.designmodel.cor;

import com.sym.demo.designmodel.cor.context.OrderHandlerContext;
import lombok.Data;

/**
 * @description chain of responsibility 责任链模式
 * 校验处理器
 * @Author: sym
 * @Date: 2022/11/1 9:58
 */
@Data
public abstract class OrderBaseHandler<E> {

    protected OrderBaseHandler nextHandler;

    public void next(OrderBaseHandler handler) {
        this.nextHandler = handler;
    }

    /**
     * 订单处理
     * 方法实现要求，在方法最后调用 @doNextHandler(OrderHandlerContext context)
     * @param context
     */
    public abstract void doHandler(OrderHandlerContext context);

    protected void doNextHandler(OrderHandlerContext context){
        if(nextHandler!=null){
            nextHandler.doHandler(context);
        }
    }
    /**
     * 构建者模式
     * @param <E>
     */
    public static class Builder<E> {
        private OrderBaseHandler<E> head;
        private OrderBaseHandler<E> tail;

        public Builder<E> addHandler(OrderBaseHandler<E> handler) {
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.next(handler);
            this.tail = handler;
            return this;
        }

        public void clean(){
            this.head = null;
            this.tail = null;
        }

        public OrderBaseHandler<E> build() {
            return this.head;
        }
    }
}
