package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
//开启熔断
@EnableCircuitBreaker
//@SpringCloudApplication//该注解等价于 @SpringBootApplication + @EnableDiscoveryClient + @EnableCircuitBreaker
/**
 * eureka
 * @EnableDiscoveryClient是spring提供的注解，注册中心使用eureka、zookeeper等 无需替换此注解
 * @EnableEurekaClient 是Eureka提供的注解，从eureka替换成其他网关的时候，需要替换此注解，因此不推荐使用
 */
// @EnableEurekaClient
@EnableDiscoveryClient
//启动feign客户端
@EnableFeignClients
public class  ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
