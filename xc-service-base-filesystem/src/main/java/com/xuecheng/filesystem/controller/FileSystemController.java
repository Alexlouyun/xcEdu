package com.xuecheng.filesystem.controller;

import com.xuecheng.api.filesystem.FileSystemControllerApi;
import com.xuecheng.filesystem.service.FileSystemSerivce;
import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Alexlou on 2020/6/28.
 */
@RestController
@RequestMapping("/filesystem")
public class FileSystemController implements FileSystemControllerApi {
    @Autowired
    FileSystemSerivce fileSystemSerivce;

    @Override
    @PostMapping("/upload")
    public UploadFileResult upload(MultipartFile multipartFile, String fileTag, String businesskey, String metadata) {
        return fileSystemSerivce.upload(multipartFile,fileTag,businesskey,metadata);
    }
}
