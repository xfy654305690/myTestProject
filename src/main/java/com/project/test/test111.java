package com.project.test;

import com.project.business.zj_Report_Kd_Wg_Business;
import com.project.business.zj_Report_Zss_Business;
import com.project.controller.*;
import com.project.util.dealTime;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class test111 {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException, MessagingException {
        zj_Report_Kd_Controller zj_Report_Kd_Controller=new zj_Report_Kd_Controller();
        zj_Report_RhItv_Controller zj_Report_RhItv_Controller=new zj_Report_RhItv_Controller();
        zj_Report_Tcf_Controller zj_Report_Tcf_Controller=new zj_Report_Tcf_Controller();
        zj_Report_Wyj_Controller zj_Report_Wyj_Controller=new zj_Report_Wyj_Controller();
        zj_Report_Xubao_Controller zj_Report_Xubao_Controller=new zj_Report_Xubao_Controller();
        zj_Report_Zt_Controller zj_Report_Zt_Controller=new zj_Report_Zt_Controller();
        zj_Report_Kd_Wg_Controller zj_Report_Kd_Wg_Controller=new zj_Report_Kd_Wg_Controller();

        zj_Report_OtherDone_Controller zj_Report_OtherDone_Controller=new zj_Report_OtherDone_Controller();

        //获取当前日期DD格式
        String nowDay= dealTime.get_date_By_String_DD();



            zj_Report_RhItv_Controller.report_RhItv_Zj_DoData();
            zj_Report_Kd_Controller.report_Kd_Zj_DoData();


    }


}
