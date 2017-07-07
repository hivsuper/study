package org.lxp.mutiple.thread;

/**
 * @Description: 测试volatile
 * @author Super.Li
 * @date Jul 7, 2017
 */
public class ThreadVolatileTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Singleton.getInstance());
    }
}

final class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
