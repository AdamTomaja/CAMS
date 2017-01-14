package com.cydercode.cams.standalonenode.fetcher;

import com.cydercode.cams.datamodel.Constants;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

/**
 * @author Adam Tomaja
 */
@Component
public class FreeDiskSpaceFetcher implements Fetcher {

    @Override
    public Map<String, Object> fetch() {
        File rootFile = new File("/");

        return of(Constants.USABLE_DISK_SPACE, rootFile.getUsableSpace(),
                Constants.FREE_DISK_SPACE, rootFile.getFreeSpace(),
                Constants.TOTAL_DISK_SPACE, rootFile.getTotalSpace());
    }
}
