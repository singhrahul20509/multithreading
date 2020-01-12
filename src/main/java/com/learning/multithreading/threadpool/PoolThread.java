package com.learning.multithreading.threadpool;

import java.util.concurrent.BlockingQueue;

public class PoolThread extends Thread {

    private boolean stop;
    private BlockingQueue<Runnable> blockingQueue;

    public PoolThread(BlockingQueue<Runnable> blockingQueue) {
        this.stop = false;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                if(!blockingQueue.isEmpty()) {
                    MainThread.MyThread task = (MainThread.MyThread) blockingQueue.take();
                    task.setName(this.getName());
                    task.run();
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Stopping " + this.getName());
    }

    public void stopThread() {
        this.stop = true;
    }
}
