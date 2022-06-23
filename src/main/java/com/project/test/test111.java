package com.project.test;

import com.project.business.zj_Report_Kd_Wg_Business;
import com.project.business.zj_Report_Zss_Business;
import com.project.controller.*;
import com.project.util.dealTime;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test111 {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException, MessagingException {

        zj_Report_Znzw_Controller zj_Report_Znzw_Controller=new zj_Report_Znzw_Controller();
        zj_Report_Znzw_Controller.report_Znzw_Zj();

    }


}
