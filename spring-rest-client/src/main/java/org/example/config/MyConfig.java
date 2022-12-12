package org.example.config;

import org.example.Communication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
@ComponentScan("org.example")
public class MyConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Communication communication(){
        return new Communication();
    }

}
