package com.sym.demo.lambda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description
 * @Author: sym
 * @Date: 2024/9/10 15:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramSeries implements Serializable {
    private static final long serialVersionUID = -67921792830732747L;
    /**
     * 自增主键
     */
    private Long id;
    /**
     * 节目id
     */
    private Long programId;
    /**
     * 系列名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
}