package com.sym.common.enums;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @description
 * @Author: sym
 * @Date: 2023/3/23 12:42
 */
public class Demo2 {
    public static void main(String[] args) {
        // year month day
        Date startDateTime = DateUtil.parseDateTime("2022-02-02 00:00:00");
        int year = DateUtil.year(startDateTime);
        int month = DateUtil.month(startDateTime);
        int day = DateUtil.dayOfMonth(startDateTime);
        System.out.println(year + "-" + month + "-" + day);


    }
}
