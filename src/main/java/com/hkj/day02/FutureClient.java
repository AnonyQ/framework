package com.hkj.day02;

/**
 * 通过FutureClient获取请求数据
 * Created by lisainan on 2017/12/5.
 */
public class FutureClient {
    /**
     * 先返回一个空数据
     */
    private FutureData data = new FutureData();

    public Data getData(String msg) {
        // 开启一个线程加载真实数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                data.setRealData(msg);
            }
        }).start();
        return data;
    }
}
