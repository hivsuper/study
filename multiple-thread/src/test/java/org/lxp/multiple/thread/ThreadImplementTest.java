package org.lxp.multiple.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Description: 线程的两种实现方法
 * @author Super.Li
 * @date Jul 4, 2017
 */
public class ThreadImplementTest {
    private Map<Integer, Long> map = new ConcurrentHashMap<>();

    class MethodOne extends Thread {
        private int count = 0;

        @Override
        public void run() {
            map.put(++count, this.getId());
        }
    }

    class MethodTwo implements Runnable {
        private int count = 0;

        @Override
        public void run() {
            map.put(++count, Thread.currentThread().getId());
        }
    }

    @Test
    public void textThread() {
        /**
         * 方法一：继承Thread
         */
        MethodOne extendsThread = new MethodOne();
        extendsThread.start();
        /**
         * 方法二：实现Runnable
         */
        MethodTwo implementsRunnable = new MethodTwo();
        new Thread(implementsRunnable).start();
    }

    @Test
    public void testTwoRuns() throws InterruptedException {
        /**
         * 注意：以下两种方法启动方式截然不同
         */
        Thread tmp;
        MethodOne extendsThread = new MethodOne();
        for (int i = 0; i < 3; i++) {// 只有一个线程
            tmp = new Thread(extendsThread);
            tmp.start();
            tmp.join();
        }
        Assert.assertTrue(map.containsKey(3));
        Assert.assertTrue(map.containsKey(2));
        Assert.assertTrue(map.containsKey(1));

        map.clear();// 清空缓存
        for (int i = 0; i < 3; i++) {// 三个不同线程
            tmp = new MethodOne();
            tmp.start();
            tmp.join();
        }
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(1));
    }
}