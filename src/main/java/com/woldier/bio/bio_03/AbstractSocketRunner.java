package com.woldier.bio.bio_03;

import java.net.Socket;

/**
 * socket抽线类
 */
public abstract class AbstractSocketRunner implements Runnable{
    protected final Socket socket;
    protected AbstractSocketRunner(Socket socket) {
        this.socket = socket;
    }
}
