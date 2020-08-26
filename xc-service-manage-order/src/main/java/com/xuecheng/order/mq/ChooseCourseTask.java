package com.xuecheng.order.mq;

import com.xuecheng.framework.domain.task.XcTask;
import com.xuecheng.order.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alexlou on 2020/7/11.
 */
@Component
public class ChooseCourseTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseCourseTask.class);
    @Autowired
    TaskService taskService;
    //定时发送添加选课任务
    @Scheduled(cron = "0/3 * * * * *")
    public void sendChooseCourseTask(){
        //得到1min之前的时间
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(GregorianCalendar.MINUTE,-1);
        Date time = calendar.getTime();
        List<XcTask> taskList = taskService.findTaskList(time, 1000);
    }
    //定义任务调度策略
//    @Scheduled(cron = "0/3 * * * * *")//每隔3秒执行任务
    @Scheduled(fixedRate = 3000)//任务开始执行后3秒执行下次任务
//    @Scheduled(fixedDelay = 5000)//任务执行结束后5秒执行下次任务
    public void task1(){
        LOGGER.info("================测试定时任务1开始=================");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("================测试定时任务1结束=================");
    }
    @Scheduled(fixedRate = 3000)//任务开始执行后3秒执行下次任务
    public void task2(){
        LOGGER.info("================测试定时任务2开始=================");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("================测试定时任务2结束=================");
        AtomicInteger
    }
}
