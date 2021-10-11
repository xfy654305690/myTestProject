package com.project.test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class testMail {

    public static void main(String[] args) throws Exception {

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
                Message message = createAttachMail(session);
                //5、发送邮件
                ts.sendMessage(message, message.getAllRecipients());
                ts.close();
            }

    private static Message createAttachMail(Session session) throws MessagingException, IOException {

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
                 //返回生成的邮件
                 return message;

    }

}
