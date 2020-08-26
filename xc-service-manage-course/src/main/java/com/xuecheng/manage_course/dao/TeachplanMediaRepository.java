package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.TeachplanMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexlou on 2020/7/8.
 */
@Repository
public interface TeachplanMediaRepository  extends JpaRepository<TeachplanMedia,String> {
}
