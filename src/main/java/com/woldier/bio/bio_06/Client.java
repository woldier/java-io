package com.woldier.bio.bio_06;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
@Slf4j
public class Client {
    public static void main(String[] args) {
        try {
            log.debug("==============客户端启动================");
            Socket socket = new Socket("127.0.0.1",9898);
            new Thread(()->Client.send2Server(socket)).start(); //开启线程监听输入
            new Thread((()->Client.listenServer(socket))).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void send2Server(Socket socket)  {
        log.debug("==============客户端开输入线程================");
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintStream printStream = new PrintStream(outputStream);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("请说:");
            String msg = sc.nextLine();
            printStream.println(msg);
            printStream.flush();
        }
    }

    private static void listenServer(Socket socket){
        log.debug("==============客户端开接受消息线程================");
        try (
                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ){
            String b;
            while((b=bufferedReader.readLine())!=null)
               log.debug(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
