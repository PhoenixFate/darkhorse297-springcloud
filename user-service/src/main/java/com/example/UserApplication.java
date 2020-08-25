package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.mapper")

/**
 * @EnableDiscoveryClient是spring提供的注解，注册中心使用eureka、zookeeper等 无需替换此注解
 *
 * @EnableEurekaClient 是Eureka提供的注解，从eureka替换成其他网关的时候，需要替换此注解，因此不推荐使用
 *
 */
// @EnableEurekaClient
@EnableDiscoveryClient


@EnableSwagger2
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
