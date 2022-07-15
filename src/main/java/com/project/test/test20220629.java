package com.project.test;

import com.project.business.zj_Report_Kd_Business;
import com.project.business.zj_Report_Kd_Qz_Business;
import com.project.business.zj_Report_Zcy_Zss_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Scanner;

public class test20220629 {
    public static void main(String[] args) throws Exception {
        zj_Report_Zcy_Zss_Business zj_Report_Zcy_Zss_Business=new zj_Report_Zcy_Zss_Business();
        zj_Report_Zcy_Zss_Business.selectZj_Report_Zcy_Zss();

    }

}
