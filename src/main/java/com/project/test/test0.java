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
import java.util.Date;
import java.util.List;

public class test0 {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        String config="mybatis.xml";
        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_XubaoDao Zj_Report_XubaoDao = sqlSession.getMapper(zj_Report_XubaoDao.class);

        dealTime dealTime=new dealTime();
        //获取下月一号，返回日期格式
        Date endDate=dealTime.get_NextMonth_FirstDay_ByDate();

        //获取当前日期YYYYMM格式
        String nowMonth=dealTime.get_date_By_String_YYYYMM();

        //续包支局数据
        List<zj_Report_Xubao_Zj> zj_Report_XubaoList =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Zj(endDate,nowMonth );


        sqlSession.close();

        //获取行个数
        int maxRow = zj_Report_XubaoList.size();
        //获取列个数
        zj_Report_Xubao_Zj Zj_Report_Xubao_Zj=new zj_Report_Xubao_Zj();
        Field[] field=Zj_Report_Xubao_Zj.getClass().getDeclaredFields();
        int maxCell=field.length;

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:\\Test\\test.xlsx"));

        XSSFSheet sheet = xssfWorkbook.getSheetAt(1);

        for (int row = 0; row < maxRow; row++) {
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            /* int maxRol = sheet.getRow(row).getLastCellNum();*/
            XSSFRow rowCreat=sheet.createRow(row);
            //获取studentList具体类，并且
            zj_Report_Xubao_Zj zj_Report_XubaoDetail=zj_Report_XubaoList.get(row);
            Field[] fieldDeatil =zj_Report_XubaoDetail.getClass().getDeclaredFields();

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
                Method m = zj_Report_XubaoDetail.getClass().getMethod("get" + name);
                if (type.equals("class java.lang.String")) {
                    // 调用getter方法获取属性值
                    String value = (String) m.invoke(zj_Report_XubaoDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Integer")) {
                    Integer value = (Integer) m.invoke(zj_Report_XubaoDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Short")) {
                    Short value = (Short) m.invoke(zj_Report_XubaoDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Double")) {
                    Double value = (Double) m.invoke(zj_Report_XubaoDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Boolean")) {
                    Boolean value = (Boolean) m.invoke(zj_Report_XubaoDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.util.Date")) {
                    Date value = (Date) m.invoke(zj_Report_XubaoDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }

            }
        }


        // 刷新公式
        xssfWorkbook.setForceFormulaRecalculation(true);
        //使用evaluateFormulaCell对函数单元格进行强行更新计算
        xssfWorkbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

        //导出
        FileOutputStream fos=new FileOutputStream("D:\\Test\\test.xlsx");

        xssfWorkbook.write(fos);
        fos.close();


    }

}
