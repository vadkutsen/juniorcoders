package com.vadkutsen.juniorcoders.config;

import com.vadkutsen.juniorcoders.backend.service.EmailService;
import com.vadkutsen.juniorcoders.backend.service.MockEmailService;
import com.vadkutsen.juniorcoders.backend.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/spring/application-prod.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
