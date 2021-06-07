package org.lxp.crawler;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

public class OKHttpHelperTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public WireMockRule forecastIoService = new WireMockRule();

    @Before
    public void setUp() {
        forecastIoService.start();
    }

    @After
    public void tearDown() {
        forecastIoService.stop();
    }

    @Test
    public void testBatchGet() {
        OKHttpHelper helper = new OKHttpHelper();
        final String crawlerUrl = "http://127.0.0.1:8080/crawler-test";
        forecastIoService.stubFor(get(urlEqualTo("/crawler-test")).willReturn(aResponse().withFixedDelay(10).withStatus(HttpStatus.SC_OK)));
        assertThat(helper.get(crawlerUrl)).isEqualTo(200);
    }
}