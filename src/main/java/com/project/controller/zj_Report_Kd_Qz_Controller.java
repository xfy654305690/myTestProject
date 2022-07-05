package com.project.controller;

import com.project.business.zj_Report_Kd_Business;
import com.project.business.zj_Report_Kd_Qz_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Kd_Qz_Controller {


    public static void report_Kd_Qz_Zj() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        zj_Report_Kd_Qz_Business Zj_Report_Kd_Qz_Business=new zj_Report_Kd_Qz_Business();
        Zj_Report_Kd_Qz_Business.report_Kd_Qz_Zj();

    }

    public static void report_Kd_Qz_Zj_Js() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        zj_Report_Kd_Qz_Business Zj_Report_Kd_Qz_Business=new zj_Report_Kd_Qz_Business();
        Zj_Report_Kd_Qz_Business.report_Kd_Qz_Zj_Js();

    }

//    public static void report_Kd_Qz_Zj_630() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//
//        zj_Report_Kd_Qz_Business Zj_Report_Kd_Qz_Business=new zj_Report_Kd_Qz_Business();
//        Zj_Report_Kd_Qz_Business.report_Kd_Qz_Zj_630();
//
//    }



}
