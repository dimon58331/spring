package com.spring_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import static org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
public class MySecurityConfig{
    @Bean
    public UserDetailsManager userDetailsManager(){
        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        UserDetailsManager detailsManager = new InMemoryUserDetailsManager();
        detailsManager.createUser(userBuilder.username("Dzmitry")
                .password("12345678").roles("EMPLOYEE").build());

        detailsManager.createUser(userBuilder.username("Roman")
                .password("12345678").roles("HR").build());

        detailsManager.createUser(userBuilder.username("Dzmitry")
                .password("12345678").roles("HR", "MANAGER").build());

        return detailsManager;
    }

}
