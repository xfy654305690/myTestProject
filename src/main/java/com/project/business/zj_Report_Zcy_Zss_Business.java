package com.project.business;


import com.project.model.zj_Report_Zcy_Zss_Zj;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_KdDao;
import com.project.view.zj_Report_Zcy_ZssDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

public class zj_Report_Zcy_Zss_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\Zcy\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\Zcy\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\Zcy\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\Zcy\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="电信-市场部—周朝艳";
    public static  final  String wechartPictureAdress="D:\\test\\Zcy\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\Zcy\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\Zcy\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\Zcy\\SOUCE\\";


    //取数导出excle
    public static void selectZj_Report_Zcy_Zss() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_Zcy_ZssDao zj_Report_Zcy_ZssDao = sqlSession.getMapper(zj_Report_Zcy_ZssDao.class);

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();


        //宽带新增数据-KD
        List<zj_Report_Zcy_Zss_Zj> selectZj_Report_Zcy_Zss_List =
                zj_Report_Zcy_ZssDao.selectZj_Report_Zcy_Zss();


        sqlSession.close();

        zj_Report_Zcy_Zss_Zj zj_Report_Zcy_Zss_Zj=new zj_Report_Zcy_Zss_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();


        DealExcle.cpoyToExcle(selectZj_Report_Zcy_Zss_List,inExcleFile,OutExcleFile,1,zj_Report_Zcy_Zss_Zj);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"春开准实时通报"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
        System.out.println("复制文件成功成功");
        String OutPictureFileNew=OutPictureFile+"picture"+"_"+nowDayYYYYMMDD+".png";

        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);

        System.out.println("图片转化成功");

        //将图片发送微信
        // 1是文字，2是图片
        DealSendMessage.searchMyFriendAndSend(wechartSendName,2,OutExcleSouceFilenew);

        System.out.println("发送微信成功");

    }

}
