package com.project.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DoJob  implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        doDetail doDetail=new  doDetail();
        try {
            //doDetail.doDetailTest();
            System.out.println("开始了");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
