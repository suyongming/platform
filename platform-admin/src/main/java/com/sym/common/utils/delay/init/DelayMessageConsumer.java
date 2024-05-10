package com.sym.common.utils.delay.init;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.sym.common.constant.RedisKeyConstant;
import com.sym.common.redis.RedisUtils;
import com.sym.common.utils.delay.DelayMessage;
import com.sym.common.utils.delay.DelayMessageExecutor;
import com.sym.common.utils.delay.DelayMessageUtils;
import com.sym.common.utils.delay.threadpool.DelayMessageConsumerThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 延迟消息消费者
 *
 * @author suym
 */
@Slf4j
@Component
public class DelayMessageConsumer implements InitializingBean {

    @Resource
    private DelayMessageUtils delayMessageUtils;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private List<DelayMessageExecutor> delayMessageExecuteList;

    private final ExecutorService executor = new ThreadPoolExecutor(1, 2, 1,
            TimeUnit.DAYS, new LinkedBlockingQueue<>(), new DelayMessageConsumerThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    @Override
    public void afterPropertiesSet() {

        CompletableFuture.runAsync(() -> {
            try {
                while (true) {
                    monitor();
                    Thread.sleep(300);
                }
            } catch (RuntimeException | InterruptedException e) {
                log.error("[DelayMessageConsumer]延迟消息消费失败, ", e);
            }
        }, executor);
    }

    private void monitor() {

        long current = System.currentTimeMillis();

        for (DelayMessageExecutor executor : delayMessageExecuteList) {
            // 分组名
            String groupName = executor.getGroupName();
            // 拼接key
            String groupQueueKey = String.format(RedisKeyConstant.DELAY_MESSAGE_QUEUE, groupName);
            // 查看这个业务下的所有演示队列
            Set<Object> set = redisUtils.rangeByScore(groupQueueKey, 0, current);

            if (CollectionUtils.isEmpty(set)) {
                continue;
            }

            for (Object id : set) {
                String message;
                DelayMessage delayMessage = null;
                try {
                    String messageKey = String.format(RedisKeyConstant.DELAY_MESSAGE_POOL, groupName, id);
                    message = (String) redisUtils.get(messageKey);
                    if (ObjectUtil.isNull(message)) {
                        return;
                    }
                    delayMessage = JSONUtil.toBean(message, DelayMessage.class);
                    log.info("[DelayMessageConsumer]{}分组的{}消息开始消费", groupName, id);
                    executor.execute(delayMessage);
                    log.info("[DelayMessageConsumer]{}分组的{}消息处理成功", groupName, id);
                } catch (RuntimeException e) {
                    log.error("[DelayMessageConsumer]{}分组的{}消息处理失败, ", groupName, id, e);
                } finally {
                    // 无论是否执行成功，都删除
                    delayMessageUtils.delMsgQueue(groupName, String.valueOf(id));
                    if (delayMessage != null) {
                        delayMessageUtils.delMsgPool(delayMessage.getGroupName(), String.valueOf(id));
                    }
                }
            }

        }
    }
}
