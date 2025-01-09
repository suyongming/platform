package com.sym.demo.lambda;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
public class LambdaAuthor {
    private Integer id;

    private String name;

    private Integer age;

    private String intro;

    private List<LambdaBook> books;

    public static List<LambdaAuthor> getDemoData() {
        LambdaBook 西游记 = LambdaBook.builder().id(3).name("西游记").category("名著,中国").score(100).intro("书本1").build();
        LambdaBook 百年孤独 = LambdaBook.builder().id(3).name("百年孤独").category("名著,哥伦比亚").score(94).intro("书本2").build();
        LambdaBook 龙珠 = LambdaBook.builder().id(3).name("龙珠").category("漫画,日本").score(85).intro("书本3").build();
        LambdaBook 大剑 = LambdaBook.builder().id(4).name("剑风传奇").category("漫画,日本").score(80).intro("书本4").build();



        LambdaAuthor zhangsan = LambdaAuthor.builder().id(1).name("张三").age(3).intro("~我读书少...")
                .books(new ArrayList<LambdaBook>() {{ add(西游记); }})
                .build();
        LambdaAuthor lisi = LambdaAuthor.builder().id(2).name("李四").age(4).intro("~我不看漫画...")
                .books(new ArrayList<LambdaBook>() {{ add(西游记); }})
                .books(new ArrayList<LambdaBook>() {{ add(百年孤独); }})
                .build();
        LambdaAuthor wangwu = LambdaAuthor.builder().id(3).name("王五").age(5).intro("~我看不懂历史群像文...")
                .books(new ArrayList<LambdaBook>() {{ add(西游记); }})
                .books(new ArrayList<LambdaBook>() {{ add(龙珠); }})
                .books(new ArrayList<LambdaBook>() {{ add(大剑); }})
                .build();


        return new ArrayList<LambdaAuthor>(){{
            add(zhangsan);
            add(lisi);
            add(wangwu);
        }};
    }

}
