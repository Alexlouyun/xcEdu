package com.xuecheng.order.dao;

import com.github.pagehelper.Page;
import com.xuecheng.framework.domain.task.XcTask;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Alexlou on 2020/7/11.
 */
@Repository
public interface XcTaskRepository extends JpaRepository<XcTask,String> {
    //查询某个时间之前的前N条任务
    Page<XcTask> findByUpdateTimeBefore(Pageable pageable, Date updatetime);
}
