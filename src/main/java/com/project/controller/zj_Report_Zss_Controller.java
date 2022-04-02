package com.project.controller;

import com.project.business.zj_Report_Kd_Business;
import com.project.business.zj_Report_Zss_Business;
import com.project.business.zj_Report_Zt_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Zss_Controller {

    public static void main(String[] args) throws Exception {

        zj_Report_Zss_Business zj_Report_Zss_Business=new zj_Report_Zss_Business();
        zj_Report_Zss_Business.report_Zss_Zj();

    }

//    public static void report_Zss_Zj() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        zj_Report_Zss_Business zj_Report_Zss_Business=new zj_Report_Zss_Business();
//        zj_Report_Zss_Business.report_Zss_Zj();
//    }

}
