package io.kimmking.rpcfx.kyro;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class KryoMsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Serializer serializer = new KryoSerializer();
        if (in.readableBytes() < 4) {
            return;
        }
        // 标记当前readIndex的位置
        in.markReaderIndex();
        // 读取传送过来的消息长度，ByteBuf的 readInt() 方法会让它的readIndex+4
        int dataLength = in.readInt();
        if (dataLength <= 0) {// 如果读到的消息长度不大于0，这是不应该出现的情况，关闭连接
            ctx.close();
        }
        if (in.readableBytes() < dataLength) { // 说明是不完整的报文，重置readIndex
            in.resetReaderIndex();
            return;
        }

        // 至此，读取到一条完整报文
        byte[] body = new byte[dataLength];
        in.readBytes(body);

        // 将bytes数组转换为我们需要的对象
        Object obj = serializer.deserialize(body, NettyMessage.class);
        out.add(obj);
    }
}
