package org.lxp.crawler;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

public class HttpClientHelperTest {
    private List<String> urls;
    private AbstractHttpClientHelper helper;

    @Before
    public void setUp() {
        int size = 100;
        urls = IntStream.range(0, size).mapToObj(index -> "http://127.0.0.1:8080/crawler-test?query=".concat(String.valueOf(index)))
                .collect(Collectors.toList());
    }

    @Test
    public void testBatchGet() throws Exception {
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
        assertTrue(statusCodes.toString().equals(statusCodesAsync.toString()));
        assertTrue(costTime > costTimeAsync);
    }

}
