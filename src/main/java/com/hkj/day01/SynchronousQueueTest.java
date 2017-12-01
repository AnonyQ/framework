package com.hkj.day01;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue的使用
 * Created by lisainan on 2017/11/27.
 */
public class SynchronousQueueTest {

    /**
     * 不给初始容量会抛出异常？
     * 该队列没有初始容量，每一个put或add等其他生产操作前都要先take或offer等其他消费操作，并且是一一对应的，
     * 如果先使用生产操作则会抛出异常，先使用消费操作该线程就会一直处于阻塞状态，直到有一个线程向该队列中添加
     * 数据
     * 注意：每一个操作都要是不同的线程，否则还是会出现异常或者阻塞
     */
    public static void main(String args[]) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>(true);
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    System.out.println(synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    System.out.println(synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    synchronousQueue.add("test1");
                    synchronousQueue.add("test2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
