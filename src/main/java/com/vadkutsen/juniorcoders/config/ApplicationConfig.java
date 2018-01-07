package com.vadkutsen.juniorcoders.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.vadkutsen.juniorcoders.backend.persistence.repositories")
@EntityScan(basePackages = "com.vadkutsen.juniorcoders.backend.persistence.domain.backend")
@EnableTransactionManagement
@PropertySource("file:///${user.home}/spring/application-common.properties")
@PropertySource("file:///${user.home}/spring/stripe.properties")
public class ApplicationConfig {
}
