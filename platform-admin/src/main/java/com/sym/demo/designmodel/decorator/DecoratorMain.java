package com.sym.demo.designmodel.decorator;

/**
 * @description
 * @Author: sym
 * @Date: 2022/10/31 11:36
 */
public class DecoratorMain {
    public static void main(String[] args) {
        Video videoIns = new Movie();
        videoIns = new AdvertisingVideoDecorator(videoIns);
        videoIns.play();

    }
}
