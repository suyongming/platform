package com.sym;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.sym.common.utils.delay.DelayConsumerExample;
import com.sym.common.utils.delay.DelayMessage;
import com.sym.common.utils.delay.DelayMessageProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @description
 *
 * @Author: sym
 * @Date: 2024/5/10 14:58
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class DelayMessageTest {

    @Resource
    private DelayMessageProvider delayMessageProvider;

    /**
     * 参考 {@link com.sym.common.utils.delay.DelayConsumerExample}
     */
    @Test
    public void runDelayMessage() {
        DateTime expireTime = DateUtil.offsetSecond(new Date(), 10);
        delayMessageProvider.sendMessage(
                DelayMessage.builder()
                        .groupName(DelayConsumerExample.GROUP_NAME)
                        .body("张三哈哈哈")
                        .expireTime(expireTime)
                        .id(UUID.randomUUID().toString().replaceAll("-",""))
                .build());

        System.out.println("预计发送时间:" + DateUtil.formatDateTime(expireTime));
    }
}
