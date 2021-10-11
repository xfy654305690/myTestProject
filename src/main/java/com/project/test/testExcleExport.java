package com.project.test;


import com.project.model.student;
import com.project.view.studentDao;
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
import java.util.Date;
import java.util.List;

public class testExcleExport {
    public static void main(String[] args) throws IOException, ParseException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        String config="mybatis.xml";
        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();
        /*String sqpId="com.project.view.studentDao"+"."+"selectStduent";
        List<student> sudentList=sqlSession.selectList(sqpId);*/
        studentDao stuDao=sqlSession.getMapper(studentDao.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        List<student>sudentList=stuDao.selectStduent(simpleDateFormat.parse("2021-08-23"),simpleDateFormat.parse("2021-08-23"));
        sudentList.forEach(student -> System.out.println(student));
        sqlSession.close();

        //获取行个数
        int maxRow = sudentList.size();
        //获取列个数
        student stu=new student();
        Field[] field=stu.getClass().getDeclaredFields();
        int maxCell=field.length;
        //创建工作簿
       /* XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("E:\\Test\\student.xlsx"));*/
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //读取第一个工作表
        XSSFSheet sheet = xssfWorkbook.createSheet("测试");
        /*int maxRow = sheet.getLastRowNum();*/


        //差一个固定行，思路：第一列直接循环取类的属性名字



        for (int row = 0; row < maxRow; row++) {
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
           /* int maxRol = sheet.getRow(row).getLastCellNum();*/
            XSSFRow rowCreat=sheet.createRow(row);
            //获取studentList具体类，并且
            student stuDetail=sudentList.get(row);
            Field[] fieldDeatil =stuDetail.getClass().getDeclaredFields();

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
                Method m = stuDetail.getClass().getMethod("get" + name);
                if (type.equals("class java.lang.String")) {
                    // 调用getter方法获取属性值
                    String value = (String) m.invoke(stuDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Integer")) {
                    Integer value = (Integer) m.invoke(stuDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Short")) {
                    Short value = (Short) m.invoke(stuDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Double")) {
                    Double value = (Double) m.invoke(stuDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.lang.Boolean")) {
                    Boolean value = (Boolean) m.invoke(stuDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }
                if (type.equals("class java.util.Date")) {
                    Date value = (Date) m.invoke(stuDetail);
                    if (value != null) {
                        cellCreat.setCellValue(value);
                    }
                }

            }
        }

        FileOutputStream fos=new FileOutputStream("E:\\Test\\student.xlsx");
        xssfWorkbook.write(fos);
        fos.close();

    }
}
