package com.learning.multithreading.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolIml implements ThreadPool {

    private BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
    private int maxNumberOfThreads = 5;
    private List<PoolThread> threadPool = new ArrayList<>();

    public ThreadPoolIml() {
        initiateThreadPool();
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initiateThreadPool() {
        for (int i=1; i<=maxNumberOfThreads; i++){
            String name = "thread-" + i;
            PoolThread thread = new PoolThread(blockingQueue);
            thread.setName(name);
            thread.start();
            threadPool.add(thread);
            System.out.println(thread.getName()+" started");
        }
    }

    @Override
    public void stopThreadPool() {
        for (PoolThread thread: threadPool) {
            thread.stopThread();
        }
    }
}
