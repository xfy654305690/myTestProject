package com.project.controller;

import com.project.business.zj_Report_Kd_Business;
import com.project.business.zj_Report_Xubao_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Kd_Controller {

//
//    public static void main(String[] args) throws Exception {
//
//        zj_Report_Kd_Business Zj_Report_Kd_Business=new zj_Report_Kd_Business();
//        Zj_Report_Kd_Business.report_Kd_Zj();
//
//    }
    public static void report_Kd_Zj_Js() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        zj_Report_Kd_Business Zj_Report_Kd_Business=new zj_Report_Kd_Business();
        Zj_Report_Kd_Business.report_Kd_Zj_Js();

    }

    public static void report_Kd_Zj() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        zj_Report_Kd_Business Zj_Report_Kd_Business=new zj_Report_Kd_Business();
        Zj_Report_Kd_Business.report_Kd_Zj();

    }


}
