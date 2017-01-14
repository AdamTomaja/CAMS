package com.cydercode.cams.standalonenode.fetcher;


import com.cydercode.cams.datamodel.Constants;
import com.cydercode.cams.standalonenode.fetcher.FreeDiskSpaceFetcher;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Adam Tomaja
 */
public class FreeDiskSpaceFetcherTest {

    @Test
    public void getFreeDiskSpace() throws Exception {
        // given
        FreeDiskSpaceFetcher fetcher = new FreeDiskSpaceFetcher();

        // when
        Map<String, Object> metadata = fetcher.fetch();

        long usableDiskSpace = (long) metadata.get(Constants.USABLE_DISK_SPACE);
        long totalDiskSpace = (long) metadata.get(Constants.TOTAL_DISK_SPACE);
        long freeDiskSpace = (long) metadata.get(Constants.FREE_DISK_SPACE);

        // then
        assertThat(usableDiskSpace).isNotZero();
        assertThat(totalDiskSpace).isNotZero();
        assertThat(freeDiskSpace).isNotZero();
    }
}