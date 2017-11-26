package com.hkj.day01;

import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue队列的使用，放入该队列的元素必须实现Comparable接口
 */
public class PriorityBlockingQueueTest {

    static class People implements Comparable<People> {
        public People(int age) {
            this.age = age;
        }

        private int age = 0;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(People people) {
            return this.age - people.age;
        }
        @Override
        public String toString() {
            return "People{" +
                    "age=" + age +
                    '}';
        }
    }

    /**
     * 队列的Take使用
     * 在向队列中添加元素和取元素的时候都用了堆排序，保证了取元素的时候取到的元素的顺序是按照优先级顺序来的
     */
    @Test
    public void testTake() throws InterruptedException {
        PriorityBlockingQueue<People> priorityQueue = new PriorityBlockingQueue<>();

        priorityQueue.add(new People(19));
        priorityQueue.add(new People(18));
        priorityQueue.add(new People(27));
        priorityQueue.add(new People(23));
        priorityQueue.add(new People(24));
        priorityQueue.add(new People(19));
        priorityQueue.add(new People(15));

        System.out.println(priorityQueue);
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());

        System.out.println(priorityQueue);
    }
}
