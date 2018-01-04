package org.lxp.java8;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

public class StudyCompletableFutureTest {

    @Test
    public void testExecutorService() throws Exception {
        Pair<Long, List<String>> pair = StudyCompletableFuture.executorService();
        System.out.println("executorService:" + pair.getLeft());
        Assert.assertEquals(StudyCompletableFuture.MAX_SIZE, pair.getRight().size());
    }

    @Test
    public void testCompletableFutureSupplyAsync() throws Exception {
        Pair<Long, List<String>> pair = StudyCompletableFuture.completableFutureSupplyAsync();
        System.out.println("completableFutureSupplyAsync:" + pair.getLeft());
        Assert.assertEquals(StudyCompletableFuture.MAX_SIZE, pair.getRight().size());
    }

    @Test
    public void testCompletableFutureSupplyAsyncWithExecutorService() throws Exception {
        Pair<Long, List<String>> pair = StudyCompletableFuture.completableFutureSupplyAsyncWithExecutorService();
        System.out.println("completableFutureSupplyAsyncWithExecutorService:" + pair.getLeft());
        Assert.assertEquals(StudyCompletableFuture.MAX_SIZE, pair.getRight().size());
    }

    @Test
    public void testCompletableFutureRunAsync() throws Exception {
        Pair<Long, List<String>> pair = StudyCompletableFuture.completableFutureRunAsync();
        System.out.println("completableFutureRunAsync:" + pair.getLeft());
        Assert.assertEquals(StudyCompletableFuture.MAX_SIZE, pair.getRight().size());
    }

    @Test
    public void testCompletableFutureRunAsyncWithExecutorService() throws Exception {
        Pair<Long, List<String>> pair = StudyCompletableFuture.completableFutureRunAsyncWithExecutorService();
        System.out.println("completableFutureRunAsyncWithExecutorService:" + pair.getLeft());
        Assert.assertEquals(StudyCompletableFuture.MAX_SIZE, pair.getRight().size());
    }

    @Test
    public void testSupplyAsyncAndThenApply() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).thenApply(i -> i + 1);
        Assert.assertEquals(2, future.get().intValue());
    }

    @Test
    public void testSupplyAsyncAndThenCompose() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 2));
        Assert.assertEquals(3, future.get().intValue());
    }

    @Test
    public void testSupplyAsyncAndAccept() throws InterruptedException, ExecutionException {
        StringBuilder sb = new StringBuilder();
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).thenAccept(x -> {
            sb.append(x + 3);
        }).join();
        Assert.assertEquals("4", sb.toString());
    }
}
