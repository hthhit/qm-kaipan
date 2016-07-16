package com.qm.kaipan.task;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AsyncTaskExcutor {
    private static AsyncTaskExcutor instance = new AsyncTaskExcutor();

    public static AsyncTaskExcutor getInstance() {
        return instance;
    }

    private ScheduledExecutorService schedueExecutor = null;

    private AsyncTaskExcutor() {
        int processNum = Runtime.getRuntime().availableProcessors();
        schedueExecutor = new AsyncTaskWorker(processNum + 1);
    }

    public void postTask(AsyncTask t) {
        schedueExecutor.submit(t);
    }

    public ScheduledFuture<?> postTask(AsyncTask t, long delay, long interval) {
        return schedueExecutor.scheduleWithFixedDelay(t, delay, interval, TimeUnit.MILLISECONDS);
    }

    public ScheduledFuture<?> postTask(AsyncTask t, long delay) {
        return schedueExecutor.schedule(t, delay, TimeUnit.MILLISECONDS);
    }

}
