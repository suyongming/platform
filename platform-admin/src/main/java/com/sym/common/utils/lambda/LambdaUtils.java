package com.sym.common.utils.lambda;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class LambdaUtils {

    /**
     * SerializedLambda 反序列化缓存
     * :::弱引用
     */
    private static final Map<Class, WeakReference<SerializedLambda>> FUNC_CACHE = new ConcurrentHashMap<>();

    /**
     * 解析 lambda 表达式
     *
     * @param func 需要解析的 lambda 对象
     * @param <T>  类型，被调用的 Function 对象的目标类型
     * @return 返回解析后的结果
     */
    public static <T> SerializedLambda resolve(SFunction<T, ?> func) {
        Class clazz = func.getClass();
        if(FUNC_CACHE.containsKey(clazz)) {
            WeakReference<SerializedLambda> serializedLambdaWeakReference = FUNC_CACHE.get(clazz);
            SerializedLambda serializedLambda = serializedLambdaWeakReference.get();
            if(serializedLambda != null) {
                return serializedLambda;
            }
        }
        return Optional.ofNullable(FUNC_CACHE.get(clazz))
            .map(WeakReference::get)
            .orElseGet(() -> {
                SerializedLambda lambda = SerializedLambda.resolve(func);
                FUNC_CACHE.put(clazz, new WeakReference<>(lambda));
                return lambda;
            });
    }

}
