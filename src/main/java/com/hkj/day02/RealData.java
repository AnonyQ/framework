package com.hkj.day02;

/**
 * 客户端需要的真实的数据
 * Created by lisainan on 2017/12/5.
 */
public class RealData implements Data {
    private String data;

    public RealData(String data) {
        System.out.println("获得请求");
        try {
            System.out.println("正在加载数据过程可能有点漫长。。。");
            Thread.sleep(5000);
            System.out.println("数据加载完毕！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
    }

    @Override
    public String getRequest() {
        return null;
    }

}
