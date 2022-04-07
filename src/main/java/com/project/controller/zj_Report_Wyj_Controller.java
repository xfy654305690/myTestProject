package com.project.controller;


import com.project.business.zj_Report_Wyj_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class zj_Report_Wyj_Controller {



//    public static void main(String[] args) throws Exception {
//
//        zj_Report_Wyj_Business Zj_Report_Wyj_Business =new zj_Report_Wyj_Business();
//        Zj_Report_Wyj_Business.report_Wyj_Zj_All();
//
//    }

    public static void report_Wyj_Zj_Js() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Wyj_Business Zj_Report_Wyj_Business =new zj_Report_Wyj_Business();
        Zj_Report_Wyj_Business.report_Wyj_Zj_Js();
    }

    public static void report_Wyj_Zj() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Wyj_Business Zj_Report_Wyj_Business =new zj_Report_Wyj_Business();
        Zj_Report_Wyj_Business.report_Wyj_Zj();
    }

    public static void report_Wyj_Zj_DoData() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Wyj_Business Zj_Report_Wyj_Business =new zj_Report_Wyj_Business();
        Zj_Report_Wyj_Business.report_Wyj_Zj_DoData_NowMonth();
    }

    public static void report_Wyj_Zj_Js_DoData() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Wyj_Business Zj_Report_Wyj_Business =new zj_Report_Wyj_Business();
        Zj_Report_Wyj_Business.report_Wyj_Zj_DoData_LastMonth();
    }


}
