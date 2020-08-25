package com.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    /**
     * 传统的restTemplate来进行远程调用
     * @return
     */
    @Bean(name = "default")
    RestTemplate restTemplateDefault() {
        return new RestTemplate();
    }


    /**
     * 使用@LoadBalanced注解进行远程调用，自动负载均衡
     * @return
     */
    @Bean(name = "balance")
    @LoadBalanced
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }


    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
