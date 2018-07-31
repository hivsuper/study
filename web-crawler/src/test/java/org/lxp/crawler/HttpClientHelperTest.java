package org.lxp.crawler;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.util.StopWatch;

public class HttpClientHelperTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private AbstractHttpClientHelper helper;
    private final String crawlerUrl = "http://127.0.0.1:8080/crawler-test?sleep=";

    @Test
    public void testBatchGet() throws IOException {
        // given
        List<String> urls = IntStream.range(0, 100)
                .mapToObj(index -> crawlerUrl.concat(String.valueOf(TimeUnit.SECONDS.toMillis(1) + index)))
                .collect(Collectors.toList());
        // execute
        StopWatch stopWatch = new StopWatch();
        helper = new CloseableHttpAsyncClientHelper();
        stopWatch.start();
        List<Integer> statusCodesAsync = helper.batchGet(urls);
        stopWatch.stop();
        long costTimeAsync = stopWatch.getLastTaskTimeMillis();
        System.out.println(costTimeAsync + ":" + statusCodesAsync);
        helper = new CloseableHttpClientHelper();
        stopWatch.start();
        List<Integer> statusCodes = helper.batchGet(urls);
        stopWatch.stop();
        long costTime = stopWatch.getLastTaskTimeMillis();
        System.out.println(costTime + ":" + statusCodes);
        // verify
        assertTrue(statusCodes.toString().equals(statusCodesAsync.toString()));
        assertTrue(costTime > costTimeAsync);
    }

    @Test
    public void shouldThrowExceptionWhenAsyncSocketTimeout() throws Exception {
        expectedException.expect(ExecutionException.class);
        helper = new CloseableHttpAsyncClientHelper();
        helper.get(crawlerUrl.concat(String.valueOf(TimeUnit.SECONDS.toMillis(61))));
    }

    @Test
    public void shouldThrowExceptionWhenSocketTimeout() throws Exception {
        expectedException.expect(SocketTimeoutException.class);
        helper = new CloseableHttpClientHelper();
        helper.get(crawlerUrl.concat(String.valueOf(TimeUnit.SECONDS.toMillis(61))));
    }

    @Test
    public void shouldThrowExceptionWhenConnectTimeout() throws Exception {
        expectedException.expect(IllegalStateException.class);
        helper = new CloseableHttpClientHelper();
        final int size = helper.maxTotalConnection;
        final int time = 41;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        IntStream.range(0, size)
                .mapToObj(index -> CompletableFuture.supplyAsync(() -> {
                    Integer rtn = null;
                    try {
                        rtn = helper.get(crawlerUrl.concat(String.valueOf(TimeUnit.SECONDS.toMillis(time))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return rtn;
                }, executorService)).filter(Objects::nonNull).collect(Collectors.toList());
        helper.get(crawlerUrl.concat(String.valueOf(TimeUnit.SECONDS.toMillis(time))));
    }
}
