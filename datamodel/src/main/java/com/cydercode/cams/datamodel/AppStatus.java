package com.cydercode.cams.datamodel;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Map;

/**
 * @author Adam Tomaja
 */
public class AppStatus {

    private String instanceName;

    private Health health;

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

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("instanceName", instanceName)
                .add("health", health)
                .add("metadata", metadata)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppStatus appStatus = (AppStatus) o;
        return Objects.equal(instanceName, appStatus.instanceName) &&
                health == appStatus.health &&
                Objects.equal(metadata, appStatus.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(instanceName, health, metadata);
    }
}
