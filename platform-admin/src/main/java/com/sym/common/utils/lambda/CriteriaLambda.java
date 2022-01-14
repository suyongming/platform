package com.sym.common.utils.lambda;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description
 * @Author: sym
 * @Date: 2022/1/14 13:53
 */
public class CriteriaLambda<T> extends FunctionColumns<T>  {

    private Example.Criteria criteria;

    public CriteriaLambda(ExampleLambda<T> exampleLambda) {
        criteria = exampleLambda.end().createCriteria();
    }

    protected Example.Criteria getCriteria () {
        return this.criteria;
    }

    /**
     * mysql and =
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andEqualTo(SFunction<T, ?> tsFunction, Object value) {
        criteria = this.criteria.andEqualTo(columnToString(tsFunction), value);
        return this;
    }

    /**
     * mysql and =
     *
     * @param condition  ==true 執行
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andEqualTo(boolean condition, SFunction<T, ?> tsFunction, Object value) {
        if (condition) {
            criteria = this.criteria.andEqualTo(columnToString(tsFunction), value);
        }
        return this;
    }

    /**
     * mysql and !=
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andNotEqualTo(SFunction<T, ?> tsFunction, Object value) {
        criteria = this.criteria.andNotEqualTo(columnToString(tsFunction), value);
        return this;
    }

    /**
     * mysql and !=
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andNotEqualTo(boolean condition, SFunction<T, ?> tsFunction, Object value) {
        if (condition) {
            criteria = this.criteria.andNotEqualTo(columnToString(tsFunction), value);
        }
        return this;
    }

    /**
     * mysql in
     *
     * @param tsFunction
     * @param values
     * @return
     */
    public CriteriaLambda<T> andIn(SFunction<T, ?> tsFunction, List values) {
        criteria = this.criteria.andIn(columnToString(tsFunction), values);
        return this;
    }

    /**
     * mysql in
     *
     * @param condition
     * @param tsFunction
     * @param values
     * @return
     */
    public CriteriaLambda<T> andIn(boolean condition, SFunction<T, ?> tsFunction, List values) {
        if (condition) {
            criteria = this.criteria.andIn(columnToString(tsFunction), values);
        }
        return this;
    }

    /**
     * mysql >=
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andGe(SFunction<T, ?> tsFunction, Object value) {
        criteria = this.criteria.andGreaterThanOrEqualTo(columnToString(tsFunction), value);
        return this;
    }

    /**
     * mysql >=
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andGe(boolean condition, SFunction<T, ?> tsFunction, Object value) {
        if (condition) {
            criteria = this.criteria.andGreaterThanOrEqualTo(columnToString(tsFunction), value);
        }
        return this;
    }

    /**
     * mysql <=
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andLe(SFunction<T, ?> tsFunction, Object value) {
        criteria = this.criteria.andLessThanOrEqualTo(columnToString(tsFunction), value);
        return this;
    }

    /**
     * mysql <=
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andLe(boolean condition, SFunction<T, ?> tsFunction, Object value) {
        if (condition) {
            criteria = this.criteria.andLessThanOrEqualTo(columnToString(tsFunction), value);
        }
        return this;
    }


    /**
     * mysql not in
     *
     * @param tsFunction
     * @param values
     * @return
     */
    public CriteriaLambda<T> andNotIn(SFunction<T, ?> tsFunction, List values) {
        criteria = this.criteria.andNotIn(columnToString(tsFunction), values);
        return this;
    }

    /**
     * mysql not in
     *
     * @param condition
     * @param tsFunction
     * @param values
     * @return
     */
    public CriteriaLambda<T> andNotIn(boolean condition, SFunction<T, ?> tsFunction, List values) {
        if (condition) {
            criteria = this.criteria.andNotIn(columnToString(tsFunction), values);
        }
        return this;
    }

    /**
     * mysql or like
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andLike(SFunction<T, ?> tsFunction, String value) {
        criteria = this.criteria.andLike(columnToString(tsFunction), "%" + value + "%");
        return this;
    }
    /**
     * mysql or like
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> orLike(boolean condition, SFunction<T, ?> tsFunction, String value) {
        if(condition) {
            criteria = this.criteria.orLike(columnToString(tsFunction), "%" + value + "%");
        }
        return this;
    }

    /**
     * mysql like
     *
     * @param condition  满足执行
     * @param tsFunction
     * @param value
     * @return
     */
    public CriteriaLambda<T> andLike(boolean condition, SFunction<T, ?> tsFunction, String value) {
        if (condition) {
            criteria = this.criteria.andLike(columnToString(tsFunction), "%" + value + "%");
        }
        return this;
    }

    public CriteriaLambda<T> or(SFunction<T, ?> tsFunction, Object value) {
        criteria = this.criteria.orEqualTo(columnToString(tsFunction), value);
        return this;
    }

    public CriteriaLambda<T> or(boolean condition, SFunction<T, ?> tsFunction, Object value) {
        if (condition) {
            criteria = this.criteria.orEqualTo(columnToString(tsFunction), value);
        }
        return this;
    }

    public CriteriaLambda<T> orLike(SFunction<T, ?> tsFunction, String value) {
        criteria = this.criteria.orLike(columnToString(tsFunction), value);
        return this;
    }

    /**
     * 拼接Sql
     * @param appendSql
     * @return
     */
    public CriteriaLambda<T> apply(String appendSql) {
        criteria = this.criteria.andCondition(appendSql);
        return this;
    }

    /**
     * 拼接Sql
     * @param appendSql
     * @return
     */
    public CriteriaLambda<T> apply(boolean condition, String appendSql) {
        if(condition) {
            criteria = this.criteria.andCondition(appendSql);
        }
        return this;
    }

    public Example.Criteria end() {
        return this.criteria;
    }

}
