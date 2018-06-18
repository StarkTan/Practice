package com.stark.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * echo 服务器
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8001);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try (OutputStream output = socket.getOutputStream();
                     InputStream input = socket.getInputStream();) {
                    int bufSize = 1024;
                    byte[] buffer = new byte[bufSize];
                    int index = 0;
                    while (true) {
                        int read = input.read();
                        if (read != -1) {
                            buffer[index++] = (byte) read;
                            if (read == 10) {
                                output.write(buffer, 0, index);
                                output.flush();
                                index = 0;
                            }
                            if (index >= bufSize) {
                                output.write("The message is too long\n".getBytes());
                                output.flush();
                                index = 0;
                            }
                        } else {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
