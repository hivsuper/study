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
    public void testCompletableFuture() throws Exception {
        Pair<Long, List<String>> pair = StudyCompletableFuture.completableFuture();
        System.out.println("completableFuture:" + pair.getLeft());
        Assert.assertEquals(StudyCompletableFuture.MAX_SIZE, pair.getRight().size());
    }

    @Test
    public void testCompletableFutureWithExecutorService() throws Exception {
        Pair<Long, List<String>> pair = StudyCompletableFuture.completableFutureWithExecutorService();
        System.out.println("completableFutureWithExecutorService:" + pair.getLeft());
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
