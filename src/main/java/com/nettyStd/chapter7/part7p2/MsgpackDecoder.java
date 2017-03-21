package com.nettyStd.chapter7.part7p2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by xiaoyx on 2017/3/21.
 */
/*代码清单7-2*/
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, length);
        MessagePack msgpack = new MessagePack();
        list.add(msgpack.read(array));
    }
}
