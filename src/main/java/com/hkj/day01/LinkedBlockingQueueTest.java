package com.hkj.day01;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Create by lisainan on17-12-28
 */
public class LinkedBlockingQueueTest {

    LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Test
    public void testAdd() {
        queue.add("zs");
        queue.add("ls");
        System.out.println(queue.size());
    }

    @Test
    public void testOffer() {
        queue.offer("zs");
        queue.offer("ls");
    }
}
