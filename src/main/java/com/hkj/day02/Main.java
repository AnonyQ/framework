package com.hkj.day02;

/**
 * 主程序入口
 * Created by lisainan on 2017/12/5.
 */
public class Main {
    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        Data data = futureClient.getData("发送请求");
        System.out.println("发送完毕！");
    }
}
