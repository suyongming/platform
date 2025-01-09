package com.sym.demo.event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @description
 * @Author: sym
 * @Date: 2025/1/6 14:57
 */
@Getter
public class MemberLikeEvent extends ApplicationEvent {
    /**
     * 点赞人ID
     */
    private Long userId;

    /**
     * 媒体ID
     */
    private Long mediaId;

    public MemberLikeEvent(Object source, Long userId, Long mediaId) {
        super(source);
        this.userId = userId;
        this.mediaId = mediaId;


    }
}
