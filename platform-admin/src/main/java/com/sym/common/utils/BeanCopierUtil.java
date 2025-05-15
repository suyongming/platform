package com.sym.common.utils;


import com.github.pagehelper.Page;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author Jasper.sun
 * @Date 2021/7/20 13:38
 * 使用cglib的BeanCopier
 * 对create进行优化，同类型只创建1次
 */
public class BeanCopierUtil {
    /**
     * BeanCopier的缓存
     */
    static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * BeanCopier的copy
     * @param source 源文件的
     * @param target 目标文件
     * @param converter 转换器
     */
    public static void copy(Object source, Object target, Converter converter) {
        String key = genKey(source.getClass(), target.getClass());
        BeanCopier beanCopier;
        if (BEAN_COPIER_CACHE.containsKey(key)) {
            beanCopier = BEAN_COPIER_CACHE.get(key);
        } else {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_CACHE.put(key, beanCopier);
        }
        beanCopier.copy(source, target, converter);
    }

    /**
     * BeanCopier的copy
     * @param source 源文件的
     * @param target 目标文件
     */
    public static void copy(Object source, Object target){
        BeanCopierUtil.copy(source,target,null);
    }

    /**
     * 复制并返回
     * @param source 源对象
     * @param target 目标类型
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> target, Converter converter){
        if(source==null||target==null){
            return null;
        }
        T obj = null;
        try {
            obj = target.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BeanCopierUtil.copy(source,obj,converter);
        return obj;
    }

    public static <T> T copy(Object source, Class<T> target){
        return copy(source,target,null);
    }

    /**
     * 集合复制
     * @param sourceList
     * @param target
     * @param converter
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List sourceList , Class<T> target, Converter converter){
        if(sourceList==null||sourceList.isEmpty()||target==null){
            return sourceList;
        }
        //Page对象处理
        if(sourceList instanceof Page){
            Page page = (Page) sourceList;
            List l =  (List<T>) page.stream().map(item->{
                return copy(item,target,converter);
            }).collect(Collectors.toList());
            Page page1 = new Page();
            page1.setTotal(page.getTotal());
            page1.setPageNum(page.getPageNum());
            page1.setPages(page.getPages());
            page1.addAll(l);
            return page1;
        }
        return (List<T>) sourceList.stream().map(item->{
            return copy(item,target,converter);
        }).collect(Collectors.toList());
    }
    public static <T> List<T> copyList(List sourceList , Class<T> target){
        return copyList(sourceList,target,null);
    }
    /**
     * 生成key
     * @param srcClazz 源文件的class
     * @param tgtClazz 目标文件的class
     * @return string
     */
    private static String genKey(Class<?> srcClazz, Class<?> tgtClazz) {
        return srcClazz.getName() + tgtClazz.getName();
    }
}
