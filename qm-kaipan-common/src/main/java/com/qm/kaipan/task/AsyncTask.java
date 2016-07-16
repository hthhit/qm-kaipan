package com.qm.kaipan.task;

import java.util.concurrent.atomic.AtomicLong;

public abstract class AsyncTask implements Runnable{
    private static AtomicLong createdNum = new AtomicLong(0);
    private static AtomicLong finishedNum = new AtomicLong(0);
    private static AtomicLong errorNum = new AtomicLong(0);

    private static AtomicLong totalCostTime = new AtomicLong(0);

    public void run() {
        long startTime = System.currentTimeMillis();
        boolean withError = false;
        try {
            customRun();
        } catch (Exception e) {
            withError = true;
            errorNum.incrementAndGet();
            String threadName = Thread.currentThread().getName();
            System.err.println("[thread:" + threadName + "][handleTaskError]");
            e.printStackTrace();
        } finally {
            if (!withError)
                finishedNum.addAndGet(1);

            totalCostTime.addAndGet((System.currentTimeMillis() - startTime));
        }
    }

    public abstract void customRun();

    public static void addCreateNum() {
        createdNum.incrementAndGet();
    }

    public static void addFinishNum() {
        finishedNum.incrementAndGet();
    }

    public static long getAddNum() {
        return createdNum.get();
    }

    public static long getFinishNum() {
        return finishedNum.get();
    }

    public static long getErrorNum() {
        return errorNum.get();
    }

    public static long getTotalExecuteTime() {
        return totalCostTime.get();
    }
}
