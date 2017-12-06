package org.lxp.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class StudyCompletableFuture {
    private static int processors = Runtime.getRuntime().availableProcessors();
    static final int MAX_SIZE = 100000;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(processors);

    public static Pair<Long, List<String>> executorService() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> list = new ArrayList<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            executorService.execute(() -> {
                list.add(value);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), list);
    }

    public static Pair<Long, List<String>> completableFuture() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            futures.add(CompletableFuture.supplyAsync(() -> {
                return value;
            }));
        }
        List<String> rtn = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), rtn);
    }

    public static Pair<Long, List<String>> completableFutureWithExecutorService() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int index = 0; index < MAX_SIZE; index++) {
            final String value = String.valueOf(index);
            futures.add(CompletableFuture.supplyAsync(() -> {
                return value;
            }, executorService));
        }
        List<String> rtn = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        stopWatch.stop();
        return ImmutablePair.of(stopWatch.getTime(), rtn);
    }
}