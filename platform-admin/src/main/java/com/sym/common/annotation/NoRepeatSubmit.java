package com.sym.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author suyongming
 * @date ：2020/4/21 0:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 设置请求锁定时间
     * 1.锁过期时间 秒
     * 如果过期则自动释放锁，防止宕机的情况。
     * 2.根据header 中的token || Authorization 区分是哪个用户的请求
     * @return
     */
    long lockTime() default 10;

}
