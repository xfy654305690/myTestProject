package com.project.controller;

import com.project.business.zj_Report_Kd_Business;
import com.project.business.zj_Report_Zcy_Zss_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Zcy_Zss_Controller {

    public static void Zj_Report_Zcy_Zss() throws Exception {
        zj_Report_Zcy_Zss_Business zj_Report_Zcy_Zss_Business=new zj_Report_Zcy_Zss_Business();
        zj_Report_Zcy_Zss_Business.selectZj_Report_Zcy_Zss();

    }

}
