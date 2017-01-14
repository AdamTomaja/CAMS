package com.cydercode.cams.standalonenode.fetcher;

import com.cydercode.cams.datamodel.Constants;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Adam Tomaja
 */
public class ProcessStatsFetcherTest {

    @Test
    public void shouldFetchStats() {
        // given
        ProcessStatsFetcher fetcher = new ProcessStatsFetcher();

        // when
        Map<String, Object> metadata = fetcher.fetch();

        // then
        assertThat((int) metadata.get(Constants.THREAD_COUNT)).isNotZero();
        assertThat((double) metadata.get(Constants.SYSTEM_CPU_LOAD)).isNotZero();
        assertThat((double) metadata.get(Constants.PROCESS_CPU_LOAD)).isNotZero();
        assertThat((long) metadata.get(Constants.FREE_MEMORY)).isNotZero();
        assertThat((long) metadata.get(Constants.FREE_SWAP)).isNotZero();
    }
}