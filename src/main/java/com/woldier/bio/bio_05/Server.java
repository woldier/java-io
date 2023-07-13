package com.woldier.bio.bio_05;

import com.woldier.bio.bio_03.SocketRunner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文件传输
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9998);
            while(true){
                Socket accept = serverSocket.accept();
                new Thread(new FileSocketRunner(accept)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
