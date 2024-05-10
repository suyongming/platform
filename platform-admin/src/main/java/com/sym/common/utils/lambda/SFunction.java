package com.sym.common.utils.lambda;

import java.io.Serializable;

/**
 * nonthing to do
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface SFunction<T, R> extends Serializable {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}