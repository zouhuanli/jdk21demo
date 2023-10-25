package com.homura.jdk21demo.virtualthread;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalDemo示例
 * Created by homura on 2023/10/25.
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread t = Thread.ofVirtual().name("VirtualThread" + i).unstarted(ThreadLocalDemo::addNum);
            t.start();
        }

        TimeUnit.SECONDS.sleep(5L);
    }

    private static void addNum() {
        Integer num = threadLocal.get();
        if (num == null) {
            num = 0;
        }
        num++;
        threadLocal.set(num);
        System.out.println(Thread.currentThread().getName() + ":" + num);
    }
}
