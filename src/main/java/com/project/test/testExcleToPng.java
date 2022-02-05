package com.project.test;

import com.spire.xls.*;

import java.io.IOException;


public class testExcleToPng {

    public static void main(String[] args) throws IOException {

        //加载Excel工作表
        Workbook wb = new Workbook();
        wb.loadFromFile("D:\\test\\test.xlsx");

        //获取工作表
        Worksheet sheet = wb.getWorksheets().get(0);

        //调用方法将Excel工作表保存为图片
        sheet.saveToImage("D:\\test\\ToImg.png");
        //调用方法，将指定Excel单元格数据范围保存为图片
        //sheet.saveToImage("ToImg2.png",8,1,30,7);

        }
    }
