package com.sym.common.enums;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.NamedThreadFactory;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSONObject;
import com.sym.common.exception.exception.BaseCustomException;
import com.sym.common.utils.MeikeSHA256Utils;
import com.sym.common.utils.ThreadPoolUtil;
import lombok.*;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @description
 * @Author: sym
 * @Date: 2022/10/9 16:36
 */
public class Demo {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // TODO 如果需要的线程很多，一定记得加分布式锁，避免重复请求，导致创建太多无用线程

        Date startDateTime = DateUtil.parseDateTime("2022-02-02 00:00:00");
        Date endDateTime = DateUtil.parseDateTime("2023-03-24 14:15:00");

        long diff = DateUtil.between(endDateTime, startDateTime, DateUnit.DAY);
        // 5天 切割避免 超过阻塞队列导致数据丢失
        long section = 5L;
        if (diff > section) {
            // 1. 按照section天拆分  拆成多组待执行的线程
            List<DateExecuteMaps> executeMaps = executeMaps(startDateTime, endDateTime, section, diff);

            ExecutorService newFixedThreadPool = ThreadPoolUtil.newFixedThreadPool;
            // 60个线程
            for (int i = 0; i < executeMaps.size(); i++) {
                DateExecuteMaps plan = executeMaps.get(i);
                CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + ":当前任务开始时间：" + DateUtil.formatDateTime(plan.getStartDateTime()) + "，结束时间：" + DateUtil.formatDateTime(plan.getEndDateTime()));
                    try {
                        Thread.sleep(2000L);
//                        System.out.println("不用线程池我显示出不来~");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }, threadPoolExecutor); // TODO 假设有60个线程同时执行
//                });// FIXME 这样是坏的，不用线程池，每一次运行结果都不一样，有时候能运行，有时候运行不了，多运行几次试试
//                }, singleExecutor); // FIXME hutool的SingleExecutor 线程池  如果用了他就和单线程没什么鬼区别
                }, newFixedThreadPool); // FIXME hutool的SingleExecutor 线程池  如果用了他就和单线程没什么鬼区别


            }

            newFixedThreadPool.shutdown();

        }




    }

    /**
     * @Description: 按照时间区间拆分
     * @Param: [section] 区间大小
     * @return: [{
     * "startDateTime" : xxx,
     * "endDateTime": xxx
     * }]
     * @Author: sym
     * @Date: 2023/3/22
     */
    private static List<DateExecuteMaps> executeMaps(Date startDateTime, Date endDateTime, Long section, Long diff) {
        List<DateExecuteMaps> result = new ArrayList<>();

        int maxNum = diff.intValue() / section.intValue();
        for (int i = 0; i < diff / section; i++) {
            DateExecuteMaps map = null;
            if (i == 0) {
                map = DateExecuteMaps.builder()
                        .startDateTime(startDateTime)
                        .endDateTime(DateUtil.offsetDay(startDateTime, section.intValue()))
                        .build();
            } else {
                map = DateExecuteMaps.builder()
                        .startDateTime(DateUtil.offsetDay(startDateTime, section.intValue() * i))
                        .endDateTime(DateUtil.offsetDay(startDateTime, section.intValue() * (i + 1)))
                        .build();
            }
            result.add(map);
        }

        // 按照结束时间升序排序
        result = result.stream().sorted(Comparator.comparing(DateExecuteMaps::getEndDateTime)).collect(Collectors.toList());

        if (result.get(result.size() - 1).getEndDateTime().compareTo(endDateTime) != 0) {
            result.add(DateExecuteMaps.builder()
                    .startDateTime(DateUtil.offsetDay(startDateTime, section.intValue() * maxNum))
                    .endDateTime(endDateTime)
                    .build());
        }

        return result;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    @Data
    static class DateExecuteMaps {
        private Date startDateTime;
        private Date endDateTime;
    }

    public static void asyncTest(Date startDateTime, Date endDateTime) throws ExecutionException, InterruptedException {

    }

}
