package com.vadkutsen.juniorcoders.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    /** Public URLs */
    private static final String[] PUBLIC_MATCHERS = {
        "/webjars/**",
        "/css/**",
        "/js/**",
        "/images/**",
        "/",
        "/about/**",
        "/contact/**",
            "/console/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> activeRpofiles = Arrays.asList(env.getActiveProfiles());
        if (activeRpofiles.contains("dev")) {
            http.csrf().disable();
            http.headers().frameOptions().disable();
        }

        http
            .authorizeRequests()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/payload")
            .failureUrl("/login?error").permitAll()
            .and()
            .logout().permitAll();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password("password")
            .roles("USER");
    }

}
