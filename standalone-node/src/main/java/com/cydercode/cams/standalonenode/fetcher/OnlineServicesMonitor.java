package com.cydercode.cams.standalonenode.fetcher;

import com.cydercode.cams.datamodel.Constants;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adam Tomaja
 */
@Component
public class OnlineServicesMonitor implements Fetcher {

    @Value("#{'${cams.services}'.split(',')}")
    List<String> urls;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Map<String, Object> fetch() {
        Map<String, Map<String, String>> result = new HashMap<>();

        urls.forEach(url -> {
            try {
                restTemplate.getForEntity(url, String.class);
                result.put(url, ImmutableMap.of("status", "ok"));
            } catch (Exception e) {
                result.put(url, ImmutableMap.of("status", "error", "message", e.getMessage()));
            }
        });

        return ImmutableMap.of(Constants.ONLINE_SERVICES, result);
    }
}
