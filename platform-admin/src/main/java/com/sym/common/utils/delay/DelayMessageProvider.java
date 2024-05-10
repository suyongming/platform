package com.sym.common.utils.delay;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.sym.common.exception.exception.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 延迟消息生产者
 *
 * @author suym
 */
@Slf4j
@Component
public class DelayMessageProvider {

    @Resource
    private DelayMessageUtils delayMessageUtils;

    /**
     * 发送延迟信息
     *
     * @param delayMessage 消息体
     * @return 是否发送成功
     */
    public boolean sendMessage(DelayMessage delayMessage) {
        checkMsg(delayMessage);
        // 若消息体为空或过期时间为空则不执行
        if (ObjectUtil.isNull(delayMessage.getBody()) || Objects.isNull(delayMessage.getExpireTime())) {
            return false;
        }

        try {
            log.info("[DelayMessageProvider][sendMessage] 发送延迟信息：{}", JSONObject.toJSONString(delayMessage));
            // 向队列中添加消息
            delayMessageUtils.addMsgQueue(delayMessage.getGroupName(), delayMessage.getId(), delayMessage.getExpireTime().getTime());
            // 存入消息池
            delayMessageUtils.addMsgPool(delayMessage);
        } catch (Exception e) {
            log.error("[DelayMessageProvider][sendMessage] 发送延迟信息失败, ", e);
            return false;
        }

        return true;
    }

    private void checkMsg(DelayMessage delayMessage) {
        AssertUtil.isTrue(StringUtils.isNotBlank(delayMessage.getGroupName()), "延迟消息: group name不能为空。");
        AssertUtil.isTrue(StringUtils.isNotBlank(delayMessage.getId()),"延迟消息: 消息ID不能为空。");
        AssertUtil.isTrue(ObjectUtil.isNotNull(delayMessage.getExpireTime()), "延迟消息: 消息过期时间不能为空。");
        AssertUtil.isTrue(ObjectUtil.isNotNull(delayMessage.getBody()), "延迟消息: 消息体不能为空。");
    }
}
