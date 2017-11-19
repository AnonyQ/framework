package com.hkj.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by LiSainan on 2017/11/12.
 */

public class ListAdd1 {

    private final List<String> list = new ArrayList<>();

    public void add() {
        list.add("Lisi");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        // 使用CountDownLatch可以保证t1线程已发送请求，然后t2线程会执行相应的动作，达到同步的目的
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        // 使用notify不可以，因为两个线程持有相同的锁，而使用的notify的线程不可以释放该锁，因此不会同步，只有在线程t1完全执行结束后t2才会执行
        final Object lock = new Object();
        ListAdd1 listAdd1 = new ListAdd1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            listAdd1.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了元素");
                            Thread.sleep(500);
                            if (listAdd1.size() == 5) {
                                // 当元素个数等于5的时候唤醒正在等待的线程
                                System.out.println("发送请求，将t2线程唤醒");
                                countDownLatch.countDown();
                                // lock.notify();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    // 当元素个数不等于5的时候，将当前线程出于阻塞状态
                    if (listAdd1.size() != 5) {
                        try {
                            countDownLatch.await();
                            // lock.wait();
                            System.out.println(Thread.currentThread().getName() + "已唤醒");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t2");

        t2.start();
        t1.start();
    }

    @Test
    public void test() {
        ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();
        hashMap.put("key", "Obj");
    }

    @Test
    public void testLinkedBlockingQueue() throws InterruptedException {
        // 若指定容量大小，则不能存储超过指定容量的元素
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(5);
        for (int i = 0;i < 10;i ++) {
            // 1. 向容器中插入元素，超过容量则插入失败返回false，不会终止程序
            blockingQueue.offer(i);
            // 2. 向容器中插入元素，若超出容量则插入失败返回false，会抛出异常并终止程序
            // blockingQueue.add(i);
            // 3. 向容器中插入元素，若超出元素容量则等待指定时间（单位为:TimeUnit.MINUTES），如果还是失败则返回false
            // blockingQueue.offer(i, 1000, TimeUnit.MINUTES);
        }
        System.out.println(blockingQueue.toString());
    }

    /**
     * 指定LinkedBlockedDeque的容量，如果使用add添加的元素数量超出容量，则会抛出异常，
     * 使用offer则会添加失败，但不会抛出异常
     */
    @Test
    public void testLinkedBlockedDeque() {
        LinkedBlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(5);
        for (int i = 0;i < 10;i ++) {
            blockingDeque.add(i);
        }
        blockingDeque.offer(6);
    }

    /**
     * 对放入集合中的元素按照默认排序方式排序（前提：元素实现Comparable接口，重写compareTo方法），
     * 但是，即使重写了compareTo方法，也不会在放入元素的时候进行排序，而是在使用take方法取元素的
     * 时候，按照优先级大小去取
     */
    @Test
    public void testPriorityBlockingQueue() {
        /* PriorityBlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();
        blockingQueue.add(5);
        blockingQueue.add(3);
        blockingQueue.add(4);
        blockingQueue.add(1);
        System.out.println(blockingQueue);
        */

        PriorityBlockingQueue<Task> blockingQueue = new PriorityBlockingQueue<>();
        blockingQueue.add(new Task(-1, "任务-1"));
        blockingQueue.add(new Task(2, "任务1"));
        blockingQueue.add(new Task(3, "任务2"));
        blockingQueue.add(new Task(1, "任务3"));

        System.out.println(blockingQueue);
    }

    class Task implements Comparable<Task> {
        private int id;
        private String name;
        public Task (int id, String name) {
            this.id = id; this.name = name;
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return id + ": " +name;
        }
        @Override
        public int compareTo(Task task) {
            return this.id < task.id ? -1 : 1;
        }
    }

    /**
     * 接口中可以有静态方法？
     * 从jdk1.8开始接口中可以有default方法和static方法
     */
    @Test
    public void testInterMethod() {
        Inter.fun();
    }

    interface Inter {
        public static void fun() {
            System.out.println("Inter fun()");
        }
    }

    /**
     * DelayQueue的使用
     */
    @Test
    public void testDelayQueue() throws InterruptedException {
        // 放入DelayQueue的元素需要实现Delayed接口并重写getDelay，compareTo方法
        DelayQueue<People> delayQueue = new DelayQueue<>();
        People lisi = new People(10000, "lisi");
        delayQueue.offer(lisi);

        People xiaohong = new People(5000, "xiaohong");
        delayQueue.offer(xiaohong);
        System.out.println(delayQueue.take());
        System.out.println(delayQueue.take());

    }

    class People implements Delayed {
        private TimeUnit timeUnit = TimeUnit.SECONDS;  // 定义时间的工具类
        private long age;  // 截止时间
        private String name;
        public People(long age, String name) {
            this.age = age;
            this.name = name;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return age - System.currentTimeMillis();
        }
        @Override
        public int compareTo(Delayed delayed) {
            return (this.getDelay(this.timeUnit) - delayed.getDelay(this.timeUnit))
                    > 0 ? 1 : 0;
        }
        @Override
        public String toString() {
            return "[ " + name + ", " + age + " ]";
        }
    }

}
