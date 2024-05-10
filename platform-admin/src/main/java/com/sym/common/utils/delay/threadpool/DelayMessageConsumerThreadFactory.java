package com.sym.common.utils.delay.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 调用omni系统的线程工厂
 *
 * @author tianyi.zhang
 */
public class DelayMessageConsumerThreadFactory implements ThreadFactory {

    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable runnable) {
        String name = String.format("delayMessageConsumer-%s", nextId.getAndIncrement());
        Thread thread = new Thread(runnable, name);
        thread.setDaemon(true);
        return thread;
    }
}
