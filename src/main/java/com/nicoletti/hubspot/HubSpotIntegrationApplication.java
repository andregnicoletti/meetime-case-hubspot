package com.nicoletti.hubspot;

import com.nicoletti.hubspot.config.HubspotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(HubspotProperties.class)
public class HubSpotIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HubSpotIntegrationApplication.class, args);
	}

}
