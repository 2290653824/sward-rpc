package com.swpu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengjian
 * @date 2024-01-29 22:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RpcMessage {

    /**
     * rpc message type
     */
    private byte messageType;
    /**
     * serialization type
     */
    private byte serializeType;
    /**
     * compress type
     */
    private byte compressType;
    /**
     * request id
     */
    private int requestId;
    /**
     * request data
     */
    private Object data;
}
