package com.project.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Myschedule {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(DoJob.class)
                .withIdentity("job1", "group1").build();
        // 3、构建Trigger实例,每隔1s执行一次
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 5000);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .usingJobData("trigger1", "这是jobDetail1的trigger")
                .startNow()//立即生效
                .startAt(startDate)
                .withSchedule(CronScheduleBuilder.cronSchedule("/30 * * * * ?"))
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 45 10 * * ?"))
                .build();
        //4、执行
        scheduler.scheduleJob(jobDetail, cronTrigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();


        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory1 = new StdSchedulerFactory();
        Scheduler scheduler1 = schedulerFactory1.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail1 = JobBuilder.newJob(DoJob1.class)
                .withIdentity("job2", "group2").build();
        // 3、构建Trigger实例,每隔1s执行一次
        Date startDate1 = new Date();
        startDate1.setTime(startDate.getTime() + 5000);
        CronTrigger cronTrigger1 = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup2")
                .usingJobData("trigger2", "这是jobDetail2的trigger")
                .startNow()//立即生效
                .startAt(startDate)
                .withSchedule(CronScheduleBuilder.cronSchedule("/10 * * * * ?"))
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 45 10 * * ?"))
                .build();
        //4、执行
        scheduler1.scheduleJob(jobDetail1, cronTrigger1);
        System.out.println("--------scheduler start ! ------------");
        scheduler1.start();

//        //睡眠
//        TimeUnit.MINUTES.sleep(1);
//        scheduler.shutdown();
//        System.out.println("--------scheduler shutdown ! ------------");


    }


}
