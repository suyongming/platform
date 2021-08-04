package com.sym.demo.pachong;

import lombok.Data;

/**
 * @description
 * @Author: sym
 * @Date: 2021/6/2 20:52
 */
@Data
public class UserCartReptileVo {
    /** 名字*/
    private String nickName;
    /** 粉丝数*/
    private String fansNum;
    /** 头像地址*/
    private String headImgUrl;

    private Integer gender;
}
