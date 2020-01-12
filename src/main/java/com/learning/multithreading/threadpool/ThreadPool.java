package com.learning.multithreading.threadpool;

public interface ThreadPool {
    public void execute(Runnable runnable);
    public void stopThreadPool();
}
