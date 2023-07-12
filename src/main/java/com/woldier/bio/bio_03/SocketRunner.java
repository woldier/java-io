package com.woldier.bio.bio_03;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

@Slf4j
public class SocketRunner extends AbstractSocketRunner{
    public SocketRunner(Socket socket) {
        super(socket);
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s ;
            while((s =bufferedReader.readLine()) !=null) {
                log.debug(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
