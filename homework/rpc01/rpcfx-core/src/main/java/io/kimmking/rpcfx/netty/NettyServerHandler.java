package io.kimmking.rpcfx.netty;

import io.kimmking.rpcfx.kyro.NettyMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage nettyMessage;
        try {
            //获取调用的内容
            nettyMessage = (NettyMessage) msg;
            //处理后写返回的内容

            ChannelFuture f = ctx.writeAndFlush(nettyMessage);
            f.addListener(ChannelFutureListener.CLOSE);
        } finally {

        }

    }
}
