package org.lxp.java8;

import java.util.List;

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

}
