package com.cydercode.cams.standalonenode;

import com.cydercode.cams.datamodel.AppStatus;
import com.cydercode.cams.standalonenode.fetcher.FreeDiskSpaceFetcher;
import com.cydercode.cams.standalonenode.fetcher.UptimeFetcher;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cydercode.cams.datamodel.Constants.*;
import static com.cydercode.cams.datamodel.Health.HEALTHY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Adam Tomaja
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    FreeDiskSpaceFetcher freeDiskSpaceFetcher;

    @MockBean
    UptimeFetcher uptimeFetcher;

    @Test
    public void statusShouldBeReturned() {
        // given
        given(freeDiskSpaceFetcher.fetch()).willReturn(ImmutableMap.of(FREE_DISK_SPACE, 1234));
        given(uptimeFetcher.fetch()).willReturn(ImmutableMap.of(NODE_UPTIME, 4321));

        // when
        ResponseEntity<AppStatus> response = restTemplate.getForEntity(STATUS_PATH, AppStatus.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        AppStatus appStatus = response.getBody();

        assertThat(appStatus.getInstanceName())
                .isEqualTo("IntegrationTestInstance");

        assertThat(appStatus.getMetadata().get(NODE_TYPE))
                .isEqualTo(NODE_TYPE_STANDALONE);

        assertThat(appStatus.getMetadata().get(FREE_DISK_SPACE))
                .isEqualTo(1234);

        assertThat(appStatus.getMetadata().get(NODE_UPTIME))
                .isEqualTo(4321);

        assertThat(appStatus.getHealth())
                .isEqualTo(HEALTHY);
    }
}