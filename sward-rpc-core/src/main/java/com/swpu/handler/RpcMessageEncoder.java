package com.swpu.handler;

import com.swpu.constants.RpcConstants;
import com.swpu.entity.RpcMessage;
import com.swpu.enums.MessageTypeEnum;
import com.swpu.generator.BaseJdkIdGenerator;
import com.swpu.generator.IdGenerator;
import com.swpu.utils.compress.Compress;
import com.swpu.utils.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcMessageEncoder extends MessageToByteEncoder<RpcMessage> {

    private IdGenerator idGenerator;

    private Serializer serializer;

    private Compress compress;

    public RpcMessageEncoder() {
        this.idGenerator = new BaseJdkIdGenerator();
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(RpcConstants.MAGIC);
        byteBuf.writerIndex(byteBuf.writerIndex() + 4);// 跳过长度字段
        byteBuf.writeInt(idGenerator.getNextId());//requestId
        byteBuf.writeByte(RpcConstants.VERSION);
        byteBuf.writeByte(rpcMessage.getMessageType());
        byteBuf.writeByte(rpcMessage.getSerializeType());
        byteBuf.writeByte(rpcMessage.getCompressType());
        byteBuf.writerIndex(byteBuf.writerIndex() + RpcConstants.EXTEND_LENGTH);//extend字段
        byte[] body = null;
        int allLength = RpcConstants.HEAD_LENGTH;
        if (MessageTypeEnum.HEARTBEAT_REQUEST_TYPE.getCode() != rpcMessage.getMessageType() ||
                MessageTypeEnum.HEARTBEAT_RESPONSE_TYPE.getCode() == rpcMessage.getMessageType()) {
            byte[] serializedData = serializer.serialize(rpcMessage.getData());//TODO
            body = compress.compress(serializedData);
            allLength += body.length;
        }
        if(body!=null){
            byteBuf.writeBytes(body);
        }
        int writerByte=byteBuf.writerIndex();
        byteBuf.writerIndex(writerByte-allLength+RpcConstants.MAGIC.length);
        byteBuf.writeInt(allLength);
        byteBuf.writerIndex(writerByte);
    }
}