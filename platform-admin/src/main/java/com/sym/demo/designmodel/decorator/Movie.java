package com.sym.demo.designmodel.decorator;

/**
 * @description
 * @Author: sym
 * @Date: 2022/10/31 11:25
 */
public class Movie implements Video{

    @Override
    public void play() {
        System.out.println("观看视频");

    }
}
