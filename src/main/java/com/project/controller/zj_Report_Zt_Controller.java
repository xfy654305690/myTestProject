package com.project.controller;


import com.project.business.zj_Report_Zt_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Zt_Controller {


//    public static void main(String[] args) throws Exception {
//
//        zj_Report_Zt_Business Zj_Report_Zt_Business=new zj_Report_Zt_Business();
//        Zj_Report_Zt_Business.report_Zt_Zj();
//
//    }

    public static void report_Zt_Zj() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Zt_Business Zj_Report_Zt_Business=new zj_Report_Zt_Business();
        Zj_Report_Zt_Business.report_Zt_Zj();
    }

    public static void report_Zt_Zj_Js() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Zt_Business Zj_Report_Zt_Business=new zj_Report_Zt_Business();
        Zj_Report_Zt_Business.report_Zt_Zj_Js();
    }
    public static void report_Zt_Zj_DoData()throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Zt_Business Zj_Report_Zt_Business=new zj_Report_Zt_Business();
        Zj_Report_Zt_Business.report_Tcf_Zj_DoData_NowMonth();

    }
    public static void report_Zt_Zj_Js_DoData()throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Zt_Business Zj_Report_Zt_Business=new zj_Report_Zt_Business();
        Zj_Report_Zt_Business.report_Xubao_Zj_DoData_LastMonth();

    }

}
