package com.project.test;

import com.project.controller.*;
import com.project.util.dealTime;

public class doDetail {

        public static void main(String[] args) throws Exception {

            zj_Report_Kd_Controller zj_Report_Kd_Controller=new zj_Report_Kd_Controller();
            zj_Report_RhItv_Controller zj_Report_RhItv_Controller=new zj_Report_RhItv_Controller();
            zj_Report_Tcf_Controller zj_Report_Tcf_Controller=new zj_Report_Tcf_Controller();
            zj_Report_Wyj_Controller zj_Report_Wyj_Controller=new zj_Report_Wyj_Controller();
            zj_Report_Xubao_Controller zj_Report_Xubao_Controller=new zj_Report_Xubao_Controller();
            zj_Report_Zt_Controller zj_Report_Zt_Controller=new zj_Report_Zt_Controller();
            zj_Report_OtherDone_Controller zj_Report_OtherDone_Controller=new zj_Report_OtherDone_Controller();

            //增加定时功能



            //获取当前日期DD格式
            String nowDay= dealTime.get_date_By_String_DD();
            if (Integer.valueOf(nowDay)>=5){
                zj_Report_Xubao_Controller.report_Xubao_Zj();
                zj_Report_Tcf_Controller.report_Tcf_Zj();
                zj_Report_Zt_Controller.report_Zt_Zj();
                zj_Report_Wyj_Controller.report_Wyj_Zj();
                zj_Report_RhItv_Controller.report_RhItv_Zj();
                zj_Report_Kd_Controller.report_Kd_Zj();
                zj_Report_OtherDone_Controller.report_OtherDone_Zj();
            }
            if (Integer.valueOf(nowDay)==6){
                zj_Report_Xubao_Controller.report_Xubao_Zj_Js();
                zj_Report_Tcf_Controller.report_Tcf_Zj_Js();
                zj_Report_Wyj_Controller.report_Wyj_Zj_Js();
                zj_Report_RhItv_Controller.report_RhItv_Zj();
            }
            if (Integer.valueOf(nowDay)==8){
                zj_Report_Kd_Controller.report_Kd_Zj();
                zj_Report_Zt_Controller.report_Zt_Zj();
            }

//            //后续把发送数据也搞过来这边方法拿出来，判断日期，如果日期到了，就发送，不到就不发送。


        }

}
