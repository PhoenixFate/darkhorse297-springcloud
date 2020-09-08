package com.phoenix.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="aliyun.oss")
public class AliOssConstantConfig {

    private  String address;

    private  String endPoint;

    private  String accessKey;

    private  String secretKey;

    private  String bucket;

    private String filePath;

}