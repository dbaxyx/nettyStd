package com.nettyStd.chapter11.part11p1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by xiaoyx on 2017/3/29.
 */
public class WebSocketServer {
    public void run(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //将请求和应答消息编码或者解码为HTTP消息
                            pipeline.addLast("http-codec", new HttpServerCodec());
                            //将HTTP消息的多个部分组合成一条完成的HTTP消息
                            pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                            //向客户端发送HTML5文件，它主要用于支持浏览器和服务端进行WebSocket通信
                            socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            pipeline.addLast("handler", new WebSocketServerHandler());
                        }
                    });
            Channel ch = b.bind(port).sync().channel();
            System.out.println("Web socket server start at port " + port + ".");
            System.out.println("Open your browser and navigate to http://localhost:" + port + "/");
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0 ){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e ) {
                e.printStackTrace();
            }
        }
        new WebSocketServer().run(port);
    }
}
