package com.project.controller;

import com.project.business.zj_Report_Kd_Qz_Business;
import com.project.business.zj_Report_Kd_Qz_Gis_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class zj_Report_Kd_Qz_Gis_Controller {


    public static void report_Kd_Qz_Gis_Zj() throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        zj_Report_Kd_Qz_Gis_Business zj_Report_Kd_Qz_Gis_Business=new zj_Report_Kd_Qz_Gis_Business();
        zj_Report_Kd_Qz_Gis_Business.report_Kd_Qz_Gis_Zj();

    }

}
