package com.sym.demo.threadpooldemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * 线程池练习
 *
 * @author suyongming
 * @date ：2021/2/15 21:45
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        // 最快 没有核心线程 全是临时员工
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        /* <pre>
         * {@code corePoolSize < 0}<br>
         * {@code keepAliveTime < 0}<br>         // 10个核心线程
         * {@code maximumPoolSize <= 0}<br>
         * {@code maximumPoolSize < corePoolSize}
         * </pre>*/
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        /*
         * 单例线程池
         * 1个核心线程
         * */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

//        自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new MyTask(i));
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class MyTask implements Runnable {
        int num;


        @Override
        public void run() {
            System.out.println("thread" + this.num);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
