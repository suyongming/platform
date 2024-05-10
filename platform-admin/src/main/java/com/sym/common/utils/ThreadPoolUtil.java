package com.sym.common.utils;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * @description
 * @Author: sym
 * @Date: 2023/3/22 21:07
 */
public class ThreadPoolUtil {
    /**
    * @Description: 最大线程30、超出最多保留10s, 阻塞队列30 拒绝策略参考CustomRejectedExecutionHandler
    * @Param:
    * @return:
    * @Author: sym
    * @Date: 2023/3/22
    */
    public final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5,
            30,
            10,  // 多出的线程将最多保留10s
            TimeUnit.SECONDS,
            new ArrayBlockingQueue(30),  // 阻塞队列
            new NamedThreadFactory("", false),
            new CustomRejectedExecutionHandler());

    public final static ExecutorService newFixedThreadPool =  Executors.newFixedThreadPool(60);

    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        // 如果需要抛出自定义的 拒绝策略 就在这里自定义，触发时机是 超过临时最大线程 + 阻塞队列
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("线程超出来了");
//            throw BaseCustomException.getInstance("请稍后再试!!");
        }
    }


}
