package com.nettyStd.chapter4.part4p2;

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

    private int counter;

    private byte[] req;


    public TimeClientHandler() {
         req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    public void channelActive(ChannelHandlerContext ctx)
    {
        ByteBuf message = null;
        for (int i = 0; i <100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Now is : " + body + "; the counter is : " + ++counter);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //释放资源
        logger.warn("Unexpected excetption from downstream : " + cause.getMessage());
        ctx.close();
    }
}
