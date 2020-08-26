package com.xuecheng.test.fastdfs;

/**
 * Created by Alexlou on 2020/8/5.
 */
public class TestDeadLock {
    private static Object ob1 = new Object();
    private static Object ob2 = new Object();

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable{
        @Override
        public void run() {
            synchronized (ob1){
                System.out.println("Thread1 拿到了ob1的锁");
                try {
                    //停顿2s的意义是让Thread2拿到ob2的锁
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ob2){
                    System.out.println("Thread1 拿到了ob2的锁");

                }
            }
        }
    }

    private static class Thread2 implements Runnable{
        @Override
        public void run() {
            synchronized (ob2){
                System.out.println("Thread2 拿到了ob2的锁");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ob1){
                    System.out.println("Thread2 拿到了ob1的锁");

                }
            }
        }
    }
}
