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

          zj_Report_Kd_Controller zj_Report_Kd_Controller=new zj_Report_Kd_Controller();
//        zj_Report_RhItv_Controller zj_Report_RhItv_Controller=new zj_Report_RhItv_Controller();
//        zj_Report_Tcf_Controller zj_Report_Tcf_Controller=new zj_Report_Tcf_Controller();
//        zj_Report_Wyj_Controller zj_Report_Wyj_Controller=new zj_Report_Wyj_Controller();
//        zj_Report_Xubao_Controller zj_Report_Xubao_Controller=new zj_Report_Xubao_Controller();
//        zj_Report_Zt_Controller zj_Report_Zt_Controller=new zj_Report_Zt_Controller();
//        zj_Report_Kd_Wg_Controller zj_Report_Kd_Wg_Controller=new zj_Report_Kd_Wg_Controller();
//
//        zj_Report_Zss_Business zj_Report_Zss_Business=new zj_Report_Zss_Business();

        //zj_Report_Zss_Business.report_Zss_Zj();

//        zj_Report_Xubao_Controller.report_Xubao_Zj();
//        zj_Report_Tcf_Controller.report_Tcf_Zj();
//        zj_Report_Zt_Controller.report_Zt_Zj();
//        zj_Report_Wyj_Controller.report_Wyj_Zj();
//        zj_Report_RhItv_Controller.report_RhItv_Zj();
        //zj_Report_Kd_Controller.report_Kd_Zj();
//        zj_Report_Kd_Wg_Controller.report_Kd_Zj_Gis();
        zj_Report_Kd_Controller.report_Kd_Zj_Js();

    }


}
