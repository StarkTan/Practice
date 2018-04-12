package com.stark.container.server;

import com.stark.container.handler.RequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 数据处理流程配置类
 * 功能：
 * 配置请求的处理流程
 */
public class WebServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public WebServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));//添加加密和解密操作类
        }
        pipeline.addLast(new HttpServerCodec());//是request的编码和response解码的组合
        pipeline.addLast(new HttpObjectAggregator(65536)); //http消息的整合
        pipeline.addLast(new ChunkedWriteHandler());//防止数据量过大造成数据MMO
        pipeline.addLast(new RequestHandler());//数据处理类
    }
}
