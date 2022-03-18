package com.project.controller;

import com.project.business.zj_Report_Kd_Business;
import com.project.business.zj_Report_RhItv_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class zj_Report_RhItv_Controller {


//    public static void main(String[] args) throws Exception {
//
//        zj_Report_RhItv_Business zj_Report_RhItv_Business=new zj_Report_RhItv_Business();
//        zj_Report_RhItv_Business.report_RhItv_Zj();
//
//    }

    public static void report_RhItv_Zj() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_RhItv_Business zj_Report_RhItv_Business=new zj_Report_RhItv_Business();
        zj_Report_RhItv_Business.report_RhItv_Zj();
    }


}
