package com.project.test;

import com.project.controller.*;
import com.project.model.zj_Report_Xubao_Zj;
import com.project.util.dealTime;
import com.project.view.zj_Report_XubaoDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.mail.MessagingException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class test111 {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException, MessagingException {

        zj_Report_Kd_Controller zj_Report_Kd_Controller=new zj_Report_Kd_Controller();
        zj_Report_RhItv_Controller zj_Report_RhItv_Controller=new zj_Report_RhItv_Controller();
        zj_Report_Tcf_Controller zj_Report_Tcf_Controller=new zj_Report_Tcf_Controller();
        zj_Report_Wyj_Controller zj_Report_Wyj_Controller=new zj_Report_Wyj_Controller();
        zj_Report_Xubao_Controller zj_Report_Xubao_Controller=new zj_Report_Xubao_Controller();
        zj_Report_Zt_Controller zj_Report_Zt_Controller=new zj_Report_Zt_Controller();
        zj_Report_OtherDone_Controller zj_Report_OtherDone_Controller=new zj_Report_OtherDone_Controller();

        //获取当前日期DD格式
        String nowDay= dealTime.get_date_By_String_DD();
        if (nowDay.equals("07")||nowDay.equals("12")||nowDay.equals("15")||nowDay.equals("19")||nowDay.equals("23")||nowDay.equals("26")||nowDay.equals("28")||nowDay.equals("30")){
            zj_Report_Xubao_Controller.report_Xubao_Zj_DoData();
            zj_Report_Tcf_Controller.report_Tcf_Zj_DoData();
            zj_Report_Zt_Controller.report_Zt_Zj_DoData();
            zj_Report_Wyj_Controller.report_Wyj_Zj_DoData();
            zj_Report_RhItv_Controller.report_RhItv_Zj_DoData();
            zj_Report_Kd_Controller.report_Kd_Zj();
        }

    }


}
