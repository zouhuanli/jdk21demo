package com.homura.jdk21demo.virtualthread;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * VirtualThread 示例
 * Created by homura on 2023/10/25.
 */
public class NewVirtualThreadPerTaskExecutorDemo {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Thread t = Thread.ofVirtual().name("VirtualThread").unstarted(() -> System.out.println("Hello, Virtual Thread!"));
        t.setDaemon(true);
        t.setPriority(10);
        t.start();

        System.out.println(t.isVirtual());
        System.out.println(t.getName());
        System.out.println(t.getState());
        System.out.println(t.getThreadGroup());
        System.out.println(t.getPriority());

        System.out.println(LocalDateTime.now());
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }  // executor.close() is called implicitly, and waits
        System.out.println(LocalDateTime.now());
        try (var executor = Executors.newFixedThreadPool(100)) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
        System.out.println(LocalDateTime.now());
        /***
         * Hello, World!
         * 2023-10-25T20:16:24.666452900
         * 2023-10-25T20:16:26.133547
         * 2023-10-25T20:18:07.043543300
         */

    }

}
