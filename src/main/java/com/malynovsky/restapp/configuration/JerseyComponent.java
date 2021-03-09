package com.malynovsky.restapp.configuration;

import com.malynovsky.restapp.resource.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyComponent extends ResourceConfig {
    public JerseyComponent() {
        register(TeamResource.class); //TODO try to practice with creating custom annotations
        register(ResultApiResource.class);
        register(CountryResource.class);
        register(PlayerResource.class);
        register(ReportResource.class);
    }
}
