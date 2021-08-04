package com.sym.common.enums;

import lombok.Getter;

/**
 * 平台
 * @author suyongming
 */
@Getter
public enum KocPlatformEnum {
    /** */
    西瓜(1),
    快手(2),
    火山(3),
    哔哩哔哩(4),
    抖音(5),
    微博(6),
    小红书(7),
    大众点评(8);

    private Integer code;

    KocPlatformEnum(Integer code) {
        this.code = code;
    }

}
