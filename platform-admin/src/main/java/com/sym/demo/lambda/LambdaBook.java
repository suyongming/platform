package com.sym.demo.lambda;

import lombok.*;

/**
 * @description
 * @Author: sym
 * @Date: 2024/8/12 16:50
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LambdaBook {
    private Integer id;

    private String name;

    private String category;

    private Integer score;

    private String intro;
}
