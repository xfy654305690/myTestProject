package com.project.controller;

import com.project.business.zj_Report_Kd_Wg_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Kd_Wg_Controller {

    public static void report_Kd_Zj_Gis() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        zj_Report_Kd_Wg_Business zj_Report_Kd_Wg_Business=new zj_Report_Kd_Wg_Business();
        zj_Report_Kd_Wg_Business.report_Kd_Zj_Gis();

    }

}
