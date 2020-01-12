package com.learning.multithreading.threadpool;

public class MainThread {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPoolIml();
        for (int i=1; i<=20; i++) {
            Runnable myThread = new MyThread(i);
            threadPool.execute(myThread);
        }

        try {
            System.out.println("Main thread is going to sleep");
            Thread.sleep(10000);
            System.out.println("Main thread woke up");
            System.out.println("Main thread going to stop thread pool");
            threadPool.stopThreadPool();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread implements Runnable{

        private int i;
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(name+" running task "+i);
        }
    }
}
