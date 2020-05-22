package com.stark.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("Connect Successful");
        ByteBuf msg = Unpooled.copiedBuffer("First Message\r\n".getBytes());
        ctx.writeAndFlush(msg);
        msg = Unpooled.copiedBuffer("Second Message\r\n".getBytes());
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        String body = (String) msg;
        System.out.println("Receive Message : "+ msg);
    }
}
