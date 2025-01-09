package com.sym.common.utils.delay;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description
 * @Author: sym
 * @Date: 2024/5/10 15:24
 */
@Slf4j
@Component
public class DelayConsumerExample implements DelayMessageExecutor{

    public static String GROUP_NAME = "demo";


    @Override
    public String getGroupName() {
        return GROUP_NAME;
    }

    @Override
    public boolean execute(DelayMessage delayMessage) {
        // 没有消息体，不予执行
        if (Objects.isNull(delayMessage.getBody())) {
            return false;
        }
        try {
            log.info("[DelayConsumerExample], Start: {}", JSONObject.toJSONString(delayMessage.getBody()));
        } catch (Exception e) {
            log.error("[DelayConsumerExample], error, ", e);
            return false;
        }

        log.info("[DelayConsumerExample], end: {}", JSONObject.toJSONString(delayMessage.getBody()));
        return true;
    }
}
