package com.sym.demo.designmodel.decorator;

/**
 * @description 广告视频装饰者
 * @Author: sym
 * @Date: 2022/10/31 11:27
 */
public class BaseVideoDecorator implements Video {
    private Video video;

    public BaseVideoDecorator(Video video) {
        this.video = video;
    }

    @Override
    public void play() {
        video.play();

    }


}
