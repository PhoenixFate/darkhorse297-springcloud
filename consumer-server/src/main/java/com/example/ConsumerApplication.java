package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker

@SpringCloudApplication//该注解等价于上面三个注解
public class  ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
