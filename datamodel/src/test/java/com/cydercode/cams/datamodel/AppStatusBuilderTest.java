package com.cydercode.cams.datamodel;

import org.junit.Test;

import static com.cydercode.cams.datamodel.AppStatusBuilder.createAppStatusBuilder;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Adam Tomaja
 */
public class AppStatusBuilderTest {

    @Test
    public void appStatusShouldBeCreated() {
        // given
        AppStatusBuilder builder = createAppStatusBuilder()
                .setInstanceName("Test")
                .setSystemUptime(1234)
                .setAppUptime(123)
                .withMetadata("Sys", "Windows")
                .setHealth(Health.HEALTHY);

        // when
        AppStatus appStatus = builder.build();

        // then
        assertThat(appStatus.getAppUptime()).isEqualTo(123);
        assertThat(appStatus.getInstanceName()).isEqualTo("Test");
        assertThat(appStatus.getSystemUptime()).isEqualTo(1234);
        assertThat(appStatus.getMetadata().get("Sys")).isEqualTo("Windows");
        assertThat(appStatus.getHealth()).isEqualTo(Health.HEALTHY);
    }
}