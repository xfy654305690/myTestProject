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
        JobDetail jobDetailZss1 = JobBuilder.newJob(DoJob1.class)
                .withIdentity("jobZss1", "groupZss1").build();
        // 3、构建Trigger实例,每隔1s执行一次
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 5000);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .usingJobData("trigger1", "这是jobDetail1的trigger")
                .startNow()//立即生效
                .startAt(startDate)
                //.withSchedule(CronScheduleBuilder.cronSchedule("/3 * * * * ?"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 30 23 * * ?"))
                .build();
        CronTrigger cronTriggerZss1 = TriggerBuilder.newTrigger().withIdentity("triggerZss1", "triggerGroupZss1")
                .usingJobData("triggerZss1", "这是jobDetail1的triggerZss1")
                .startNow()//立即生效
                .startAt(startDate)
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ? "))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 45 11,13,15,17,19,21 * * ? *"))
                .build();

        //4、执行
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.scheduleJob(jobDetailZss1, cronTriggerZss1);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();



    }


}
