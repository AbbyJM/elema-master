package com.abby.elema.task;

import java.util.concurrent.*;

/**
 * @author: Abby
 */
public class DefaultThreadPoolExecutor extends ThreadPoolExecutor {

    private static final int DEFAULT_CORE_POOL_SIZE=20;

    private static final int DEFAULT_MAX_POOL_SIZE=50;

    private static final int DEFAUL_KEEP_ALIVE_TIME=300;

    public DefaultThreadPoolExecutor(){
        super(DEFAULT_CORE_POOL_SIZE,DEFAULT_MAX_POOL_SIZE,DEFAUL_KEEP_ALIVE_TIME,TimeUnit.SECONDS,new ArrayBlockingQueue<>(1000));
    }

    public DefaultThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public DefaultThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory){
        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);
    }
}
