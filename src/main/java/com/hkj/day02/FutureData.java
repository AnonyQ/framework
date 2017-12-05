package com.hkj.day02;

/**
 * 客户端获取到的是模拟的空数据，空壳
 * Created by lisainan on 2017/12/5.
 */
public class FutureData implements Data {
    /**
     * 用以标示数据是否已经加载完毕
     */
    private boolean isReady = false;

    /**
     * 用以标识真实的已经加载完毕的数据
     */
    private Data realData = null;

    public void setRealData(String data) {
        // 如果当前数据已经加载完毕，直接返回
        if (isReady) {
            return;
        }

        this.realData = new RealData(data);
    }

    @Override
    public String getRequest() {
        // 当数据没有加载成功时，当前线程处于阻塞状态
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getRequest();
    }
}
