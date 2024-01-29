package com.swpu.enums;

import lombok.*;

/**
 * @author zhengjian
 * @date 2024-01-29 23:50
 */
@AllArgsConstructor
@Getter
public enum MessageTypeEnum {

    HEARTBEAT_REQUEST_TYPE((byte)2,"心跳请求"),
    HEARTBEAT_RESPONSE_TYPE((byte)3,"心跳响应");

    private final byte code;
    private final String desc;

}
