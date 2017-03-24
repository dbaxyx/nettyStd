package com.nettyStd.chapter9.part9p1.marshalling;

import com.nettyStd.chapter8.part8p1.protobuf.SubscribeReqProto;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyx on 2017/3/24.
 */
public class SubReqClientHandler {
    public SubReqClientHandler() {

    }

    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder req = SubscribeReqProto.SubscribeReq.newBuilder();
        req.setAddress(1,"Najing YuhuaTai");
        req.setProductName("Netty Book For Marshalling");
        req.setSubReqID(i);
        req.setUserName("Lilinfeng");
        return req.build();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response : [" + msg + "]");
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
