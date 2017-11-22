package com.hkj.day01;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by lsn on 2017/11/21.
 */
public class DelayQueueTest {
    /**
     * 存入DelayQueue中的元素必须实现Delayed接口
     */
    static class DelayAlarm implements Delayed {
        private String id;
        private Long delaytime;
        private Long delay;
        private Long time;
        public DelayAlarm(String id) {
            this.id = id;
        }
        public DelayAlarm(String id, Long delaytime, Long delay) {
            this.id = id;
            this.delaytime = delaytime;
            this.delay = delay;
            this.time = this.delay + delaytime;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public Long getDelaytime() {
            return delaytime;
        }
        public void setDelaytime(Long delaytime) {
            this.delaytime = delaytime;
        }
        public Long getDelay() {
            return delay;
        }
        public void setDelay(Long delay) {
            this.delay = delay;
        }
        public Long getTime() {
            return time;
        }
        public void setTime(Long time) {
            this.time = time;
        }

        /**
         * 预期时间-当前时间，时间单位：毫秒
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time * 1000 - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        @Override
        public int compareTo(Delayed delayed) {
            return (int) (this.getTime() - ((DelayAlarm )delayed).getTime());
        }
        @Override
        public String toString() {
            return "DelayAlarm{" +
                    "id='" + id + '\'' +
                    ", delaytime=" + delaytime +
                    ", delay=" + delay +
                    ", time=" + time +
                    '}';
        }
    }

    /**
     * 缓存队列
     */
    public static DelayQueue<DelayAlarm> queue = new DelayQueue<>();
    public static volatile boolean stop;

    /**
     * 向缓存队列中添加元素，添加失败则提示一下
     * @param delayAlarm
     */
    public static void push(DelayAlarm delayAlarm) {
        if (!queue.offer(delayAlarm)) {
            System.out.println("/- push error");
        }
    }
    static class Consumer extends Thread {
        @Override
        public void run() {
            while (!stop) {
                try {
                    DelayAlarm delayAlarm = queue.take();
                    System.out.println(System.currentTimeMillis() / 1000 + "----" + delayAlarm);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        new Consumer().start();
        long daltime = System.currentTimeMillis() / 1000;
        DelayAlarm a = new DelayAlarm( "001", daltime, 1L );
        DelayAlarm b = new DelayAlarm( "002", daltime, 3L );
        DelayAlarm c = new DelayAlarm( "003", daltime, 5L );
        DelayAlarm d = new DelayAlarm( "004", daltime, 2L );
        DelayAlarm f = new DelayAlarm( "005", daltime, 10L );
        push( a );
        push( b );
        push( c );
        push( d );
        push( f );
        //移除003的告警
//        DelayAlarm g = new DelayAlarm( "003" );
//        if ( queue.contains( g ) ) {
//            queue.remove( g );
//        }
    }
}
