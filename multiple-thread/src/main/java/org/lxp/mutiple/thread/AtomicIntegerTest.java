package org.lxp.mutiple.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 测试AtomicInteger
 * @author Super.Li
 * @since Sep 17, 2017
 */
public class AtomicIntegerTest {
    private static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        MyThead thread0 = new MyThead(1);
        MyThead thread1 = new MyThead(0);
        thread0.start();
        thread1.start();
    }

    public static class MyThead extends Thread {

        private int v;

        public MyThead(int v) {
            this.v = v;
        }

        @Override
        public void run() {
            synchronized (num) {
                while (num.get() < 100) {
                    if (v == 1) {
                        if (num.get() % 2 == 0) {
                            try {
                                num.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(num.incrementAndGet());
                            num.notifyAll();
                        }
                    } else if (v == 0) {
                        if (num.get() % 2 == 1) {
                            try {
                                num.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(num.incrementAndGet());
                            num.notifyAll();
                        }
                    }
                }
            }
        }
    }
}
