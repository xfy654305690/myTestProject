package com.project.business;

import com.project.model.zj_Report_Xubao_Tx;
import com.project.model.zj_Report_Xubao_Xf;
import com.project.model.zj_Report_Xubao_Zj;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_XubaoDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class zj_Report_Xubao_Business {

    public static  final  String config="mybatis.xml";

    //取数导出excle
    public static void report_Xubao_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

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
        List<zj_Report_Xubao_Zj> zj_Report_XubaoList_Zj =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Zj(endDate,nowMonth );

        //续包县份数据
        List<zj_Report_Xubao_Xf> zj_Report_XubaoList_Xf =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Xf(endDate,nowMonth );

        //续包条线数据
        List<zj_Report_Xubao_Tx> zj_Report_XubaoList_Tx =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Tx(endDate,nowMonth );


        sqlSession.close();

        zj_Report_Xubao_Zj Zj_Report_Xubao_Zj=new zj_Report_Xubao_Zj();
        zj_Report_Xubao_Xf Zj_Report_Xubao_Xf=new zj_Report_Xubao_Xf();
        zj_Report_Xubao_Tx Zj_Report_Xubao_Tx=new zj_Report_Xubao_Tx();

        dealExcle DealExcle =new dealExcle();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理支局奖扣（1.处理缺口2.处理奖扣）


        //处理EXCLE 支局1
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Zj,"D:\\Test\\test.xlsx","D:\\Test\\test.xlsx",1,Zj_Report_Xubao_Zj);

        //处理EXCLE 县份2
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Xf,"D:\\Test\\test.xlsx","D:\\Test\\test.xlsx",2,Zj_Report_Xubao_Xf);

        //处理EXCLE 条线3
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Tx,"D:\\Test\\test.xlsx","D:\\Test\\test.xlsx",3,Zj_Report_Xubao_Tx);


        //将exlce处理成图片
        DealExcle.excleToPng("D:\\Test\\test.xlsx","D:\\test\\ToImg.png");

        //将图片发送微信
        // 1是文字，2是图片
        DealSendMessage.searchMyFriendAndSend("aiaiai",2,"D:\\test\\ToImg.png");

        //文字后续在加，不急



    }

    //处理支局奖扣
    public static void report_Xubao_Zj_DoDetail( List<zj_Report_Xubao_Zj>  zj_Report_Xubao_Zj_List)  {





    }

}
