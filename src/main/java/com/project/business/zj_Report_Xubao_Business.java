package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_XubaoDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class zj_Report_Xubao_Business {

    public static  final  String config="mybatis.xml";
    public static  final  String inExcleFile="E:\\Test\\test.xlsx";
    public static  final  String OutExcleFile="E:\\Test\\test.xlsx";
    public static  final  String inPictureFile="E:\\Test\\test.xlsx";
    public static  final  String OutPictureFile="E:\\test\\ToImg.png";
    public static  final  String wechartSendName="aiaiai";
    public static  final  String wechartPictureAdress="E:\\test\\ToImg.png";
    public static  final  String inExcleDataFile="E:\\Test\\test.xlsx";
    public static  final  String OutExcleDataFile="E:\\Test\\";

    //取数导出excle
    public static void report_Xubao_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

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
        dealEmail DealEmail=new dealEmail();

        //处理支局奖扣（1.处理缺口2.处理奖扣）
        //report_Xubao_Zj_DoDetail(zj_Report_XubaoList_Zj);

        //处理EXCLE 支局1
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Zj,inExcleFile,OutExcleFile,1,Zj_Report_Xubao_Zj);

        //处理EXCLE 县份2
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Xf,inExcleFile,OutExcleFile,2,Zj_Report_Xubao_Xf);

        //处理EXCLE 条线3
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Tx,inExcleFile,OutExcleFile,3,Zj_Report_Xubao_Tx);

        System.out.println("数据处理成功");

        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFile);

        System.out.println("图片转化成功");

        //将图片发送微信
        // 1是文字，2是图片
        DealSendMessage.searchMyFriendAndSend(wechartSendName,2,wechartPictureAdress);

        System.out.println("发送微信成功");

        //文字后续在加，不急，预留

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="续包通报"+nowMonth+"详见附件";
        String content="续包通报"+nowMonth+"详见附件";
        //邮件发送附件图片
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,wechartPictureAdress);

        System.out.println("邮件发送成功");

        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //发送数据给支局长 *********这里乱码没有结解决
        if (nowDay.equals("5")||nowDay.equals("10")||nowDay.equals("15")||nowDay.equals("20")||nowDay.equals("25")){

            InputStream inDealData= Resources.getResourceAsStream(config);
            SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
            SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
            SqlSession sqlSessionDealData=factoryDealData.openSession();
            zj_Report_XubaoDao Zj_Report_XubaoDaoDealData = sqlSessionDealData.getMapper(zj_Report_XubaoDao.class);

            for (int i=0;i<zj_Report_Public_List.size();i++){

                //续包县份数据
                List<zj_Report_Xubao_Data> zj_Report_Xubao_Data =
                        Zj_Report_XubaoDaoDealData.selectZj_Report_Xubao_Data(nowMonth,zj_Report_Public_List.get(i).getZj_Full_Name());

                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

                String  OutExcleDataFileNew=OutExcleDataFile+str+nowDayYYYYMMDD+".xlsx";
                String titleMailSingle =zj_Report_Public_List.get(i).getZj_Full_Name()+"续包数据详见附件";
                String contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"续包数据详见附件";
                System.out.println(OutExcleDataFileNew);
                System.out.println(titleMailSingle);

                //复制值,并且另存为
                DealExcle.cpoyToExcle(zj_Report_Xubao_Data,null,OutExcleDataFileNew,0,zj_Report_Xubao_Data);
                //读取附件并且发送
                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

            }
            sqlSession.close();

        }


    }

    //处理支局奖扣
    public static List<zj_Report_Xubao_Zj> report_Xubao_Zj_DoDetail( List<zj_Report_Xubao_Zj>  zj_Report_Xubao_Zj_List)  {

        zj_Report_Xubao_Zj_List.forEach((e) -> {
            //计算缺口
            //e.setBb_Amt_gap(String.valueOf(Math.ceil(Integer.getInteger(e.getBb_Amt())*0.83)));
            //计算奖扣


        });

        return zj_Report_Xubao_Zj_List;


    }

}
