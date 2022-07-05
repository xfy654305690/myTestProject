package com.project.controller;


import com.project.business.zj_Report_Kj_Business;
import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Kj_Controller {

    public static void report_Kj_Zj() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, IOException, ParseException, InvocationTargetException {
        zj_Report_Kj_Business zj_Report_Kj_Business=new zj_Report_Kj_Business();
        zj_Report_Kj_Business.report_Kj_Zj();

    }

    public static void report_Kj_Zj_Js() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, IOException, ParseException, InvocationTargetException {
        zj_Report_Kj_Business zj_Report_Kj_Business=new zj_Report_Kj_Business();
        zj_Report_Kj_Business.report_Kj_Zj_Js();

    }

}
