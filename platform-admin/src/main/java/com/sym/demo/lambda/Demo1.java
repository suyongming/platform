package com.sym.demo.lambda;

import com.alibaba.fastjson.JSONObject;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @Author: sym
 * @Date: 2024/8/21 17:41
 */
public class Demo1 {



    public static void main(String[] args) throws UnsupportedEncodingException {
        Long id = 1L;
        List<ProgramSeries> programSeriesList = new ArrayList<ProgramSeries>(){{
            add(ProgramSeries.builder()
                    .id(1L)
                    .build());
        }};


        ProgramSeries operateProgramSeries = programSeriesList.stream()
                .filter(programSeries -> programSeries.getId().equals(id)).findFirst().orElse(null);


        System.out.println(JSONObject.toJSON(operateProgramSeries));
    }
}
