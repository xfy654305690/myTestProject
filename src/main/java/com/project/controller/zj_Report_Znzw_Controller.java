package com.project.controller;


import com.project.business.zj_Report_Znzw_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Znzw_Controller {

    public static void report_Znzw_Zj() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, IOException, ParseException, InvocationTargetException {
        zj_Report_Znzw_Business zj_Report_Znzw_Business=new zj_Report_Znzw_Business();
        zj_Report_Znzw_Business.report_Znzw_Zj();

    }

}
