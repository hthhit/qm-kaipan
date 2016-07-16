package com.qm.kaipan.task;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncTaskWorker extends ScheduledThreadPoolExecutor {
    private static final String threadNamePrefix = "AsyncTaskWorkerThread";
    private static final AtomicInteger threadNum = new AtomicInteger(1);

    private static ThreadFactory workThreadFactory = new ThreadFactory() {

        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, threadNamePrefix + "-" + threadNum.getAndIncrement());
            if (!t.isDaemon()) {
                t.setDaemon(true);
            }
            return t;
        }
    };

    public AsyncTaskWorker(int corePoolSize) {
        super(corePoolSize, workThreadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        AsyncTask.addCreateNum();
        super.beforeExecute(t, r);
    }
}
