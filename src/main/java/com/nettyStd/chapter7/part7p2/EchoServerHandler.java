package com.nettyStd.chapter7.part7p2;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by xiaoyx on 2017/3/20.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server receive the msgpack message : [" + msg + "]");
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close(); //发生异常，关闭链路
    }
}
