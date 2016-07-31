package com.cydercode.cams;

/**
 * @author Adam Tomaja
 */
public class AppStatus {

    private String instanceName;

    private Health health;

    private long appUptime;

    private long systemUptime;

    public AppStatus(String instanceName, Health health, long appUptime, long systemUptime) {
        this.instanceName = instanceName;
        this.health = health;
        this.appUptime = appUptime;
        this.systemUptime = systemUptime;
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
}
