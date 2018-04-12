package com.stark.container.handler;

import com.stark.servlet.NettyHttpRequest;
import com.stark.servlet.NettyHttpResponse;
import com.stark.testcase.TestServlet;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import javax.servlet.Servlet;
import java.nio.charset.Charset;

import static io.netty.handler.codec.http.HttpMethod.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

/**
 * Created by Stark on 2018/4/3.
 * 请求处理类 接受http请求的类
 */
public class RequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (!request.decoderResult().isSuccess()) {
            sendError(ctx, BAD_REQUEST);
            return;
        }

        if (request.method() != GET) {
            sendError(ctx, METHOD_NOT_ALLOWED);
            return;
        }

        final String uri = request.uri();
        System.out.println(uri);

        NettyHttpRequest nettyRequest = new NettyHttpRequest(request);
        NettyHttpResponse nettyResponse = new NettyHttpResponse();
        Servlet servlet = new TestServlet();
        servlet.service(nettyRequest, nettyResponse);
        FullHttpResponse response = getHttpResponse(nettyResponse);
        // Close the connection as soon as the error message is sent.
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }
    /*输出简单的错误消息
     */
    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        // Close the connection as soon as the error message is sent.
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
    private FullHttpResponse getHttpResponse(NettyHttpResponse response) {
        int statusCode = response.getStatus();
        FullHttpResponse nettyResponse = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.valueOf(statusCode));
        String responseString = response.getResult();
        if (responseString != null) {
            HttpUtil.setContentLength(nettyResponse, responseString.getBytes().length);
            Charset encoding = Charset.forName(response.getCharacterEncoding());
            nettyResponse.content().writeBytes(Unpooled.copiedBuffer(responseString, encoding));
        }
        return nettyResponse;
    }
}
