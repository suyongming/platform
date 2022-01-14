package com.sym.common.utils.lambda;


import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * lambda Util Example ,方法可补充
 *
 * @param <T>
 */
public class ExampleLambda<T> extends FunctionColumns<T> {

    private Example example;

    private Example.Criteria criteria;

    private T entity;

    /**
     * 转换
     *
     * @param column
     * @return
     */
    @Override
    public String columnToString(SFunction<T, ?> column) {
        return super.columnToString(column);
    }

    public ExampleLambda(Class<T> eclass) {
        example = new Example(eclass);
        criteria = example.createCriteria();
    }


    /**
     * 仅查询包含的列
     * @param columns
     * @return
     */
    public ExampleLambda<T> selectColumns(SFunction<T, ?>... columns) {
        if(!Objects.isNull(columns)) {
            List<String> collect = Arrays.stream(columns).map(i -> columnToString(i)).collect(Collectors.toList());
            example.selectProperties(collect.toArray(new String[collect.size()]));
        }
        return this;
    }

    /**
     * 增加额外的查询条件
     * @param criteria
     * @return
     */
    public ExampleLambda<T> addCriteria(CriteriaLambda<T> criteria) {
        example.and(criteria.getCriteria());
        return this;
    }

    /**
     * mysql and =
     *
     * @param tsFunction
     * @param value
     * @return
     */
    public ExampleLambda<T> andEqualTo(SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andEqualTo(boolean condition, SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andNotEqualTo(SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andNotEqualTo(boolean condition, SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andIn(SFunction<T, ?> tsFunction, List values) {
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
    public ExampleLambda<T> andIn(boolean condition, SFunction<T, ?> tsFunction, List values) {
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
    public ExampleLambda<T> andGe(SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andGe(boolean condition, SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andLe(SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andLe(boolean condition, SFunction<T, ?> tsFunction, Object value) {
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
    public ExampleLambda<T> andNotIn(SFunction<T, ?> tsFunction, List values) {
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
    public ExampleLambda<T> andNotIn(boolean condition, SFunction<T, ?> tsFunction, List values) {
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
    public ExampleLambda<T> andLike(SFunction<T, ?> tsFunction, String value) {
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
    public ExampleLambda<T> orLike(boolean condition, SFunction<T, ?> tsFunction, String value) {
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
    public ExampleLambda<T> andLike(boolean condition, SFunction<T, ?> tsFunction, String value) {
        if (condition) {
            criteria = this.criteria.andLike(columnToString(tsFunction), "%" + value + "%");
        }
        return this;
    }

    public ExampleLambda<T> or(SFunction<T, ?> tsFunction, Object value) {
        criteria = this.criteria.orEqualTo(columnToString(tsFunction), value);
        return this;
    }

    public ExampleLambda<T> or(boolean condition, SFunction<T, ?> tsFunction, Object value) {
        if (condition) {
            criteria = this.criteria.orEqualTo(columnToString(tsFunction), value);
        }
        return this;
    }

    public ExampleLambda<T> orLike(SFunction<T, ?> tsFunction, String value) {
        criteria = this.criteria.orLike(columnToString(tsFunction), value);
        return this;
    }

    /**
     * 拼接Sql
     * @param appendSql
     * @return
     */
    public ExampleLambda<T> apply(String appendSql) {
        criteria = this.criteria.andCondition(appendSql);
        return this;
    }

    /**
     * 拼接Sql
     * @param appendSql
     * @return
     */
    public ExampleLambda<T> apply(boolean condition, String appendSql) {
        if(condition) {
            criteria = this.criteria.andCondition(appendSql);
        }
        return this;
    }




    /**
     * mysql sort
     *
     * @param tsFunction
     * @return
     */
    public ExampleLambda<T> orderByDesc(SFunction<T, ?> tsFunction) {
        example.orderBy(columnToString(tsFunction)).desc();
        return this;
    }

    /**
     * mysql sort
     *
     * @param value
     * @return
     */
    public ExampleLambda<T> orderByAsc(SFunction<T, ?> tsFunction) {
        example.orderBy(columnToString(tsFunction)).asc();
        return this;
    }

    public Example end() {
        return this.example;
    }
}