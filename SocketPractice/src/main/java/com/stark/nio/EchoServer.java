package com.stark.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        server_2(8002);
    }

    /**
     * 阻塞多线程处理连接
     */
    public static void server_0(int port) throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(port));
        while (true) {
            SocketChannel socketChannel = serverChannel.accept();
            new Thread(() -> {
                int bufSize = 1024;
                ByteBuffer buf = ByteBuffer.allocate(bufSize);
                int index = 0;
                byte[] buf0 = new byte[bufSize];
                while (true) {
                    try {
                        int len = socketChannel.read(buf);
                        if (len == -1) break;
                        if (len + index > bufSize) {
                            buf.clear();
                            buf.put("The data is too long\n".getBytes());
                            buf.flip();
                            socketChannel.write(buf);
                        } else {
                            buf.flip();
                            for (int i = 0; i < len; i++) {
                                byte read = buf.get();
                                buf0[index++] = read;
                                if (read == 10) {
                                    buf.compact();
                                    buf.put(buf0, 0, index);
                                    index = 0;
                                    buf.flip();
                                }
                            }
                            if (buf.remaining() != 0)
                                socketChannel.write(buf);
                            buf.compact();
                        }
                        buf.clear();
                    } catch (IOException e) {
                        break;
                    }
                }
            }).start();
        }
    }

    private static class DataCache {
        private byte[] buf0 = new byte[1024];
        private ByteBuffer buf1 = ByteBuffer.allocate(1024);
        private int index = 0;
        private int flag = 0;
    }

    /**
     * 加入Selector 单线程处理所有业务
     */
    public static void server_1(int port) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select(10 * 1000);
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,
                            SelectionKey.OP_READ | SelectionKey.OP_WRITE,
                            new DataCache());
                    iterator.remove();//为啥不移除会有问题
                }
                doRead(key);
                doWrite(key);
            }
        }
    }

    private static void doRead(SelectionKey key) throws Exception {
        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            DataCache cache = (DataCache) key.attachment();
            byte[] buf0 = cache.buf0;
            ByteBuffer buf1 = cache.buf1;
            int index = cache.index;
            buf1.compact();
            int len = channel.read(buf1);
            if (len > 0) {
                buf1.flip();
                for (int i = 0; i < len; i++) {
                    byte read = buf1.get();
                    buf0[index++] = read;
                    if (read == 10) {
                        cache.flag = index - 1;
                    }
                }
                cache.index = index;
            }
        }
    }

    private static void doWrite(SelectionKey key) throws Exception {
        if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            DataCache cache = (DataCache) key.attachment();
            byte[] buf0 = cache.buf0;
            int index = cache.index;
            int flag = cache.flag;
            if (flag > 0) {
                channel.write(ByteBuffer.wrap(buf0, 0, flag + 1));
                int next = 0;
                for (int i = flag + 1; i < index; i++) {
                    buf0[next++] = buf0[i];
                }
                cache.index = next;
                cache.flag = 0;
            }
        }
    }

    private static class MyThread extends Thread {
        private Selector selector;

        protected MyThread(Selector selector) {
            this.selector = selector;
        }

        public Selector getSelector() {
            return selector;
        }

        @Override
        public void run() {
            try {
                for (; ; ) {
                    this.selector.select(10 * 1000);
                    for (SelectionKey key : selector.selectedKeys()) {
                        doRead(key);
                        doWrite(key);
                    }
                }
            } catch (Exception ignored) {

            }
        }
    }

    /**
     * 将上面一个线程的任务使用多个线程执行
     * 使用Telnet 测试效果不是很好（等等使用netty在相同条件下测试,netty 没有让我失望，怎么处理的）
     */
    public static void server_2(int port) throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8002));
        MyThread[] threads = new MyThread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new MyThread(Selector.open());
        }
        new Thread(() -> {
            int count = 0;
            for (; ; ) {
                try {
                    SocketChannel socketChannel = serverChannel.accept();
                    MyThread thread = threads[count++ % 5];
                    socketChannel.configureBlocking(false);
                    socketChannel.register(thread.getSelector(),
                            SelectionKey.OP_READ | SelectionKey.OP_WRITE,
                            new DataCache());
                    if (thread.getState().compareTo(Thread.State.NEW) == 0) {
                        thread.start();
                    }
                } catch (IOException ignored) {

                }
            }
        }).start();
    }
}

