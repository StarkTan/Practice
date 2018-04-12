package com.stark.container;

import com.stark.container.server.WebServer;

/**
 * Created by Stark on 2018/4/3.
 * 容器的启动类
 * 功能：
 * 启动端口监听
 */
public class NettyWeb {
    public static void main(String[] args) throws Exception {
        new WebServer().run(8080, false);
    }
}
