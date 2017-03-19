package com.nettyStd.chapter3.part3p2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Created by xiaoyx on 2017/3/17.
 */
/*代码清单3-1*/
public class ChildChannelHandler extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new TimeServerHandler());
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length>0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        new TimeServer().bind(port);
    }
}
