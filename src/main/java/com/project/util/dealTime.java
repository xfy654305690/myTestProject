package com.project.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dealTime {


    //获取下月一号，返回日期格式
    public static Date get_NextMonth_FirstDay_ByDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        Date endDate=calendar.getTime();// 获取下月一号

        return endDate;
    }

    //获取前月一号，返回日期格式
    public static Date get_lastMonth_FirstDay_ByDate(){

        Calendar   calendar=Calendar.getInstance();//获取当前日期
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        Date endDate=calendar.getTime();

        return endDate;
    }

    //获取前月最后一号，返回日期格式
    public static Date get_lastMonth_LastDay_ByDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        Date endDate=calendar.getTime();

        return endDate;
    }


    //获取当季度一号，返回日期格式
    public static Date get_nowQuarter_FirstDay_ByDate(){

        Calendar calendar = Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH)+1;
        if(month>=1&&month<=3){
            calendar.set(Calendar.MONTH,0);
            calendar.set(Calendar.DATE,1);
        }
        if(month>=4&&month<=6){
            calendar.set(Calendar.MONTH,3);
            calendar.set(Calendar.DATE,1);
        }
        if(month>=7&&month<=9){
            calendar.set(Calendar.MONTH,6);
            calendar.set(Calendar.DATE,1);
        }
        if(month>=9&&month<=12){
            calendar.set(Calendar.MONTH,9);
            calendar.set(Calendar.DATE,1);
        }
        Date endDate=calendar.getTime();
        return endDate;
    }

    //获取当前季度最后一号，返回日期格式
    public static Date get_nowQuarter_LastDay_ByDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(get_nowQuarter_FirstDay_ByDate());
        calendar.add(Calendar.MONTH,2);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    //获取当季度一号，返回日期格式
    public static String get_lastQuarter_LastDay_ByDate_YYYYMM(){
        Calendar calendar = Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH)+1;
        if(month>=1&&month<=3){
            calendar.set(Calendar.MONTH,0);
            calendar.set(Calendar.DATE,1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        if(month>=4&&month<=6){
            calendar.set(Calendar.MONTH,3);
            calendar.set(Calendar.DATE,1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        if(month>=7&&month<=9){
            calendar.set(Calendar.MONTH,6);
            calendar.set(Calendar.DATE,1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        if(month>=9&&month<=12){
            calendar.set(Calendar.MONTH,9);
            calendar.set(Calendar.DATE,1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        Date endDate=calendar.getTime();
        SimpleDateFormat simpleDateFormatYM = new SimpleDateFormat("yyyyMM");//注意月份是MM
        String dateMonth=simpleDateFormatYM.format(endDate);

        return dateMonth;
    }


    //获取当月一号，返回日期格式
    public static Date get_nowMonth_FirstDay_ByDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate=calendar.getTime();

        return endDate;
    }

    //获取当月最后一号，返回日期格式
    public static Date get_nowMonth_LastDay_ByDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate=calendar.getTime();

        return endDate;
    }

    //获取当前日期YYYYMM格式
    public static String get_date_By_String_YYYYMM(){

        SimpleDateFormat simpleDateFormatYM = new SimpleDateFormat("yyyyMM");//注意月份是MM
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYM.format(nowMonthDate);

        return nowMonth;
    }

    //获取当前日期YYYYMM格式
    public static String get_date_By_String_YYYYMMDD(){

        SimpleDateFormat simpleDateFormatYM = new SimpleDateFormat("yyyyMMdd");//注意月份是MM
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYM.format(nowMonthDate);

        return nowMonth;
    }


    //获取当前日期YYYYMMDD格式
    public static String get_date_By_String_YYYY_MM_DD(){

        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYMD.format(nowMonthDate);

        return nowMonth;
    }


    //获取当前日期DD格式
    public static String get_date_By_String_DD(){

        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("dd");
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYMD.format(nowMonthDate);

        return nowMonth;
    }

    //获取当前日期差额
    public static Integer get_date_Difference_Values(Date startDate, Date endDate){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(startDate);
        calendar2.setTime(endDate);
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int day=day2 - day1;
        return day;
    }


}
