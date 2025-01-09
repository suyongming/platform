package com.sym.demo.lambda;


import com.sym.demo.entity.Person;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by su_yongming on 2021/2/8.
 */
public class LambdaDemo2 {

    public static void main(String[] args) {
//        /*【可推倒，可省略】
//         * 函数式编程只关注具体的(参数列表)->{方法体}
//         * */
//
//        /*
//         * 1.自定义函数接口【匿名内部类，接口下只有一个抽象方法。】
//         * */
//        int calculateNum = calculateNum((left, right) -> left - right);
//        System.out.println("calculateNum >>>>>>>>>" + calculateNum);
//
//        // 2.自定义遍历抽象接口
//        printlnNum(value -> value % 2 == 0);
//
//        // 3. 自定义Function<T, R>: T参数,R返回值
//        Person person = cusFunctionGetOne(javaProgrammers -> javaProgrammers.get(0));
//        System.out.println(person);
//
//        // 4. 匿名内部类案例Demo
//        List<LambdaAuthor> demoData = LambdaAuthor.getDemoData();
//        demoData.stream()
//                .distinct()
//                .filter(lambdaAuthor -> lambdaAuthor.getAge() > 4)
//                .forEach(lambdaAuthor -> System.out.println(lambdaAuthor.getName() + ":" + lambdaAuthor.getIntro()));
//
//        // 5. filter: HashMap Entry
//        Map<String, Integer> mapDemo = new HashMap<String, Integer>(4) {{
//            put("张三", 3);
//            put("李四", 4);
//            put("王五", 5);
//            put("王五", 5);
//
//        }};
//
//        Set<Map.Entry<String, Integer>> entries = mapDemo.entrySet();
//        Stream<Map.Entry<String, Integer>> stream = entries.stream();
//        stream.distinct()
//                .filter(new Predicate<Map.Entry<String, Integer>>() {
//                    @Override
//                    public boolean test(Map.Entry<String, Integer> entry) {
//                        return entry.getValue() > 4;
//                    }
//                })
//                .forEach(System.out::println);

        // 6. anyMatch 遍历短路判断场景: 有任意一个满足 > 30 就直接返回true, 比如一场活动>>多场次，活动下【任意一个】场次都结束， 活动才等于已结束
        boolean anyMatchFlag = DemoData.javaProgrammers.stream()
                .anyMatch(person -> {
                    System.out.println("anyMatch循环中: person.age" + person.getAge() + ":" + (person.getAge() > 30));
                    return person.getAge() > 30;
                });
        System.out.println("anyMatchFlag 最终判断:" + anyMatchFlag);

        // 7. allMatch 遍历短路判断场景: 必须所有条件都要满足，都满足返回true ,任意一个不满足返回:false
        boolean allMatchFlag = DemoData.javaProgrammers.stream()
                .allMatch(person -> {
                    System.out.println("allMatch循环中: person.age" + person.getAge() + ":" + (person.getAge() > 20));
                    return person.getAge() > 20;
                });
        System.out.println("allMatchFlag 最终判断:" + allMatchFlag);

        // 8. noneMatch 使用场景: 就不要考虑用，不然写代码点的时候脑子绕绕的, 和断言思维不一样，保持简单。

        // 9. findAny: 在串行流中，findAny和findFirst几乎没有区别，在串行流中，取的是任意一个。 会返回空的Optional对象
        Optional<Person> optionalPerson = DemoData.javaProgrammers.stream()
                .filter(findAnyPerson -> findAnyPerson.getAge() > 50)
                .findAny();

        optionalPerson.ifPresent(ip-> System.out.println("大于50岁的人：" + ip.getFirstName()));


        // 10. findFirst: 取第一个
        


    }


    /**
     * 自定义方法1
     */
    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        System.out.println();
        int b = 20;
        return operator.applyAsInt(a, b);
    }

    /**
     * 自定义方法2
     */
    public static boolean printlnNum(IntPredicate intPredicate) {
        int array[] = {1, 2, 3, 4, 5, 6, 7};

        for (int i : array) {
            if (intPredicate.test(i)) {
                System.out.println(i);
            }
        }

        return true;
    }

    /**
     * 自定义Function
     * <p>
     * 需求：返回年级大于3的对象集合
     */
    public static <R> com.sym.demo.entity.Person cusFunctionGetOne(Function<List<Person>, Person> function) {
        Person person = function.apply(DemoData.javaProgrammers);
        return person;
    }


}

