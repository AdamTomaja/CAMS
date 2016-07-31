package com.cydercode.cams.datamodel;

/**
 * @author Adam Tomaja
 */
public class AppStatusBuilder {
    private String instanceName;
    private Health health;
    private long appUptime;
    private long systemUptime;

    private AppStatusBuilder() {
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

    public AppStatus create() {
        return new AppStatus(instanceName, health, appUptime, systemUptime);
    }
}