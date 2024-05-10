package com.sym.common.utils.delay;

import com.alibaba.fastjson.JSON;
import com.sym.common.constant.RedisKeyConstant;
import com.sym.common.redis.RedisUtils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 延迟消息工具类
 *
 * @author suym
 */
@Component
public class DelayMessageUtils {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 保存具体的消息体
     *
     * @author suym
     * @param delayMessage 消息体
     */
    public void addMsgPool(DelayMessage delayMessage) {
        if (null != delayMessage) {

            String key = String.format(RedisKeyConstant.DELAY_MESSAGE_POOL, delayMessage.getGroupName(), delayMessage.getId());
            redisUtils.set(key, JSON.toJSONString(delayMessage));
            redisUtils.expireAt(key, new Date(delayMessage.getExpireTime().getTime() + 7 * 24 * 3600 * 1000));
        }
    }

    /**
     * 删除具体的消息体
     *
     * @author suym
     * @param groupName 分组
     * @param messageId 消息ID
     */
    public void delMsgPool(String groupName, String messageId) {
        String key = String.format(RedisKeyConstant.DELAY_MESSAGE_POOL, groupName, messageId);
        redisUtils.delete(key);
    }

    /**
     * 向队列中添加消息
     *
     * @author suym
     * @param groupName 分组名
     * @param messageId 消息ID
     * @param score 分钟
     */
    public void addMsgQueue(String groupName, String messageId, long score) {
        String key = String.format(RedisKeyConstant.DELAY_MESSAGE_QUEUE, groupName);
        redisUtils.zSetOperationsAdd(key, messageId, score);
    }

    /**
     * 从队列删除消息
     *
     * @author suym
     * @param groupName 分组名
     * @param messageId 消息ID
     */
    public void delMsgQueue(String groupName, String messageId) {
        String key = String.format(RedisKeyConstant.DELAY_MESSAGE_QUEUE, groupName);
        redisUtils.zSetOperationsRemove(key, messageId);
    }
}
