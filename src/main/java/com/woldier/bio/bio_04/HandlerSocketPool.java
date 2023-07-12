package com.woldier.bio.bio_04;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class HandlerSocketPool {
    private ThreadPoolExecutor threadPoolExecutor;

    /**
    *
    * description 初始化连接池
    *
    * @param poolSize
     * @param queueLength
    * @return
    * @author: wang1
    * @date: 2023/7/12 14:33
    */
    public HandlerSocketPool(int poolSize,int queueLength) {
        threadPoolExecutor = new ThreadPoolExecutor(2,poolSize,200, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
    }

    public void exec(Runnable target){
        threadPoolExecutor.execute(target);
    }
}
