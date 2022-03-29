package com.project.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoJob1 implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        doDetail doDetail=new  doDetail();
        try {
            doDetail.doDetailTest();
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println("JOB风清扬"+simpleDateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
