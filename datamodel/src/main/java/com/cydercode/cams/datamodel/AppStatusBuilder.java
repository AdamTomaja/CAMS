package com.cydercode.cams.datamodel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adam Tomaja
 */
public class AppStatusBuilder {

    String instanceName;
    Health health;
    long appUptime;
    long systemUptime;
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

    public AppStatusBuilder setAppUptime(long appUptime) {
        this.appUptime = appUptime;
        return this;
    }

    public AppStatusBuilder setSystemUptime(long systemUptime) {
        this.systemUptime = systemUptime;
        return this;
    }

    public AppStatusBuilder withMetadata(String name, Object value) {
        metadata.put(name, value);
        return this;
    }

    public AppStatus build() {
        AppStatus status = new AppStatus();

        status.setInstanceName(instanceName);
        status.setAppUptime(appUptime);
        status.setHealth(health);
        status.setSystemUptime(systemUptime);
        status.setMetadata(metadata);

        return status;
    }
}