package com.cydercode.cams;

import org.springframework.web.client.RestTemplate;

/**
 * @author Adam Tomaja
 */
public class NodeClient {

    private RestTemplate restTemplate = new RestTemplate();

    private String nodeUrl;

    public NodeClient(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public AppStatus getStatus() {
        return restTemplate.getForObject(nodeUrl + NodeConstants.STATUS_PATH, AppStatus.class);
    }
}
