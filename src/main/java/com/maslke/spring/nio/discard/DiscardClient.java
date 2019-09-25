package com.maslke.spring.nio.discard;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DiscardClient {
    private SocketChannel sc;
    private final String server;
    private final int port;

    public DiscardClient(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public void connect() throws Exception {
        SocketAddress address = new InetSocketAddress(this.server, this.port);
        this.sc = SocketChannel.open(address);
        this.sc.configureBlocking(false);
        while (!this.sc.finishConnect()) {
            System.out.println("客户端连接中");
        }
        System.out.println("客户端连接成功");
    }

    public void send(String message) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        this.sc.write(byteBuffer);
        byteBuffer.clear();
    }

    public void disconnect() throws Exception {
        this.sc.shutdownOutput();
        this.sc.close();
    }

}
