package com.phoenix.web;

import com.phoenix.service.CloudStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/oss")
@AllArgsConstructor
public class UpLoadController {

    private final CloudStorageService cloudStorageService;

    /** 文件上传*/
    @PostMapping(value = "/uploadFile")
    public String uploadBlog(@RequestParam("file") MultipartFile file) throws Exception {
        //上传文件
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        return cloudStorageService.upload(file.getBytes(), suffix);
    }

}

