package com.xuecheng.api.filesystem;

import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Alexlou on 2020/6/27.
 */
@Api(value = "文件管理接口",description = "文件管理接口，提供文件的增、删、改、查")
public interface FileSystemControllerApi {
    //文件上传
    @ApiOperation("文件上传")
    public UploadFileResult upload(MultipartFile multipartFile,
                                   String fileTag,
                                   String businesskey,
                                   String metadata);
}
