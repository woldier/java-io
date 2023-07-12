package com.woldier.bio.bio_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * BIO模式服务端
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s ;
            if((s = reader.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
