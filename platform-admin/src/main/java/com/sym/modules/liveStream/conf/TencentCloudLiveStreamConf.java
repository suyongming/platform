package com.sym.modules.liveStream.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description
 * @Author: sym
 * @Date: 2024/6/13 15:43
 */
@Component
public class TencentCloudLiveStreamConf {
    @Value("${wx.appid: wx50d28b11ad54c282}")
    private String appid;

    @Value("${wx.secret: 64345fcf73d24b62368b8629b0cbfcda}")
    private String secret;






}
