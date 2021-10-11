package com.project.test;

import com.project.model.student;
import com.project.model.xfy_zss_modelCp;
import com.project.model.xfy_zss_modelXsp;
import com.project.view.xfy_zss_modelCpDao;
import com.project.view.xfy_zss_modelXspDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class test0 {

//    public void xfy_zss_modelDetail() throws Exception {
//        this.excleDoDetail();
//        this.emailDoDetail();
//
//    }


    //取数导出excle
    public  static void main(String[] args) throws IOException, ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String config="mybatis.xml";
        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM

        //产品
        xfy_zss_modelCpDao xfy_zss_modelCpDao=sqlSession.getMapper(xfy_zss_modelCpDao.class);
        List<xfy_zss_modelCp> xfy_zss_modelCpDaoList=xfy_zss_modelCpDao.selectXfy_zss_modelCp(simpleDateFormat.parse("2021-10-7"),simpleDateFormat.parse(simpleDateFormat.format(new Date())));

        System.out.println(xfy_zss_modelCpDaoList.size());
        //销售品
        xfy_zss_modelXspDao xfy_zss_modelXspDao=sqlSession.getMapper(xfy_zss_modelXspDao.class);
        List<xfy_zss_modelXsp> xfy_zss_modelXspDaoList=xfy_zss_modelXspDao.selectXfy_zss_modelXsp(simpleDateFormat.parse("2021-10-7"),simpleDateFormat.parse(simpleDateFormat.format(new Date())));

        System.out.println(xfy_zss_modelXspDaoList.size());

        in.close();
        sqlSession.close();

        System.out.println("数据成功取出");

        XSSFWorkbook xssf = new XSSFWorkbook(new FileInputStream("E:\\Test\\zss.xlsx"));
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook(xssf, 100);

        System.out.println("读取EXCLE成功");
        //第一个SHEET


        //获取行个数
        int maxRow = xfy_zss_modelCpDaoList.size();
        //获取列个数
        xfy_zss_modelCp xfy_zss_modelCp=new xfy_zss_modelCp();
        Field[] field=xfy_zss_modelCp.getClass().getDeclaredFields();
        int maxCell=field.length;

        XSSFSheet sheet = xssfWorkbook.getXSSFWorkbook().getSheetAt(0);

        for (int row =1; row <=maxRow; row++) {
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            /* int maxRol = sheet.getRow(row).getLastCellNum();*/
            XSSFRow rowCreat=sheet.createRow(row);
            //获取studentList具体类，并且
            xfy_zss_modelCp xfy_zss_modelCpDetail=xfy_zss_modelCpDaoList.get(row-1);
            Field[] fieldDeatil =xfy_zss_modelCpDetail.getClass().getDeclaredFields();

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
                Method m = xfy_zss_modelCpDetail.getClass().getMethod("get" + name);
                if (fDetail.getGenericType().toString().equals("class java.lang.String")) {
                    // 调用getter方法获取属性值
                    if ( m.invoke(xfy_zss_modelCpDetail)!= null) {
                        cellCreat.setCellValue((String) m.invoke(xfy_zss_modelCpDetail));
                    }
                }

            }
        }

        System.out.println("第一个SHEET成功");




        //第二个SHEET

        //获取行个数
        int maxRowXsp = xfy_zss_modelXspDaoList.size();
        //获取列个数
        xfy_zss_modelXsp xfy_zss_modelXSp=new xfy_zss_modelXsp();
        Field[] fieldxSP=xfy_zss_modelXSp.getClass().getDeclaredFields();
        int maxCellXsp=fieldxSP.length;

        XSSFSheet sheetXsp = xssfWorkbook.getXSSFWorkbook().getSheetAt(1);

        for (int row = 1; row <= maxRowXsp; row++) {
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            /* int maxRol = sheet.getRow(row).getLastCellNum();*/
            XSSFRow rowCreatXsp=sheetXsp.createRow(row);
            //获取studentList具体类，并且
            xfy_zss_modelXsp xfy_zss_modelXspDetail=xfy_zss_modelXspDaoList.get(row-1);
            Field[] fieldDeatil =xfy_zss_modelXspDetail.getClass().getDeclaredFields();

            //操作列
            for (int rol = 0; rol <maxCellXsp; rol++){
                XSSFCell cellCreat=rowCreatXsp.createCell(rol);
                //反射列，将类的值置入列中
                Field fDetailXsp = fieldDeatil[rol];
                // 获取属性的名字
                String name = fDetailXsp.getName();
                // 将属性的首字符大写，方便构造get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 获取属性的类型
                Method m = xfy_zss_modelXspDetail.getClass().getMethod("get" + name);
                if (fDetailXsp.getGenericType().toString().equals("class java.lang.String")) {
                    // 调用getter方法获取属性值
                    if (m.invoke(xfy_zss_modelXspDetail) != null) {
                        cellCreat.setCellValue((String)m.invoke(xfy_zss_modelXspDetail));
                    }
                }
            }
        }

        System.out.println("第二个SHEET成功");

        //第三个SHEET
        SimpleDateFormat simpleDateFormatNewDate = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat simpleDateFormatNewDateTime = new SimpleDateFormat("HH:MM:ss");

        XSSFSheet sheetDateDetail = xssfWorkbook.getXSSFWorkbook().getSheetAt(2);
        //当日
        sheetDateDetail.createRow(0).createCell(0).setCellValue(simpleDateFormatNewDate.format(new Date()));
        //当时
        sheetDateDetail.createRow(0).createCell(1).setCellValue(simpleDateFormatNewDateTime.format(new Date()));

        // 刷新公式
        //xssfWorkbook.setForceFormulaRecalculation(true);
        //使用evaluateFormulaCell对函数单元格进行强行更新计算
        //xssfWorkbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

        System.out.println("替换成功");

        //导出
        BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream("E:\\Test\\testXfy_zss_modelZssNew.xlsx"));
        xssfWorkbook.write(fos);
        xssfWorkbook.dispose();
        fos.flush();
        fos.close();
        System.out.println("导出成功");

    }
}
