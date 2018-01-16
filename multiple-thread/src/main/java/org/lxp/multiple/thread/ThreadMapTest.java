package org.lxp.multiple.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 测试map
 * @author Super.Li
 * @date Jul 6, 2017
 */
public class ThreadMapTest {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Integer> hashtable = new Hashtable<>();
        Map<Integer, Integer> hashmap = new HashMap<>();
        Map<Integer, Integer> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        test(hashtable);
        test(hashmap);
        test(synchronizedHashMap);
        test(concurrentHashMap);
    }

    private static void test(Map<Integer, Integer> map) throws InterruptedException {
        int testTimes = 5;
        long totalTimeMillis = 0;
        for (int k = 0; k < testTimes; k++) {
            totalTimeMillis += costTimeMillis(map);
        }
        System.out.println("Test " + map.getClass() + " average time " + (totalTimeMillis / testTimes));
    }

    private static long costTimeMillis(Map<Integer, Integer> map) throws InterruptedException {
        int count = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        long startMillis = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 500000; j++) {
                        map.put(0, 0);
                        map.get(0);
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        return System.currentTimeMillis() - startMillis;
    }
}
