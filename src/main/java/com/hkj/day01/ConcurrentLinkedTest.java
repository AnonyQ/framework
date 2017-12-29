package com.hkj.day01;

import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Create by lisainan on17-12-27
 */
public class ConcurrentLinkedTest {
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    @Test
    public void testAdd() {
        queue.add("lisi");
        System.out.println(queue.peek());
    }

    @Test
    public void testOffer() {
        queue.offer("lisi");
        System.out.println(queue.peek());
    }


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
}
