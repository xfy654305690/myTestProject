package com.project.test;

import com.project.controller.zj_Report_Zss_Controller;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoJob1 implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        zj_Report_Zss_Controller zj_Report_Zss_Controller=new zj_Report_Zss_Controller();
        try {
            //zj_Report_Zss_Controller.report_Zss_Zj();
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println("JOB风清扬"+simpleDateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
