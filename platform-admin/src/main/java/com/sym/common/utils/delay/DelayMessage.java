package com.sym.common.utils.delay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 延迟消息主体
 *
 * @author suym
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DelayMessage implements Serializable {

    private static final long serialVersionUID = 1535378549721868294L;

    /**
     * 消息队列组
     */
    private String groupName;

    /**
     * 消息id
     */
    private String id;

    /**
     * 消息体，对应业务内容
     */
    private Object body;

    /**
     * 消息过期时间
     */
    private Date expireTime;
}

