package com.project.test;

import com.project.business.zj_Report_Zss_Business;
import com.project.controller.*;
import com.project.util.dealTime;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            zj_Report_Kd_Qz_Controller zj_Report_Kd_Qz_Controller=new zj_Report_Kd_Qz_Controller();
            zj_Report_Kd_Qz_Gis_Controller zj_Report_Kd_Qz_Gis_Controller=new zj_Report_Kd_Qz_Gis_Controller();
            zj_Report_Znzw_Controller zj_Report_Znzw_Controller=new zj_Report_Znzw_Controller();

            //获取当前日期DD格式
            String nowDay= dealTime.get_date_By_String_DD();
            SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("MM");
            Date nowMonthDate = new Date();// 获取当前时间
            String nowMonth=simpleDateFormatYMD.format(nowMonthDate);

            if (Integer.valueOf(nowDay)>=5){
                zj_Report_Tcf_Controller.report_Tcf_Zj();
                zj_Report_Xubao_Controller.report_Xubao_Zj();
                zj_Report_Zt_Controller.report_Zt_Zj();
                zj_Report_Wyj_Controller.report_Wyj_Zj();
                zj_Report_RhItv_Controller.report_RhItv_Zj();
                zj_Report_Kd_Controller.report_Kd_Zj();
                zj_Report_Kd_Wg_Controller.report_Kd_Zj_Gis();
                zj_Report_Kd_Qz_Controller.report_Kd_Qz_Zj();
                zj_Report_Kd_Qz_Gis_Controller.report_Kd_Qz_Gis_Zj();
                zj_Report_Kd_Qz_Controller.report_Kd_Qz_Zj_630();
                zj_Report_Znzw_Controller.report_Znzw_Zj();

            }
            if (Integer.valueOf(nowDay)==6){
                zj_Report_Xubao_Controller.report_Xubao_Zj_Js();
                zj_Report_Tcf_Controller.report_Tcf_Zj_Js();
                zj_Report_Zt_Controller.report_Zt_Zj_Js();
                zj_Report_Xubao_Controller.report_Xubao_Zj_Js_DoData();
                zj_Report_Tcf_Controller.report_Tcf_Zj_Js_DoData();
                zj_Report_Zt_Controller.report_Zt_Zj_Js_DoData();

                if(Integer.valueOf(nowMonth)==1||Integer.valueOf(nowMonth)==4||Integer.valueOf(nowMonth)==7||Integer.valueOf(nowMonth)==10){
                    zj_Report_Wyj_Controller.report_Wyj_Zj_Js();
                    zj_Report_RhItv_Controller.report_RhItv_Js();
                    zj_Report_Kd_Controller.report_Kd_Zj_Js();
                    zj_Report_Wyj_Controller.report_Wyj_Zj_Js_DoData();
                    zj_Report_RhItv_Controller.report_RhItv_Zj_Js_DoData();
                    zj_Report_Kd_Controller.report_Kd_Zj_DoData();
                }else{
                    zj_Report_Kd_Controller.report_Kd_Zj_By_Month_Js();
                }
            }

            if (nowDay.equals("07")||nowDay.equals("11")||nowDay.equals("15")||nowDay.equals("19")||nowDay.equals("21")||nowDay.equals("23")||nowDay.equals("25")||nowDay.equals("27")||nowDay.equals("29")||nowDay.equals("31")){
                zj_Report_Zt_Controller.report_Zt_Zj_DoData();
                zj_Report_Xubao_Controller.report_Xubao_Zj_DoData();
                zj_Report_Tcf_Controller.report_Tcf_Zj_DoData();
                zj_Report_Wyj_Controller.report_Wyj_Zj_DoData();
                zj_Report_RhItv_Controller.report_RhItv_Zj_DoData();
                zj_Report_Kd_Controller.report_Kd_Zj_DoData();
            }

        }

    }
