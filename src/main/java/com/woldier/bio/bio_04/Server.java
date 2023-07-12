package com.woldier.bio.bio_04;

import com.woldier.bio.bio_03.SocketRunner;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket多线程伪异步共享
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while(true){
                Socket accept = serverSocket.accept();
                HandlerSocketPool handlerSocketPool = new HandlerSocketPool(2, 10);
                handlerSocketPool.exec(new SocketRunner(accept));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
