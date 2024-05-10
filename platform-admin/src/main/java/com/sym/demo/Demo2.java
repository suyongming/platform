package com.sym.demo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.math.BigDecimal;

/**
 * @description
 * @Author: sym
 * @Date: 2022/12/20 18:33
 */
public class Demo2 {
    /**
     * 1、卡号生成规则为11位随机数字
     * 2、卡密生成规则为10随机数字+字母，不区分大小写；
     */
    public static void main(String[] args) {
        System.out.println(BigDecimal.valueOf(0.00).compareTo(BigDecimal.valueOf(0.01)) < 0);

    }
}
