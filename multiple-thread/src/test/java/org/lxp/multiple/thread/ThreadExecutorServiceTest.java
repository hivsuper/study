package org.lxp.multiple.thread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Description: 测试ExecutorService
 * @author Super.Li
 * @date Jul 6, 2017
 */
public class ThreadExecutorServiceTest {
    private static final String THIS_IS_SHUTDOWN_WITH_AWAIT_TERMINATION = "This is shutdownWithAwaitTermination";
    private static final int RESULT = 111;

    private static boolean submitRunnable() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is submitRunnable");
            }
        });
        return future.get() == null;
    }

    private static Integer submitRunnableWithResult() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is submitRunnableWithResult");
            }
        }, RESULT);
        return future.get();
    }

    private static Integer submitBlockCallable() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("This is submitBlockCallable");
                return RESULT;
            }
        });
        return future.get();// 阻塞
    }

    private static boolean submitNonBlockCallable() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("This is submitNonBlockCallable");
                return RESULT;
            }
        });
        while (!future.isDone()) {// 非阻塞
            System.out.println(new Date());
        }
        return future.isDone();
    }

    private static String shutdown() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final StringBuilder sb = new StringBuilder();
        executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(10000);
                sb.append("This is shutdown");
                return RESULT;
            }
        });
        executorService.shutdown();
        return sb.toString();
    }

    private static String shutdownWithAwaitTermination() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final StringBuilder sb = new StringBuilder();
        executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(10000);
                sb.append(THIS_IS_SHUTDOWN_WITH_AWAIT_TERMINATION);
                return RESULT;
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        return sb.toString();
    }

    @Test
    public void test() throws InterruptedException, ExecutionException {
        Assert.assertTrue(submitRunnable());
        Assert.assertEquals(RESULT, submitRunnableWithResult().intValue());
        Assert.assertEquals(RESULT, submitBlockCallable().intValue());
        Assert.assertTrue(submitNonBlockCallable());
        Assert.assertTrue(shutdown().isEmpty());
        Assert.assertEquals(THIS_IS_SHUTDOWN_WITH_AWAIT_TERMINATION, shutdownWithAwaitTermination());
    }

}
