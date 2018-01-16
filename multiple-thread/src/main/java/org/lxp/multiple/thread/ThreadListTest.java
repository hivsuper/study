package org.lxp.multiple.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * http://xxgblog.com/2016/04/02/traverse-list-thread-safe/
 * 
 * @Description: 多线程操作列表
 * @author Super.Li
 * @date Jul 5, 2017
 */
public class ThreadListTest {
    public void multipleThreadArrayList(final List<Integer> list) throws InterruptedException {
        int count = 10;
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Integer integer : list) {
                    System.out.println(integer);
                }
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + " remove " + list.remove(0));
                countDownLatch.countDown();
            }
        }).start();
        countDownLatch.await();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadListTest test = new ThreadListTest();
        List<Integer> list;
        list = new Vector<>();// Vector只保证内部方法线程安全
        list = new ArrayList<>();// ArrayList效率比Vector高，但非线程安全
        list = Collections.synchronizedList(new ArrayList<>());// Collections.synchronizedList与Vector异曲同工
        list = new CopyOnWriteArrayList<>();// CopyOnWriteArrayList线程安全：每个线程先复制一份并把地址指向新list，在新的list上操作，因此最终结果未必符合预期

        test.multipleThreadArrayList(list);
        System.out.println(list.toString());
    }
}
