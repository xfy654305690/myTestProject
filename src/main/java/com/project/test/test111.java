package com.project.test;

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

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {

        dealTime dealTime=new dealTime();
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(dealTime.get_nowMonth_FirstDay_ByDate());
        String dateString ="202-02-16";

        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date date =  simpleDateFormatYMD.parse(dateString);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(date);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        int day=day2 - day1;
        System.out.println(day);


    }


}
