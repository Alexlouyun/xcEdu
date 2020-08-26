package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.TeachplanMedia;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Alexlou on 2020/6/10.
 */
@Api(value="课程管理接口",description = "课程管理接口，提供课程的管理、查询接口")
public interface CourseControllerApi {
    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachplanList(String courseId);
    @ApiOperation("添加课程计划")
    ResponseResult addTeachplan(Teachplan teachplan);
    @ApiOperation("课程视图查询")
    CourseView courseView(String id);
    @ApiOperation("预览课程")
    public CoursePublishResult preview(String id);
    @ApiOperation("保存课程计划与媒资的关联信息")
    public ResponseResult saveMedia(TeachplanMedia teachplanMedia);
    @ApiOperation("课程查询")
    public QueryResponseResult findCouresList(int page, int size, CourseListRequest courseListRequest);
}
