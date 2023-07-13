package com.woldier.bio.bio_05;

import com.woldier.bio.bio_03.AbstractSocketRunner;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.UUID;

public class FileSocketRunner extends AbstractSocketRunner {

    protected FileSocketRunner(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try {
            //得到输入流
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            //获取文件后缀名
            String suffix = dataInputStream.readUTF();
            //对象拷贝
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\bat\\IdeaProjects\\aio-bio-nio\\src\\main\\java\\com\\woldier\\bio\\bio_05\\" + UUID.randomUUID() + suffix);
            IOUtils.copy(dataInputStream,fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
