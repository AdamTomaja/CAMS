package com.cydercode.cams;

/**
 * @author Adam Tomaja
 */
public class AppStatus {

    private Health health;

    private long appUptime;

    private long systemUptime;

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
