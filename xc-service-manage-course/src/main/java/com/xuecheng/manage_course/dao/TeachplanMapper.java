package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Alexlou on 2020/6/25.
 */
@Mapper
public interface TeachplanMapper {
       TeachplanNode selectList(String courseId);
}
