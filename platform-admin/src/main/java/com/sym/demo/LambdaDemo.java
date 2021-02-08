package com.wuxi.util.demo;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by su_yongming on 2021/2/8.
 */
public class LambdaDemo {

    public static void main(String[] args) {
        // 1. Print programmers name
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        // 2. 找出年纪最大的程序员
        Person oldPerson = javaProgrammers.stream().max(Comparator.comparingInt(Person::getAge)).get();
        System.out.println();
        System.out.println("年纪最大的程序员" + oldPerson.toString());

        // 3.按性别分组，再得出他们的总工资 summingDouble,平均工资 averagingInt
        Map<String, Double> groupSum = javaProgrammers.stream().collect(Collectors.groupingBy(
                Person::getGender,
                Collectors.averagingDouble(Person::getSalary)
        ));
        groupSum.forEach((k, v) -> {
            System.out.println();
            System.out.printf("性别:%s,他们的平均工资:%s", k, v);
        });

        // 4.按性别分组找到每组最高工资
        Map<String, Optional<Person>> groupMax = javaProgrammers.stream().collect(Collectors.groupingBy(
                Person::getGender,
                Collectors.maxBy(
                        Comparator.comparingInt(Person::getSalary)
                ))
        );
        groupMax.forEach((k, v) -> {
            System.out.println();
            System.out.printf("性别:%s,他们中最高工资的人是【%s】", k, v.get().toString());
        });

        // 5.找到名重复的(firstName)
        List<Person> allProgrammers = new ArrayList<Person>();
        allProgrammers.addAll(javaProgrammers);
        allProgrammers.addAll(phpProgrammers);

        List<String> collect = allProgrammers.stream().map(Person::getFirstName).distinct().collect(Collectors.toList());
        System.out.println(collect);

    }

    private static List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
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
            add(new Person("Elsdon", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Floyd", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };

}
