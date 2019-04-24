package com.getguru.interview.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.getguru.interview.resources.DataResource;

/**
 * Created by Jalisa on 4/21/19.
 */
public class AppHealthCheck extends HealthCheck {

    private final DataResource resource;

    public AppHealthCheck(DataResource resource) {
        this.resource = resource;
    }

    @Override
    protected Result check() throws Exception {
        if(resource.ping()) {
            return Result.healthy("Able to retrieve earthquake data");
        }

        return Result.unhealthy("Cannot retrieve earthquake data");
    }
}

