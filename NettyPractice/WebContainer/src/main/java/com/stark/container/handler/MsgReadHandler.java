package com.stark.container.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by Stark on 2018/5/2.
 * 用于打印http请求和http返回的信息内容
 */
public class MsgReadHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            System.out.println("检查到请求");
            FullHttpRequest request = (FullHttpRequest) msg;
            System.out.println("获取请求地址：");
            System.out.println(request.uri());
            System.out.println("获取请求方法：");
            System.out.println(request.method());
            System.out.println("获取请求内容：");
            ByteBuf content = request.content();
            byte[] bytes = new byte[content.readableBytes()];
            content.readBytes(bytes);
            System.out.println(new String(bytes));
            System.out.println("获取请求头");
            List<Map.Entry<String, String>> headers = request.headers().entries();
            for(Map.Entry<String,String> head:headers){
                System.out.println(head.getKey()+" : "+head.getValue());
            }
        }
        if(msg instanceof FullHttpResponse){
            System.out.println("检查到返回");
        }
        ctx.fireChannelRead(msg);
    }
}
