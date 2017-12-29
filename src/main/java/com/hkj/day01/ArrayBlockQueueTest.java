package com.hkj.day01;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Create by lisainan on17-12-28
 */
public class ArrayBlockQueueTest {
    // 必须指定初始大小
    static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

    @Test
    public void testAdd() {
        queue.add("ls");
        queue.add("zs");
        // queue.add("ww");
    }

    @Test
    public void testOffer() {
        queue.offer("ls");
        queue.offer("zs");
    }

    /**
     * 取栈顶元素
     */
    @Test
    public void testPeek() {
        queue.offer("ls");
        queue.offer("zs");

        for (int i = 0;i < queue.size();i ++) {
            System.out.println(queue.peek());
        }
        System.out.println(queue.size());
    }

    @Test
    public void testPoll() {
        queue.offer("ls");
        queue.offer("zs");

        int size = queue.size();
        for (int i = 0;i < size;i ++) {
            System.out.println(queue.poll());
        }
        System.out.println(queue.size());
    }


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "take -- ").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    queue.offer("souche");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "offer -- ").start();
    }
}
