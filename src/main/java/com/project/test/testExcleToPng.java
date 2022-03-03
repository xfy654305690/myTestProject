package com.project.test;

import com.project.util.dealTime;
import com.spire.xls.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class testExcleToPng {

    public static void main(String[] args) throws IOException {


        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("C:\\Test\\test.xlsx"));

        xssfWorkbook.createSheet("12321");
        //导出
        FileOutputStream fos=new FileOutputStream("C:\\Test\\test.xlsx");

        xssfWorkbook.write(fos);
        fos.close();

    }
}
