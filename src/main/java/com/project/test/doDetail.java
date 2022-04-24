package com.project.test;

import com.project.business.zj_Report_Zss_Business;
import com.project.controller.*;
import com.project.util.dealTime;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class doDetail {

        public static void doDetailTest() throws Exception {

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
            if (Integer.valueOf(nowDay)>=5){
                zj_Report_Xubao_Controller.report_Xubao_Zj();
                zj_Report_Tcf_Controller.report_Tcf_Zj();
                zj_Report_Zt_Controller.report_Zt_Zj();
                zj_Report_Wyj_Controller.report_Wyj_Zj();
                zj_Report_RhItv_Controller.report_RhItv_Zj();
                zj_Report_Kd_Controller.report_Kd_Zj();
                zj_Report_Kd_Wg_Controller.report_Kd_Zj_Gis();

            }
            if (Integer.valueOf(nowDay)==6){
                zj_Report_Xubao_Controller.report_Xubao_Zj_Js();
                zj_Report_Tcf_Controller.report_Tcf_Zj_Js();
                zj_Report_Wyj_Controller.report_Wyj_Zj_Js();
                zj_Report_RhItv_Controller.report_RhItv_Js();
                zj_Report_Zt_Controller.report_Zt_Zj_Js();
                zj_Report_Xubao_Controller.report_Xubao_Zj_Js_DoData();
                zj_Report_Tcf_Controller.report_Tcf_Zj_Js_DoData();
                zj_Report_Wyj_Controller.report_Wyj_Zj_Js_DoData();
                zj_Report_RhItv_Controller.report_RhItv_Zj_Js_DoData();
                zj_Report_Zt_Controller.report_Zt_Zj_Js_DoData();
            }

            if (nowDay.equals("07")||nowDay.equals("11")||nowDay.equals("15")||nowDay.equals("19")||nowDay.equals("23")||nowDay.equals("26")||nowDay.equals("28")||nowDay.equals("30")){
                zj_Report_Zt_Controller.report_Zt_Zj_DoData();
                zj_Report_Xubao_Controller.report_Xubao_Zj_DoData();
                zj_Report_Tcf_Controller.report_Tcf_Zj_DoData();
                zj_Report_Wyj_Controller.report_Wyj_Zj_DoData();
                zj_Report_RhItv_Controller.report_RhItv_Zj_DoData();
                zj_Report_Kd_Controller.report_Kd_Zj_DoData();
            }

        }

    }
