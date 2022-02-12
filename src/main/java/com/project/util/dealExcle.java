package com.project.util;

import com.project.model.zj_Report_Xubao_Zj;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class dealExcle {


    //复制值toExcle
    public void cpoyToExcle(List list, String inFileName, String outFileName, int dex,Object object) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        //获取行个数
        int maxRow = list.size();

        //获取列个数
        Field[] field=object.getClass().getDeclaredFields();
        int maxCell=field.length;
        if(inFileName == null ){
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

            XSSFSheet sheet = xssfWorkbook.createSheet();
            //循环复制值
            copyData(sheet,maxCell,maxRow,list);

            // 刷新公式
            xssfWorkbook.setForceFormulaRecalculation(true);
            //使用evaluateFormulaCell对函数单元格进行强行更新计算
            xssfWorkbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

            //导出
            FileOutputStream fos=new FileOutputStream(outFileName);

            xssfWorkbook.write(fos);
            fos.close();

        }
        if(inFileName != null){
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(inFileName));

            XSSFSheet sheet = xssfWorkbook.getSheetAt(dex);
            //循环复制值
            copyData(sheet,maxCell,maxRow,list);

            // 刷新公式
            xssfWorkbook.setForceFormulaRecalculation(true);
            //使用evaluateFormulaCell对函数单元格进行强行更新计算
            xssfWorkbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

            //导出
            FileOutputStream fos=new FileOutputStream(outFileName);

            xssfWorkbook.write(fos);
            fos.close();
        }

    }


    //复制值
    public void copyData(XSSFSheet sheet,int maxCell,int maxRow,List list ) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        XSSFRow rowCreatFirst=sheet.createRow(0);
        //获取studentList具体类，并且
        Object objectListFirst=list.get(0);
        Field[] fieldDeatilFirst =objectListFirst.getClass().getDeclaredFields();

        //操作列
        for (int rol = 0; rol <maxCell; rol++){
            XSSFCell cellCreat=rowCreatFirst.createCell(rol);
            //反射列，将类的值置入列中
            Field fDetail = fieldDeatilFirst[rol];
            // 获取属性的名字
            String name = fDetail.getName();
            cellCreat.setCellValue(name);
        }

        for (int row = 1; row < maxRow+1; row++) {
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            /* int maxRol = sheet.getRow(row).getLastCellNum();*/
            XSSFRow rowCreat=sheet.createRow(row);
            //获取studentList具体类，并且
            Object objectList=list.get(row-1);
            Field[] fieldDeatil =objectList.getClass().getDeclaredFields();

            //操作列
            for (int rol = 0; rol <maxCell; rol++){
                XSSFCell cellCreat=rowCreat.createCell(rol);
                //反射列，将类的值置入列中
                Field fDetail = fieldDeatil[rol];
                // 获取属性的名字
                String name = fDetail.getName();
                // 将属性的首字符大写，方便构造get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 获取属性的类型
                String type = fDetail.getGenericType().toString();
                Method m = objectList.getClass().getMethod("get" + name);
                if (type.equals("class java.lang.String")) {
                    // 调用getter方法获取属性值
                    String value = (String) m.invoke(objectList);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Integer")) {
                    Integer value = (Integer) m.invoke(objectList);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Short")) {
                    Short value = (Short) m.invoke(objectList);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Double")) {
                    Double value = (Double) m.invoke(objectList);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Boolean")) {
                    Boolean value = (Boolean) m.invoke(objectList);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.util.Date")) {
                    Date value = (Date) m.invoke(objectList);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }

            }
        }


    }

    //复制值toExcleSingle
    public void cpoyToExcleSingle(String maxTime, String inFileName, String outFileName, int dex) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(inFileName));
        XSSFSheet sheet = xssfWorkbook.getSheetAt(dex);
        //循环复制值
        //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
        /* int maxRol = sheet.getRow(row).getLastCellNum();*/
        XSSFRow rowCreat=sheet.createRow(0);
        XSSFCell cellCreat=rowCreat.createCell(0);
        // 调用getter方法获取属性值
        if (maxTime != null) {
            cellCreat.setCellValue(maxTime);
        }

        // 刷新公式
        xssfWorkbook.setForceFormulaRecalculation(true);
        //使用evaluateFormulaCell对函数单元格进行强行更新计算
        xssfWorkbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

        //导出
        FileOutputStream fos=new FileOutputStream(outFileName);

        xssfWorkbook.write(fos);
        fos.close();

    }

    //复制值toExcle
    public void excleToPng( String inFileName, String outFileName)  {
    //加载Excel工作表
        Workbook wb = new Workbook();
        wb.loadFromFile(inFileName);

        //获取工作表
        Worksheet sheet = wb.getWorksheets().get(0);

        //调用方法将Excel工作表保存为图片
        sheet.saveToImage(outFileName);
        //调用方法，将指定Excel单元格数据范围保存为图片
        //sheet.saveToImage("ToImg2.png",8,1,30,7);
    }


    //复制EXCLE给另一个EXCLE
    public void copyExcleToOtherExcle( String inFileName, String outFileName) throws IOException {
        //加载Excel工作表
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(inFileName));

        //导出
        FileOutputStream fos=new FileOutputStream(outFileName);

        xssfWorkbook.write(fos);
        fos.close();

    }


}
