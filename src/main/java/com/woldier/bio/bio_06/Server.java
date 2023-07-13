package com.woldier.bio.bio_06;

import com.woldier.bio.bio_03.AbstractSocketRunner;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
*
* description 端口转发 ,做的是,一个客户端发送,其他客户端都能收到
* @author: wang1
* @date: 2023/7/13 10:03
*/
@Slf4j
public class Server {
    ArrayList<Socket> socketArrayList = new ArrayList<>();
    public  void run() {
        try {
            log.debug("==============服务启动================");
            //创建socket服务
            ServerSocket serverSocket = new ServerSocket(9898);
            Socket socket ;
            while(true){
                //建立了一个socket连接
                socket = serverSocket.accept();
                log.debug("==============建立了一个Socket连接================");
                //将socket放入连接集合中
                socketArrayList.add(socket);
                //开启新的线程处理集合
                new Thread(new HandelServer(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private class HandelServer extends AbstractSocketRunner{
        protected HandelServer(Socket socket) {
            super(socket);
        }

        @Override
        public void run() {
            try (
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
            ){
                    String b;
                    while((b=bufferedReader.readLine())!=null) {
                        log.debug("==============接收到消息发送给客户端================");
                        sendMsg(b);
                    }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                socketArrayList.remove(socket);
            }
        }

        private void sendMsg(String msg) {
            socketArrayList.stream()
                    .filter(e-> e!=socket) //过滤掉自己
                    .forEach(socket->{
                    //对socket发消息
                        try {
                            OutputStream outputStream = socket.getOutputStream();
                            PrintStream printStream = new PrintStream(outputStream);
                            printStream.println(msg);
                            printStream.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}
