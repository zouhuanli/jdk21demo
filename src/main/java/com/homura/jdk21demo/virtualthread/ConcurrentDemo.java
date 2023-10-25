package com.homura.jdk21demo.virtualthread;

import java.util.concurrent.TimeUnit;

/**
 * ConcurrentDemo示例
 */
public class ConcurrentDemo {
    static int num = 0;

    //支持sync关键字
    public static synchronized void addNum() {
        num++;
        System.out.println(Thread.currentThread().getName() + ":" + num);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread t = Thread.ofVirtual().name("VirtualThread" + i).unstarted(ConcurrentDemo::addNum);
            t.start();
        }
        TimeUnit.SECONDS.sleep(5L);
        System.out.println(num);
        //输出：100
    }
}
