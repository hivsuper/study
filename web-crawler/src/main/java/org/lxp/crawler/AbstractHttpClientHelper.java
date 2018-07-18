package org.lxp.crawler;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.HttpGet;

public abstract class AbstractHttpClientHelper {
    protected int maxTotalConnection = 10;

    public abstract List<Integer> batchGet(List<String> urls) throws IOException;

    protected HttpGet buildGet(String url) {
        HttpGet get = new HttpGet(url);
        get.setHeader("Connection", "close");
        return get;
    }
}
