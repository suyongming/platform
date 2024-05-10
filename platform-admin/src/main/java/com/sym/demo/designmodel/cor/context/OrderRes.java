package com.sym.demo.designmodel.cor.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @Author: sym
 * @Date: 2022/11/1 10:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRes {
    private String orderNo;
}
