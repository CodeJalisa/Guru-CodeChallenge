package com.getguru.interview.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.getguru.interview.resources.DataResource;
import com.getguru.interview.resources.Earthquake;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Jalisa on 4/21/19.
 */
public class AppHealthcheckTest {

    private final DataResource resource = mock(DataResource.class);

    @Test
    public void testCheck_Healthy() throws Exception{
        AppHealthCheck check = new AppHealthCheck(new DataResource(){});

        HealthCheck.Result actual = check.check();

        Assert.assertEquals(HealthCheck.Result.healthy("Able to retrieve earthquake data"), actual);

    }

    @Test
    public void testCheck_Unhealthy() throws Exception{
        when(resource.getEarthquakes(Optional.empty())).thenReturn(new ArrayList<>());

        AppHealthCheck check = new AppHealthCheck(resource);

        HealthCheck.Result actual = check.check();

        Assert.assertEquals(HealthCheck.Result.unhealthy("Cannot retrieve earthquake data"), actual);

    }
}
