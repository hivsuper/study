package org.lxp.multiple.thread;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description 利用DelayQueue清除超时请求<br/>
 *              1. 主线程从工作队列取出任务处理完成后，把任务从超时队列移除<br/>
 *              2. 超时检查线程找到超时请求后，把任务从工作队列中移除
 * @author Super.Li
 * @since Sep 17, 2017
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 36;
        DelayQueue<MyRequest> queue = new DelayQueue<>();// 用于记录是否超时的队列
        BlockingQueue<MyRequest> workQueue = new ArrayBlockingQueue<>(size);// 请求的队表
        Map<Integer, MyRequest> cache = new HashMap<>();// 请求与id的对照表
        for (int i = 0; i < size; i++) {// 初始化
            MyRequest impl = new MyRequest(i, System.nanoTime(), 120);
            queue.put(impl);
            workQueue.put(impl);
            cache.put(i, impl);
        }
        /**
         * 建立超时检查任务
         */
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                while (queue.size() > 0) {
                    try {
                        MyRequest impl = queue.take();
                        workQueue.remove(impl);// 若请求超时则把请求从队列中移除
                        System.out.println(String.format("%s is timeout", impl));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /**
         * 建2个线程消费请求
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (workQueue.size() > 0) {
            List<MyRequest> tasks = Arrays.asList(new MyRequest[] { workQueue.take(), workQueue.take() });
            List<Future<Integer>> futures = executorService.invokeAll(tasks);
            for (Future<Integer> future : futures) {
                queue.remove(cache.get(future.get()));// 若请求成功，则不需要再检查是否超时
            }
        }
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        executorService.shutdown();
    }
}

class MyRequest implements Delayed, Callable<Integer> {
    private int threadId;
    private long startTime;
    private long expiredTime;

    public MyRequest(int threadId, long startTime, long timeout) {
        this.threadId = threadId;
        this.startTime = startTime;
        this.expiredTime = TimeUnit.SECONDS.toNanos(timeout) + System.nanoTime();
    }

    @Override
    public Integer call() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s is ok", this));
        return threadId;
    }

    @Override
    public int compareTo(Delayed arg0) {
        int rtn;
        if (arg0 == null || !(arg0 instanceof MyRequest)) {
            rtn = 1;
        } else {
            MyRequest impl = (MyRequest) arg0;
            rtn = startTime > impl.getStartTime() ? 1 : (startTime == impl.getStartTime() ? 0 : -1);
        }
        return rtn;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return expiredTime - System.nanoTime();
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return String.format("MyRequest [threadId=%s, startTime=%s, expiredTime=%s]", threadId, startTime, expiredTime);
    }
}