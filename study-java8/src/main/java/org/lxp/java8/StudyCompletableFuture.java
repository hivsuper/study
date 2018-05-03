package org.lxp.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @see https://stackoverflow.com/questions/36256337/completablefuture-supplyasync
 */
public class StudyCompletableFuture {
    private static int processors = Runtime.getRuntime().availableProcessors();
    static final int MAX_SIZE = 100000;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(processors);

    public static Pair<Long, List<String>> executorService() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> list = new Vector<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            executorService.execute(() -> list.add(value));
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), list);
    }

    /**
     * {@link CompletableFuture#supplyAsync(java.util.function.Supplier)}
     * 
     * @return
     */
    public static Pair<Long, List<String>> completableFutureSupplyAsync() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            futures.add(CompletableFuture.supplyAsync(() -> value));
        }
        /**
         * invoke collect to guarantee all threads have finished
         */
        List<String> rtn = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), rtn);
    }

    /**
     * {@link CompletableFuture#supplyAsync(java.util.function.Supplier, java.util.concurrent.Executor)}
     * 
     * @return
     */
    public static Pair<Long, List<String>> completableFutureSupplyAsyncWithExecutorService() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            futures.add(CompletableFuture.supplyAsync(() -> value, executorService));
        }
        /**
         * invoke collect to guarantee all threads have finished
         */
        List<String> rtn = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), rtn);
    }

    /**
     * {@link CompletableFuture#runAsync(Runnable)}
     * 
     * @return
     */
    public static Pair<Long, List<String>> completableFutureRunAsync() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> rtn = new Vector<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            futures.add(CompletableFuture.runAsync(() -> rtn.add(value)));
        }
        /**
         * invoke count to guarantee all threads have finished
         */
        futures.forEach(CompletableFuture::join);
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), rtn);
    }

    /**
     * {@link CompletableFuture#runAsync(Runnable, java.util.concurrent.Executor)}
     * 
     * @return
     */
    public static Pair<Long, List<String>> completableFutureRunAsyncWithExecutorService() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> rtn = new Vector<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            futures.add(CompletableFuture.runAsync(() -> rtn.add(value), executorService));
        }
        /**
         * invoke count to guarantee all threads have finished
         */
        futures.forEach(CompletableFuture::join);
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), rtn);
    }
}