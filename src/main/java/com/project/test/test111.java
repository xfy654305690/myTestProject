package com.project.test;

import com.project.business.zj_Report_Kd_Wg_Business;
import com.project.business.zj_Report_Zss_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class test111 {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException, MessagingException {
        zj_Report_Zss_Business zj_Report_Zss_Business=new zj_Report_Zss_Business();
        zj_Report_Zss_Business.report_Zss_Zj();


    }


}
