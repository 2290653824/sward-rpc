package com.swpu.transport;

import com.swpu.entity.RpcRequest;
import com.swpu.entity.RpcResponse;

/**
 * @author zhengjian
 * @date 2024-01-27 17:50
 */
public interface RpcTransport {
    Object sendRequest(RpcRequest request);
}
