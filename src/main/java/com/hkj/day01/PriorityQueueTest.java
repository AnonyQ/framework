package com.hkj.day01;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Create by lisainan on 17-12-28
 */
public class PriorityQueueTest {
    PriorityQueue<String> queue = new PriorityQueue<>();

    @Test
    public void testOffer() {
        queue.offer("ls");
        queue.offer("zl");
        System.out.println(queue.size());
    }

    @Test
    public void testPoll() {
        queue.offer("zl");
        queue.offer("xm");
        queue.offer("ls");

        System.out.println(queue);

        int size = queue.size();
        for (int i = 0;i < size;i ++) {
            System.out.println(queue.poll());
        }
    }

}
