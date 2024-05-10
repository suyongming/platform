package com.sym.demo.designmodel.decorator;

/**
 * @description 广告视频装饰者
 * @Author: sym
 * @Date: 2022/10/31 11:27
 */
public class AdvertisingVideoDecorator extends BaseVideoDecorator {

    public AdvertisingVideoDecorator(Video video) {
        super(video);
    }

    @Override
    public void play() {
        advertisingPlay();
        super.play();

    }

    /**
    * @Description:  前置广告
    * @Param:
    * @return:
    * @Author: sym
    * @Date: 2022/10/31
    */
    private void advertisingPlay() {
        System.out.println("播放广告");
    }

}
