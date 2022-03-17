package com.project.test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testQuatz2
        extends QuartzJobBean
{

//    static int i=1;
//    public void run (){
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("风清扬第"+i+"次练功时间==========="+simpleDateFormat.format(date));
//        ++i;
//    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //定时任务业务逻辑写在以下方法中
    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(name + " 每次晨练时间===========" +simpleDateFormat.format(date));
    }

}
