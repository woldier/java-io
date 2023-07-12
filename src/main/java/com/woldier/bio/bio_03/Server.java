package com.woldier.bio.bio_03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 支持多连接
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while(true){
                Socket accept = serverSocket.accept();
                new Thread(new SocketRunner(accept)).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
