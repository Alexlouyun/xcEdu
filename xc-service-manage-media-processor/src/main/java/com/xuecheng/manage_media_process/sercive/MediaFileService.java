package com.xuecheng.manage_media_process.sercive;

import com.xuecheng.framework.domain.media.MediaFile;
import com.xuecheng.framework.domain.media.request.QueryMediaFileRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_media_process.dao.MediaFileRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * Created by Alexlou on 2020/7/8.
 */
@Service
public class MediaFileService {
    private static Logger logger = LoggerFactory.getLogger(MediaFileService.class);
    @Autowired
    MediaFileRepository mediaFileRepository;

    //  文件列表分页查询
    public QueryResponseResult findList(int page, int size, QueryMediaFileRequest queryMediaFileRequest) {
        //查询条件
        MediaFile mediaFile = new MediaFile();
        if (queryMediaFileRequest == null){
            queryMediaFileRequest = new QueryMediaFileRequest();
        }
        //查询条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                            .withMatcher("tag",ExampleMatcher.GenericPropertyMatchers.contains())//tag字段模糊匹配
                            .withMatcher("fileOriginalName",ExampleMatcher.GenericPropertyMatchers.contains())//文件原始名称模糊匹配
                            .withMatcher("processStatus",ExampleMatcher.GenericPropertyMatchers.exact());//处理状态精确匹配（默认）

        //查询条件对象
        if (StringUtils.isNotEmpty(queryMediaFileRequest.getTag())){
            mediaFile.setTag(queryMediaFileRequest.getTag());
        }
        if (StringUtils.isNotEmpty(queryMediaFileRequest.getFileOriginalName())){
            mediaFile.setTag(queryMediaFileRequest.getFileOriginalName());
        }
        if (StringUtils.isNotEmpty(queryMediaFileRequest.getProcessStatus())){
            mediaFile.setTag(queryMediaFileRequest.getProcessStatus());
        }
        //定义Example实例
        Example<MediaFile> fileExample = Example.of(mediaFile, exampleMatcher);
        if (page<=0){
            page = page-1;
        }
        if (size<=0){
            size = 10;
        }
        //分页参数
        Pageable pageable = PageRequest.of(page,size);
        //分页查询
        Page<MediaFile> all = mediaFileRepository.findAll(fileExample, pageable);
        QueryResult<MediaFile> mediaFileQueryResult = new QueryResult<>();
        mediaFileQueryResult.setTotal(all.getTotalElements());
        mediaFileQueryResult.setList(all.getContent());
        return new QueryResponseResult(CommonCode.SUCCESS,mediaFileQueryResult);


    }
}
