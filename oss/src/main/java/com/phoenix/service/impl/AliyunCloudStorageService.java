package com.phoenix.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.phoenix.config.AliOssConstantConfig;
import com.phoenix.service.CloudStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储
 *
 * @author phoenix
 */
@Slf4j
@Service
@EnableConfigurationProperties(AliOssConstantConfig.class)
@AllArgsConstructor
public class AliyunCloudStorageService extends CloudStorageService {

    private final AliOssConstantConfig aliOssConstantConfig;

    @Override
    public String upload(byte[] data, String suffix) throws Exception {
        return upload(new ByteArrayInputStream(data), getPath(aliOssConstantConfig.getFilePath(),suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) throws Exception {
        log.info("------OSS文件上传开始--------");
        String endpoint=aliOssConstantConfig.getEndPoint();
        String accessKeyId=aliOssConstantConfig.getAccessKey();
        String accessKeySecret=aliOssConstantConfig.getSecretKey();
        String bucketName=aliOssConstantConfig.getBucket();
        //创建阿里云ossClient
        OSSClient client=new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                //设置照片公共读
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 上传文件
            PutObjectResult result = client.putObject(bucketName, path, inputStream);
            if (result == null) {
                throw new Exception("上传文件失败");
            }
            log.info("------OSS文件上传成功------" + path);
        }catch (Exception e){
            throw new Exception("上传文件失败，请检查配置信息");
        }
        return aliOssConstantConfig.getAddress()+ "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) throws Exception {
        return upload(data, getPath(aliOssConstantConfig.getFilePath(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) throws Exception {
        return upload(inputStream, getPath(aliOssConstantConfig.getFilePath(), suffix));
    }
}
