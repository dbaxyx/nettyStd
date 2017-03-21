package com.nettyStd.chapter7.part7p2;

import com.nettyStd.chapter6.part6p1.UserInfo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by xiaoyx on 2017/3/21.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    public void channelActive(ChannelHandlerContext ctx){
        UserInfo [] infos = UserInfo();
        for(UserInfo infoE : infos){
            ctx.write(infoE);
        }
        ctx.flush();
    }

    private UserInfo[] UserInfo() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setUserName("ABCDEFG --->" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message : " + msg);
        ctx.write(msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
