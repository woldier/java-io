package com.woldier.bio.bio_03;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 支持多链接
 */
public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("客户端启动");
            Socket socket = new Socket("127.0.0.1",9999);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print("请说:");
                String msg = sc.nextLine();
                printStream.println(msg);
                printStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
