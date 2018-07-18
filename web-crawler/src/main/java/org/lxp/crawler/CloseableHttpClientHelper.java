package org.lxp.crawler;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseableHttpClientHelper extends AbstractHttpClientHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloseableHttpClientHelper.class);
    private PoolingHttpClientConnectionManager connManager;

    public CloseableHttpClientHelper() {
        connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(maxTotalConnection);
        connManager.setDefaultMaxPerRoute(maxTotalConnection);
    }

    @Override
    public List<Integer> batchGet(List<String> urls) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig).build()) {
            return urls.stream().map(url -> {
                final HttpGet request = buildGet(url);
                try (CloseableHttpResponse response = httpclient.execute(request)) {
                    return response.getStatusLine().getStatusCode();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                    return null;
                }
            }).collect(Collectors.toList());
        }
    }

    @Override
    public Integer get(String url) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig).build()) {
            final HttpGet request = buildGet(url);
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                return response.getStatusLine().getStatusCode();
            }
        }
    }
}
