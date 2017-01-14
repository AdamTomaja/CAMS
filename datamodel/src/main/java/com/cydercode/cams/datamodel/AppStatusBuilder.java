package com.cydercode.cams.datamodel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adam Tomaja
 */
public class AppStatusBuilder {

    String instanceName;
    Health health;
    Map<String, Object> metadata = new HashMap<>();

    private AppStatusBuilder() {
        //
    }

    public static AppStatusBuilder createAppStatusBuilder() {
        return new AppStatusBuilder();
    }

    public AppStatusBuilder setInstanceName(String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    public AppStatusBuilder setHealth(Health health) {
        this.health = health;
        return this;
    }

    public AppStatusBuilder withMetadata(String name, Object value) {
        metadata.put(name, value);
        return this;
    }

    public AppStatusBuilder withMetadatas(Map<String, Object> metadatas) {
        metadata.putAll(metadatas);
        return this;
    }

    public AppStatus build() {
        AppStatus status = new AppStatus();

        status.setInstanceName(instanceName);
        status.setHealth(health);
        status.setMetadata(metadata);

        return status;
    }
}