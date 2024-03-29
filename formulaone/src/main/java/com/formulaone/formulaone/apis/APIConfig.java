package com.formulaone.formulaone.apis;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class APIConfig extends ResourceConfig {
	public APIConfig() {
		register(DriverService.class);
		register(TeamService.class);
		register(RaceService.class);
		
		register(CORSFilter.class);
	}
}
