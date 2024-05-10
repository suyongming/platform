package com.sym.common.utils.delay;

/**
 * 延迟消息主体类型
 *
 * @author suym
 */
public interface DelayMessageExecutor {

    /**
     * 获取队列名称
     *
     * @return 分组名称
     */
    String getGroupName();

    /**
     * 到期执行机制
     *
     * @param delayMessage 消息体
     * @return 执行结果
     */
    boolean execute(DelayMessage delayMessage);
}

