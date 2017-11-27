package com.vadkutsen.juniorcoders.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/.spring/application-dev.properties")
public class DevelopmentConfig {
	
}
