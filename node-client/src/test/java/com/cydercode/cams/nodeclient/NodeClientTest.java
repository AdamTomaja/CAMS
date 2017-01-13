package com.cydercode.cams.nodeclient;

import com.cydercode.cams.datamodel.AppStatus;
import com.cydercode.cams.datamodel.Constants;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import org.junit.Rule;
import org.junit.Test;

import static com.cydercode.cams.datamodel.AppStatusBuilder.createAppStatusBuilder;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * @author Adam Tomaja
 */
public class NodeClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void statusShouldBeFetchedCorectly() {
        // given
        AppStatus statusResponse = createAppStatusBuilder()
                .setInstanceName("TestInstanceName")
                .withMetadata("Sys", "Win")
                .build();
        String statusBody = new Gson().toJson(statusResponse);

        NodeClient client = new NodeClient("http://localhost:" + wireMockRule.port());
        wireMockRule.stubFor(
                get(urlEqualTo(Constants.STATUS_PATH))
                        .willReturn(aResponse()
                                .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                .withBody(statusBody)));
        // when
        AppStatus status = client.getStatus();

        // then
        assertThat(status).isEqualTo(statusResponse);
    }
}