package com.cydercode.cams.datamodel;

import org.junit.Test;

import static com.cydercode.cams.datamodel.AppStatusBuilder.createAppStatusBuilder;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Adam Tomaja
 */
public class AppStatusTest {

    @Test
    public void twoStatusInstancesShouldEquals() {
        // given
        AppStatus statusA = createAppStatusBuilder()
                .withMetadata("sys", "win")
                .build();

        AppStatus statusB = createAppStatusBuilder()
                .withMetadata("sys", "win")
                .build();

        // when then
        assertThat(statusA).isEqualTo(statusB);
    }

    @Test
    public void twoStatusInstancesShouldNotEquals() {
        // given
        AppStatus statusA = createAppStatusBuilder()
                .withMetadata("sys", "win")
                .setInstanceName("CAMS")
                .build();

        AppStatus statusB = createAppStatusBuilder()
                .withMetadata("sys", "win")
                .build();

        // when then
        assertThat(statusA).isNotEqualTo(statusB);
    }
}
