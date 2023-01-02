package com.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.DatabaseStartupValidator;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    private final String HR = "HR";
    private final String EMPLOYEE = "EMPLOYEE";
    private final String MANAGER = "MANAGER";

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests((authorizeRequests) -> authorizeRequests.anyRequest().authenticated())
//                .formLogin(withDefaults());
//
//        http.authorizeHttpRequests((authorizeHttpRequest) -> authorizeHttpRequest
//                .antMatchers("/").hasAnyRole(HR, EMPLOYEE, MANAGER)
//                .antMatchers("/hr-info/**").hasRole(HR)
//                .antMatchers("/manager-info/**").hasRole(MANAGER)).formLogin();

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole(HR, EMPLOYEE, MANAGER)
                .antMatchers("/hr-info/**").hasRole(HR)
                .antMatchers("/manager-info/**").hasRole(MANAGER)
                .and().formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsService manager = new JdbcUserDetailsManager(dataSource);
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles(EMPLOYEE)
//                .build());
//        manager.createUser(User.withDefaultPasswordEncoder()
//                .username("user2")
//                .password("user2")
//                .roles(HR)
//                .build());
//        manager.createUser(User.withDefaultPasswordEncoder()
//                .username("user3")
//                .password("user3")
//                .roles(HR, MANAGER)
//                .build());
        return manager;
    }
}
