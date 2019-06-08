package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate getRestTemplate(){
//		默认空参数的RestTemplate即为HttpUrlConnection
        return new RestTemplate();
    }
}
