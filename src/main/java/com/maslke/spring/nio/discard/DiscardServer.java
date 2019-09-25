package com.maslke.spring.nio.discard;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class DiscardServer {
    private final String server;
    private final int port;

    private ServerSocketChannel serverSocketChannel;

    public DiscardServer(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public void listen() throws Exception {
        Selector selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.configureBlocking(false);
        this.serverSocketChannel.bind(new InetSocketAddress(this.server, this.port));
        System.out.println("服务器启动成功");
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
            while (selectionKeys.hasNext()) {
                SelectionKey key = selectionKeys.next();
                if (key.isAcceptable()) {
                    SocketChannel sc = this.serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
                else if (key.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    int len = 0;
                    while ((len = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                    socketChannel.close();
                }
                selectionKeys.remove();
            }
        }
    }
}
