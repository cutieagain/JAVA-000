package io.kimmking.rpcfx.kyro;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoMsgEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf out) throws Exception {
        Serializer serializer = new KryoSerializer();
        // 1. 将对象转换为byte
        byte[] body = serializer.serialize(msg);
        // 2. 读取消息的长度
        int dataLength = body.length;
        // 3. 先将消息长度写入，也就是消息头
        out.writeInt(dataLength);
        out.writeBytes(body);
    }
}
