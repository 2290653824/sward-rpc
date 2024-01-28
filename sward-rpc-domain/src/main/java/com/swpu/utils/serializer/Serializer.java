package com.swpu.utils.serializer;

import java.io.IOException;

/**
 * @author zhengjian
 * @date 2024-01-28 20:06
 */
public interface Serializer {
    byte[] serialize(Object obj) throws IOException; //序列化

    <T> T deserialize(byte[] bytes,Class<T> clazz) throws IOException, ClassNotFoundException; //反序列化
}
