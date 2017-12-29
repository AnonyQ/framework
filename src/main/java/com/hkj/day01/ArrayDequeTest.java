package com.hkj.day01;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * Create by lisainan on17-12-29
 */
public class ArrayDequeTest {
    ArrayDeque<String> deque = new ArrayDeque<>();

    @Test
    public void testAdd() {
        deque.add("ls");
        deque.add("zs");

    }
}
