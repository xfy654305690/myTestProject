package com.project.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dealTime {

    //获取下月一号，返回日期格式
    public Date get_NextMonth_FirstDay_ByDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        Date endDate=calendar.getTime();// 获取下月一号

        return endDate;
    }


    //获取当前日期YYYYMM格式
    public String get_date_By_String_YYYYMM(){

        SimpleDateFormat simpleDateFormatYM = new SimpleDateFormat("yyyyMM");//注意月份是MM
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYM.format(nowMonthDate);

        return nowMonth;
    }

    //获取当前日期YYYYMM格式
    public String get_date_By_String_YYYYMMDD(){

        SimpleDateFormat simpleDateFormatYM = new SimpleDateFormat("yyyyMMdd");//注意月份是MM
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYM.format(nowMonthDate);

        return nowMonth;
    }


    //获取当前日期YYYYMMDD格式
    public String get_date_By_String_YYYY_MM_DD(){

        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYMD.format(nowMonthDate);

        return nowMonth;
    }


    //获取当前日期DD格式
    public String get_date_By_String_DD(){

        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("dd");
        Date nowMonthDate = new Date();// 获取当前时间
        String nowMonth=simpleDateFormatYMD.format(nowMonthDate);

        return nowMonth;
    }


}
