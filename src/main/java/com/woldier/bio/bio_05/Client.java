package com.woldier.bio.bio_05;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9998);
            OutputStream inputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(inputStream);
            dataOutputStream.writeUTF(".jfif");
            //载入要上传的文件
            FileInputStream fileInputStream = new FileInputStream(new File("D:\\bat\\IdeaProjects\\aio-bio-nio\\src\\main\\java\\com\\woldier\\bio\\bio_05\\psc.jfif"));
            IOUtils.copy(fileInputStream,dataOutputStream); //输入流到输出流
            //数据刷入
            dataOutputStream.flush();
            Thread.sleep(10000);
            dataOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
