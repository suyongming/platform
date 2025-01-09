package com.sym.demo.listener;

import com.alibaba.fastjson.JSONObject;
import com.sym.demo.event.MemberLikeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @description 用户相关监听器
 * @Author: sym
 * @Date: 2025/1/6 15:12
 */
@Slf4j
@Component
public class MemberListener {

    /**
     * 点赞监听
     */
    @Async("asyncTaskExecutor")
    @TransactionalEventListener(MemberLikeEvent.class)
    public void onMemberLikeEvent(MemberLikeEvent event) {
        System.out.println("listener onMemberLikeEvent:" + JSONObject.toJSONString(event));
        log.info("listener onMemberLikeEvent: ｛｝", JSONObject.toJSONString(event));
    }
}
