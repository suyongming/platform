package com.sym.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sym.demo.entity.Person;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * @description
 * @Author: sym
 * @Date: 2021/8/17 17:20
 */
public class Demo {
    public static void main(String[] args) {
        // 下面数据中有两个 开发人员叫 Addison 的, 因为是jdk17 这样去重
        Set<String> firstNames = new HashSet<>();
        // 去重后的集合

        // 普通for,记得对数组判空
        for (int i = 0; i < javaProgrammers.size(); i++) {
            Person person = javaProgrammers.get(i);
            // 干掉重复的 名字叫Addison的家伙 多条件多条件多条件！！！  Addison 一共有三位
            if(firstNames.add(person.getFirstName()) == false) {
                System.out.println("重复了：》》》》》》》》" + person.getFirstName());
            }
        }


    }

    private static List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Addison", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Addison", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };

    private static List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "PHP programmer", "male", 34, 1550));
            add(new Person("Ethan", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Elsdon", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Floyd", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Elsdon", "Jaycob", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };


}
