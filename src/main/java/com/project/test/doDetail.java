package com.project.test;

import com.project.controller.*;
import com.project.util.dealTime;

public class doDetail {

        public static void doDetailTest() throws Exception {

            zj_Report_Kd_Controller zj_Report_Kd_Controller=new zj_Report_Kd_Controller();
            zj_Report_RhItv_Controller zj_Report_RhItv_Controller=new zj_Report_RhItv_Controller();
            zj_Report_Tcf_Controller zj_Report_Tcf_Controller=new zj_Report_Tcf_Controller();
            zj_Report_Wyj_Controller zj_Report_Wyj_Controller=new zj_Report_Wyj_Controller();
            zj_Report_Xubao_Controller zj_Report_Xubao_Controller=new zj_Report_Xubao_Controller();
            zj_Report_Zt_Controller zj_Report_Zt_Controller=new zj_Report_Zt_Controller();
            zj_Report_OtherDone_Controller zj_Report_OtherDone_Controller=new zj_Report_OtherDone_Controller();

            //获取当前日期DD格式
            String nowDay= dealTime.get_date_By_String_DD();
            System.out.printf("nowDay");
            if (Integer.valueOf(nowDay)>=5){
                System.out.printf("IN");
                zj_Report_Xubao_Controller.report_Xubao_Zj();
                System.out.printf("XB_COMPLETE");
                zj_Report_Tcf_Controller.report_Tcf_Zj();
                System.out.printf("TCF_COMPLETE");
                zj_Report_Zt_Controller.report_Zt_Zj();
                System.out.printf("ZT_COMPLETE");
                zj_Report_Wyj_Controller.report_Wyj_Zj();
                System.out.printf("WYJ_COMPLETE");
                zj_Report_RhItv_Controller.report_RhItv_Zj();
                System.out.printf("RHITV_COMPLETE");
                zj_Report_Kd_Controller.report_Kd_Zj();
                System.out.printf("KD_COMPLETE");
                //zj_Report_OtherDone_Controller.report_OtherDone_Zj();
                System.out.printf("OtherDone_COMPLETE");
            }
//            if (Integer.valueOf(nowDay)==6){
//                zj_Report_Xubao_Controller.report_Xubao_Zj_Js();
//                zj_Report_Tcf_Controller.report_Tcf_Zj_Js();
//                zj_Report_Wyj_Controller.report_Wyj_Zj_Js();
//                zj_Report_RhItv_Controller.report_RhItv_Zj();
//            }
//            if (Integer.valueOf(nowDay)==7){
//                zj_Report_Kd_Controller.report_Kd_Zj_Js();
//                zj_Report_Zt_Controller.report_Zt_Zj_Js();
//            }

//            //后续把发送数据也搞过来这边方法拿出来，判断日期，如果日期到了，就发送，不到就不发送。


        }

}
