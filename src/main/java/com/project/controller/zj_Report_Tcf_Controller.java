package com.project.controller;

import com.project.business.zj_Report_Tcf_Business;
import com.project.business.zj_Report_Xubao_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class zj_Report_Tcf_Controller {


//    public static void main(String[] args) throws Exception {
//
//        zj_Report_Tcf_Business zj_Report_Tcf_Business=new zj_Report_Tcf_Business();
//        zj_Report_Tcf_Business.report_Tcf_Zj_All();
//
//
//    }

    public static void report_Tcf_Zj_Js() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Tcf_Business zj_Report_Tcf_Business=new zj_Report_Tcf_Business();
        zj_Report_Tcf_Business.report_Tcf_Zj_Js();

    }

    public static void report_Tcf_Zj() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Tcf_Business zj_Report_Tcf_Business=new zj_Report_Tcf_Business();
        zj_Report_Tcf_Business.report_Tcf_Zj();

    }

    public static void report_Tcf_Zj_DoData() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Tcf_Business zj_Report_Tcf_Business=new zj_Report_Tcf_Business();
        zj_Report_Tcf_Business.report_Tcf_Zj_DoData_NowMonth();

    }

    public static void report_Tcf_Zj_Js_DoData() throws MessagingException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Tcf_Business zj_Report_Tcf_Business=new zj_Report_Tcf_Business();
        zj_Report_Tcf_Business.report_Xubao_Zj_DoData_LastMonth();

    }

    }
