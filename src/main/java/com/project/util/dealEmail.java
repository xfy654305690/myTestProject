package com.project.util;

import com.project.model.zj_Report_Public;
import org.apache.commons.codec.binary.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class dealEmail {

    //连接邮件，暂时先默认账号密码
    public static void ctreatMailMore(List<zj_Report_Public> zj_Report_Public, String username, String password,String title,String content,String enclosureAdress) throws MessagingException, IOException {

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

        ctreatAttachMail(zj_Report_Public,session,ts,title,content,enclosureAdress);

        ts.close();
    }
    //连接邮件，暂时先默认账号密码,循环发送
    public static void ctreatAttachMail(List<zj_Report_Public>zj_Report_Public,Session session,Transport ts,String title,String content,String enclosureAdress) throws MessagingException, IOException {

            MimeMessage message = new MimeMessage(session);
            //设置邮件的基本信息
            //发件人
            message.setFrom(new InternetAddress("13362851058@189.cn"));
            //收件人*******************************************************
            InternetAddress[] sendTo = new InternetAddress[zj_Report_Public.size()];

            for(int i = 0; i < zj_Report_Public.size(); i++){
                sendTo[i]=new InternetAddress(zj_Report_Public.get(i).getZj_Per_In_Cha_Ema());
            }
            message.setRecipients(Message.RecipientType.TO, sendTo);
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress("654305690@qq.com"));
            //邮件标题
            message.setSubject(title);

            //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(content, "text/html;charset=UTF-8");

            //创建邮件附件
            MimeBodyPart attach = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(enclosureAdress));
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
            message.writeTo(new FileOutputStream("C:\\Test\\mail\\souce\\"+title+".eml"));
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());

    }


    //连接邮件，暂时先默认账号密码
    public static void ctreatMailSingle(zj_Report_Public Zj_Report_Public, String username, String password,String title,String content,String enclosureAdress) throws MessagingException, IOException {

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

        MimeMessage message = new MimeMessage(session);
        //设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("13362851058@189.cn"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(Zj_Report_Public.getZj_Per_In_Cha_Ema()));
        //message.setRecipient(Message.RecipientType.TO, new InternetAddress("654305690@qq.com"));
        //邮件标题
        message.setSubject(title);

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content, "text/html;charset=UTF-8");
        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        if(enclosureAdress!=null){
            DataHandler dh = new DataHandler(new FileDataSource(enclosureAdress));
            attach.setDataHandler(dh);
            attach.setFileName(dh.getName());  //
            mp.addBodyPart(attach);
        }
        mp.addBodyPart(text);
        mp.setSubType("mixed");
        message.setContent(mp);
        message.saveChanges();

        //将创建的Email写入到E盘存储
        message.writeTo(new FileOutputStream("C:\\Test\\mail\\DATA\\"+title+".eml"));
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }



}
