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

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import java.util.Properties;

public class testAll {

    public void testAll() throws Exception {
        this.excleDoDetail();
        this.emailDoDetail();

    }

    //取数导出excle
    public  void excleDoDetail() throws IOException, ParseException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

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

    //
    public  void emailDoDetail() throws Exception {

        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.189.cn");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、连上邮件服务器
        ts.connect( "13362851058@189.cn", "Xfy654305690");
        //4、创建邮件

        MimeMessage message = new MimeMessage(session);

        //设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("13362851058@189.cn"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("654305690@qq.com"));
        //邮件标题
        message.setSubject("JavaMail邮件发送测试");

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");

        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("E:\\Test\\student.xlsx"));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());  //

        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        mp.addBodyPart(attach);
        mp.setSubType("mixed");

        message.setContent(mp);
        message.saveChanges();
        //将创建的Email写入到E盘存储
        message.writeTo(new FileOutputStream("E:\\Test\\mail\\attachMail.eml"));


        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }


}
