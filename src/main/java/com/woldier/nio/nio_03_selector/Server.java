package com.woldier.nio.nio_03_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
*
* description NIO服务端实现
* @author: wang1
* @date: 2023/7/13 16:48
*/
public class Server {
    public static void main(String[] args) {
        try {
            //获取一个socket服务通道
            ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
            //设置监听端口
            serverSocketChannel.bind(new InetSocketAddress(9999));
            //设置成非阻塞
            serverSocketChannel.configureBlocking(false);
            //创建选择器
            Selector selector = Selector.open();
            //绑定通道到选择器,设置为接收通道
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //开始提供服务
            service(serverSocketChannel, selector);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void service(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        while(selector.select()>0){ //当有通道准备就绪 进入while循环,否则阻塞
            Set<SelectionKey> selectionKeys = selector.selectedKeys(); //得到被选择的keyset
            Iterator<SelectionKey> iterator = selectionKeys.iterator(); //获取迭代器
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){ //如果是请求连接
                    ops4Accept(serverSocketChannel, selector);
                }
                else if(selectionKey.isReadable()){ //如果是读请求
                    ops4Read(selectionKey);
                }
                iterator.remove(); //处理完毕,删除
            }
        }
    }

    private static void ops4Read(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();//得到读通道
        ByteBuffer buffer = ByteBuffer.allocate(1024); //创建缓冲
        int len = 0;
        while((len= channel.read(buffer))>0){
            buffer.flip(); //反转读写
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear(); // clear 准备下次写入
        }
    }

    private static void ops4Accept(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();//接受到一个通道
        socketChannel.configureBlocking(false); //设置非阻塞
        socketChannel.register(selector,SelectionKey.OP_READ);//设置为读
    }
}
