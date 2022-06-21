package com.project.test;

import com.project.business.zj_Report_Kd_Qz_Business;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Scanner;

public class test20220617 {

    public static void main(String[] args) throws MessagingException, IOException, ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        zj_Report_Kd_Qz_Business zj_Report_Kd_Qz_Business=new zj_Report_Kd_Qz_Business();
        zj_Report_Kd_Qz_Business.report_Kd_Qz_Zj_dangyue();


    }

}
