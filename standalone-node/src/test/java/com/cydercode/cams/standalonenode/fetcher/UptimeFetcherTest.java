package com.cydercode.cams.standalonenode.fetcher;

import com.cydercode.cams.datamodel.Constants;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Adam Tomaja
 */
public class UptimeFetcherTest {

    @Test
    public void uptimeShouldBeFetched() throws InterruptedException {
        // given
        UptimeFetcher fetcher = new UptimeFetcher();
        fetcher.init();

        // when
        Thread.sleep(100);
        Map<String, Object> metadata = fetcher.fetch();

        // then
        assertThat(metadata.get(Constants.SYSTEM_UPTIME)).isNotEqualTo(0);
        assertThat((long) metadata.get(Constants.NODE_UPTIME)).isGreaterThanOrEqualTo(100);
    }
}