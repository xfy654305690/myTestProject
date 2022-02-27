package com.project.test;

import com.project.util.dealTime;
import com.spire.xls.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class testExcleToPng {

    public static void main(String[] args) throws IOException {
        dealTime dealTime=new dealTime();
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date nowMonthDate =dealTime.getLastQuarterFirstDay();// 获取当前时间
        String nowMonth=simpleDateFormatYMD.format(nowMonthDate);
        System.out.println(nowMonth);

        }
    }
