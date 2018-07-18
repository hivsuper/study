package org.lxp.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseableHttpAsyncClientHelper extends AbstractHttpClientHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloseableHttpAsyncClientHelper.class);
    private PoolingNHttpClientConnectionManager connManager;

    public CloseableHttpAsyncClientHelper() throws IOReactorException {
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors()).setSoKeepAlive(true).build();
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(maxTotalConnection);
        connManager.setDefaultMaxPerRoute(maxTotalConnection);
    }

    @Override
    public List<Integer> batchGet(List<String> urls) throws IOException {
        try (CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setConnectionManager(connManager)
                .build()) {
            httpclient.start();

            List<Future<HttpResponse>> respList = urls.stream().map(url -> {
                final HttpGet request = buildGet(url);
                return httpclient.execute(request, null);
            }).collect(Collectors.toCollection(LinkedList::new));

            return respList.stream().map(response -> {
                try {
                    return response.get().getStatusLine().getStatusCode();
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.error(e.getMessage(), e);
                    return null;
                }
            }).collect(Collectors.toList());
        }
    }
}
