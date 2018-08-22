package com.stark.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import io.socket.client.Socket;

/**
 * Created by yangjunming on 2017/1/13.
 */
public class DemoSocketServer {


    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient client) {
                String token = client.getHandshakeData().getUrlParams().get("token").get(0);
                if (!token.equals("87df42a424c48313ef6063e6a5c63297")) {
                    client.disconnect();//校验token示例
                }
                System.out.println("sessionId:" + client.getSessionId() + ",token:" + token);
            }
        });

        server.addEventListener(Socket.EVENT_MESSAGE, String.class, new DataListener<String>() {
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                System.out.println("client data:" + data);
                server.getBroadcastOperations().sendEvent(Socket.EVENT_MESSAGE, "hi");
            }
        });

        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }

}