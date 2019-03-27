package com.stark.websocket;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SocketIOApp {
    private static Socket socket;
    private static final Logger logger = LoggerFactory.getLogger(SocketIOApp.class);

    public static void main(String[] args) throws URISyntaxException {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;     // 重连尝试次数
        options.reconnectionDelay = 1000;     // 失败重连的时间间隔(ms)
        options.timeout = 20000;              // 连接超时时间(ms)
        options.forceNew = true;
        options.query = "username=test1";
        socket = IO.socket("http://localhost:8000/", options);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("Socket.EVENT_CONNECT_ERROR");
                socket.disconnect();
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("Socket.EVENT_CONNECT_TIMEOUT");
                socket.disconnect();
            }
        }).on(Socket.EVENT_PING, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("Socket.EVENT_PING");
            }
        }).on(Socket.EVENT_PONG, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("Socket.EVENT_PONG");
            }
        }).on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("get msg" + Arrays.toString(args));
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("connection broken");
                socket.disconnect();
            }
        });
        socket.connect();

        socket.emit("hello", "test", (Ack) args1 -> {
            logger.info("Ack Message=" + Arrays.stream(args1).map(Object::toString).collect(Collectors.joining(",")));
        });
    }
}