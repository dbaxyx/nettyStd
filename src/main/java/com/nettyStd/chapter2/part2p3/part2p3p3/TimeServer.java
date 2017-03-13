package com.nettyStd.chapter2.part2p3.part2p3p3;

/**
 * Created by xiaoyx on 2017/3/13.
 */

/*代码清单2-8*/
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length >0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认值
            }
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
