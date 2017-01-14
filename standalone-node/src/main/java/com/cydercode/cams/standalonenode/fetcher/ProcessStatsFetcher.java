package com.cydercode.cams.standalonenode.fetcher;

import com.cydercode.cams.datamodel.Constants;
import com.google.common.collect.ImmutableMap;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.cydercode.cams.datamodel.Constants.*;
import static java.lang.management.ManagementFactory.getPlatformMXBean;
import static java.lang.management.ManagementFactory.getThreadMXBean;

/**
 * @author Adam Tomaja
 */
@Component
public class ProcessStatsFetcher implements Fetcher {

    @Override
    public Map<String, Object> fetch() {
        OperatingSystemMXBean osBean = getPlatformMXBean(OperatingSystemMXBean.class);

        return ImmutableMap.of(
                THREAD_COUNT, getThreadMXBean().getThreadCount(),
                PROCESS_CPU_LOAD, osBean.getProcessCpuLoad(),
                SYSTEM_CPU_LOAD, osBean.getSystemCpuLoad(),
                FREE_MEMORY, osBean.getFreePhysicalMemorySize(),
                FREE_SWAP, osBean.getFreeSwapSpaceSize()
        );
    }
}
