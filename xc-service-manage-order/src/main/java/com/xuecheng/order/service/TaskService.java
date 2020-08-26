package com.xuecheng.order.service;

import com.github.pagehelper.Page;
import com.xuecheng.framework.domain.task.XcTask;
import com.xuecheng.order.dao.XcTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Alexlou on 2020/7/11.
 */
@Service
public class TaskService {
    @Autowired
    XcTaskRepository xcTaskRepository;
    //查询前N条任务
    public List<XcTask> findTaskList(Date updataTime,int size){
        //设置分页参数
        Pageable pageable = new PageRequest(0,size);
        Page<XcTask> all = xcTaskRepository.findByUpdateTimeBefore(pageable, updataTime);
        List<XcTask> list = all.getResult();
        return list;
    }
}
