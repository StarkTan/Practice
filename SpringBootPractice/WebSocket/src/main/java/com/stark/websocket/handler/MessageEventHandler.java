package com.stark.websocket.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import io.socket.client.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Socket IO 消息事件处理器
 */
@Component
public class MessageEventHandler {

    private final SocketIOServer server;

    private static final Logger logger = LoggerFactory.getLogger(MessageEventHandler.class);

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    //添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            server.getAllClients().forEach(new Consumer<SocketIOClient>() {
                @Override
                public void accept(SocketIOClient socketIOClient) {
                    socketIOClient.sendEvent(Socket.EVENT_MESSAGE, "new join:" + client.getSessionId());
                }
            });
        } else {
            logger.error("客户端为空");
        }
    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("客户端断开连接, sessionId=" + client.getSessionId().toString());
        client.disconnect();
    }

    // 消息接收入口
    @OnEvent(value = "hello")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, String msg) {
        if (ackRequest.isAckRequested()) {
            ackRequest.sendAckData("received");
        }
        server.getAllClients().forEach(new Consumer<SocketIOClient>() {
            @Override
            public void accept(SocketIOClient socketIOClient) {
                socketIOClient.sendEvent(Socket.EVENT_MESSAGE, msg);
            }
        });
    }
}
