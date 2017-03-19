package com.nettyStd.chapter3.part3p3;

import com.nettyStd.chapter2.part2p3.part2p3p5.TimeClientHandle;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaoyx on 2017/3/17.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TimeClientHandler.class);

    private final ByteBuf firstMessage;

    public TimeClientHandler() {
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Now is : " + body);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //释放资源
        logger.warn("Unexpected excetption from downstream : " + cause.getMessage());
        ctx.close();
    }
}
