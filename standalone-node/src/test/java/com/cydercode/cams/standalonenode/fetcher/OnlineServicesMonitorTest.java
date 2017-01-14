package com.cydercode.cams.standalonenode.fetcher;

import com.cydercode.cams.datamodel.Constants;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.collect.ImmutableMap;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Adam Tomaja
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineServicesMonitorTest {

    @Autowired
    OnlineServicesMonitor monitor;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void servicesShouldBeMonitoredCorrectly() {
        // given
        wireMockRule.stubFor(get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(OK.value())));

        // when
        Map<String, Object> metadata = monitor.fetch();

        // then
        Map<String, Map<String, String>> servicesStatus = (Map<String, Map<String, String>>) metadata.get(Constants.ONLINE_SERVICES);

        assertThat(servicesStatus.get("http://localhost:" + wireMockRule.port()))
                .isEqualTo(ImmutableMap.of("status", "ok"));

        assertThat(servicesStatus.get("http://sfdhajsdfh").get("status"))
                .isEqualTo("error");

        verify(1, getRequestedFor(urlEqualTo("/")));
    }
}