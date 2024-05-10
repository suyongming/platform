package com.sym.common.constant;

/**
 * @description
 * @Author: sym
 * @Date: 2024/5/10 14:14
 */
public class RedisKeyConstant {
    /** 基于Redis实现的延时队列，Redis中zset的key，占位符为分组名 **/
    public static final String DELAY_MESSAGE_QUEUE = "delayMessage:queue:%s";
    /** 基于Redis实现的延时队列，Redis中消息体的key，占位符为分组名和消息ID **/
    public static final String DELAY_MESSAGE_POOL = "delayMessage:pool:%s:%s";
}
