package com.project.test;

import com.project.controller.zj_Report_Zcy_Zss_Controller;
import com.project.controller.zj_Report_Zss_Controller;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DoJob2 implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            zj_Report_Zss_Controller zj_Report_Zss_Controller=new zj_Report_Zss_Controller();
            zj_Report_Zcy_Zss_Controller.Zj_Report_Zcy_Zss();
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println("JOB风清扬"+simpleDateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
