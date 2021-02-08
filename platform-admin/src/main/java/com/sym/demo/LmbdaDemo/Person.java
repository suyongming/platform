package com.wuxi.util.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Created by su_yongming on 2021/2/8.
 */
@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@ToString
public class Person {

    //
    private String firstName;

    private String lastName;

    private String job;

    private String gender;

    private Integer age;
    /** 工资*/
    private Integer salary;

}
