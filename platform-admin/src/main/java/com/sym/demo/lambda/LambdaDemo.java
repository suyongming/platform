package com.sym.demo.lambda;



import com.sym.demo.entity.Person;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by su_yongming on 2021/2/8.
 */
public class LambdaDemo {

    public static void main(String[] args) {
        // 1. Print programmers name
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        System.out.println();

        // 2. 遍历 php部门,得到所有人的【全名】, 并去重; Return List<String> 或 String
        String names = phpProgrammers.stream()
                // 如果只是根据【姓氏】单一个去重条件,解开line 22的注释,再注释掉23即可。
//                .map(Person::getLastName)
                // 为了满足全名的条件 需要自定义一个manyConditions方法
                .map(person -> manyConditions(person.getFirstName(), person.getLastName()))
                .map(String::valueOf)
                .distinct()
                // return String类型 并且逗号隔开
                .collect(Collectors.joining(","));
                // List<String>
//                .collect(Collectors.toList());
        System.out.println(names);

        // 3. 找出年纪最大的程序员
        Person oldPerson = javaProgrammers.stream().max(Comparator.comparingInt(Person::getAge)).get();
        System.out.println("年纪最大的程序员" + oldPerson.toString());

        // 4.按性别分组，再得出他们的总工资 summingDouble,平均工资 averagingInt
        Map<String, Double> groupSum = javaProgrammers.stream().collect(Collectors.groupingBy(
                Person::getGender,
                Collectors.averagingDouble(Person::getSalary)
        ));
        groupSum.forEach((k, v) -> {
            System.out.println();
            System.out.printf("性别:%s,他们的平均工资:%s", k, v);
        });

        // 5.按性别分组找到每组最高工资
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

        // 6.list通过filter(e->(age>30 && gender == 男))则保留 所有 30岁以上的男生,
        List<Person> maleAge30 = javaProgrammers.stream()
                // 调用equals时 注意先过滤掉哪些可能为空的字段
                .filter(person -> StringUtils.isNotBlank(person.getGender()))
                .filter(person -> person.getAge() > 30 && person.getGender().equals("male"))
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("所有30岁以上的男性" + maleAge30);

        /* 7.简单对象取交集和差集
         * 交集 24
         * 差集 135 6 (要包含两个List的差集)
         * */
        List<String> strList1 = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
        }};
        List<String> strList2 = new ArrayList<String>() {{
            add("2");
            add("4");
            add("6");
        }};

        List<String> strJiaoji = strList1.stream().filter(
                str -> strList2.contains(str)
        ).collect(Collectors.toList());
        System.out.println("简单交集: " + strJiaoji);

        List<String> str1Chatji = strList1.stream().filter(node -> !strList2.contains(node)).collect(Collectors.toList());
        List<String> str2Chatji = strList2.stream().filter(node -> !strList1.contains(node)).collect(Collectors.toList());
        str1Chatji.addAll(str2Chatji);
        System.out.println("简单差集: " + str1Chatji);

        // TODO 8.找到两个部门下所有同名同姓的程序员【其实就是取交集DTO】(这是多条件 名:firstName ,姓:lastName)
        // 8.0 先合并各部门下所有人
        List<Person> allProgrammers = new ArrayList<Person>();
        allProgrammers.addAll(javaProgrammers);
        allProgrammers.addAll(phpProgrammers);

        // 方法一 利用 HashSet + filter 来做
        HashSet<String> isExits = new HashSet<>();
        HashSet<String> jiaojiSet = new HashSet<>();
        allProgrammers.stream()
                // 8.1.1 如果姓名都不为空 则保留
                .filter(p -> StringUtils.isNotBlank(p.getLastName()) && StringUtils.isNotBlank(p.getFirstName()))
                // 8.1.2 利用HashSet的特性 如果add返回 false 则说明他是重复的(这意味这他是交集)
                .forEach(person -> {
                    if (isExits.add(person.getFirstName() + person.getLastName()) == false) {
                        jiaojiSet.add(person.getFirstName() + person.getLastName());
                    }
                });
        // 8.1.3 根据条件取复杂条件的交集
        List<Person> jiaoji1 = allProgrammers.stream().filter(
                // 如果需要差集 把【!】删掉即可
                person -> !jiaojiSet.add(person.getFirstName() + person.getLastName())).collect(Collectors.toList());
        System.out.printf("复杂对象交集>>>>【%s】", jiaoji1);
        System.out.println();


        // 方法二 利用 lambda 的 GroupingBy来做交集
        List<Person> jiaoji2 = new ArrayList<>();
        List<Person> chaji2 = new ArrayList<>();

        Map<String, List<Person>> groupByNameMap = allProgrammers.stream()
                // 8.2.1不管什么时候防止空指针是 基本素质
                .filter(p -> StringUtils.isNotBlank(p.getLastName()) && StringUtils.isNotBlank(p.getFirstName()))
                // 8.2.2 TODO manyConditions 实现了多条件分组
                .collect(Collectors.groupingBy(person -> manyConditions(person.getFirstName(), person.getLastName())));

        groupByNameMap.forEach((k, v) -> {
            if (v.size() > 1) {
                // 8.2.3 如果大于1则说明这个分组下重复了,也就意味着这是交集
                jiaoji2.addAll(v);
            } else {
                chaji2.addAll(v);
            }
        });

        System.out.println("基于多条件groupingBy 实现取交集:" + jiaoji2);
        System.out.println("基于多条件groupingBy 实现取差集:" + chaji2);

        // 9 所有部门下同岁同性别别的人
        List<Person> allPerson = new ArrayList<>();
        allPerson.addAll(javaProgrammers);
        allPerson.addAll(phpProgrammers);
        Map<String, List<Person>> friend = allPerson.stream().collect(Collectors.groupingBy(person -> manyConditions(person.getAge() + "", person.getGender())));
        friend.forEach((k, v) -> {
            if (v.size() > 1) {
                System.out.println(k + ":" + v);
            }
        });

        /* 10. String类型 字符串根据逗号隔开再去重 【,】
         *
         * 根据阿里开发的规范
         * 【推荐】使用索引访问用String的split方法得到的数组时，需做最后一个分隔符后有无内容的检查，
         * 否则会有抛IndexOutOfBoundsException的风险。
         */
        String str1 = "11,22,33,44,55,,11,22,,";
        List<String> stringList = Stream.of(str1.split(","))
                .filter(el-> StringUtils.isNotBlank(el))
                .distinct()
                .collect(Collectors.toList());

        stringList.stream().forEach(
                System.out::println
        );



    }

    /**
     * 多条件grouping by
     * 可以发现 ElsdonJaycob这个名字 出现了三次
     *
     * @param param String... 相当于 String[] 多个未知条件
     */
    private static String manyConditions(String... param) {
        // Collectors.joining(",") 可以逗号隔开 返回String
        String resultConditions = Arrays.stream(param).collect(Collectors.joining());
//        System.out.println("组合条件为:" + resultConditions);
        return resultConditions;
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

