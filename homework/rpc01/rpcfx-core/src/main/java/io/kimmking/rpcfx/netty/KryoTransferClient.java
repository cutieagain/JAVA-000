package io.kimmking.rpcfx.netty;

import io.kimmking.rpcfx.kyro.KryoMsgDecoder;
import io.kimmking.rpcfx.kyro.KryoMsgEncoder;
import io.kimmking.rpcfx.kyro.NettyMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class KryoTransferClient {
    private String host;
    private int port;
    private NettyMessage message;

    public KryoTransferClient(String host, int port, NettyMessage message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    public void send() throws InterruptedException {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 配置启动辅助类
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new KryoMsgEncoder());
                            ch.pipeline().addLast(new KryoMsgDecoder());
                            ch.pipeline().addLast(new NettyClientHandler(message));
                        }
                    });
            // 异步连接服务器，同步等待连接成功
            ChannelFuture f = b.connect(host, port).sync();
            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        NettyMessage msg = new NettyMessage();
//        msg.setName("Client");
//        msg.setBrand("changcheng");
//        msg.setSpeed(100);
//        msg.setPrice(12.5f);
//        KryoTransferClient client = new KryoTransferClient("127.0.0.1", 8889, msg);
//        client.send();
//    }
}
