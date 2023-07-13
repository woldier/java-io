package com.woldier.nio.nio_03_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * description NIO客户端
 *
 * @author: wang1
 * @date: 2023/7/13 17:12
 */
public class Client {
    public static void main(String[] args) {
        try {
            //创建一个socket通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            // 设置为非阻塞
            socketChannel.configureBlocking(false);
            //创建一个Buffer
            ByteBuffer buf = ByteBuffer.allocate(1024);

            Scanner scan = new Scanner(System.in);
            while (scan.hasNext()) {
                String str = scan.nextLine();
                buf.put((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis())
                        + "\n" + str).getBytes());
                buf.flip();
                socketChannel.write(buf);
                buf.clear();
            }
    //关闭通道
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
