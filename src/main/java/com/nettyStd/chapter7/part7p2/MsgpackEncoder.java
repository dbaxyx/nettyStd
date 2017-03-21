package com.nettyStd.chapter7.part7p2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created by xiaoyx on 2017/3/21.
 */
/*代码清单7-1*/
public class MsgpackEncoder extends MessageToByteEncoder {
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack = new MessagePack();
        //Serialize
        byte[] raw = msgpack.write(o);
        byteBuf.writeBytes(raw);
    }
}
