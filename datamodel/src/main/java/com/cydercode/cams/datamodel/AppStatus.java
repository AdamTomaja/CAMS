package com.cydercode.cams.datamodel;

import com.google.common.base.Objects;

import java.util.Map;

/**
 * @author Adam Tomaja
 */
public class AppStatus {

    private String instanceName;

    private Health health;

    private long appUptime;

    private long systemUptime;

    private Map<String, Object> metadata;

    public AppStatus() {

    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public long getAppUptime() {
        return appUptime;
    }

    public void setAppUptime(long appUptime) {
        this.appUptime = appUptime;
    }

    public long getSystemUptime() {
        return systemUptime;
    }

    public void setSystemUptime(long systemUptime) {
        this.systemUptime = systemUptime;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "AppStatus{" +
                "instanceName='" + instanceName + '\'' +
                ", health=" + health +
                ", appUptime=" + appUptime +
                ", systemUptime=" + systemUptime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppStatus appStatus = (AppStatus) o;
        return appUptime == appStatus.appUptime &&
                systemUptime == appStatus.systemUptime &&
                Objects.equal(instanceName, appStatus.instanceName) &&
                health == appStatus.health &&
                Objects.equal(metadata, appStatus.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(instanceName, health, appUptime, systemUptime, metadata);
    }
}
