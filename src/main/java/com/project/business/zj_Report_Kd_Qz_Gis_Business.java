package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_Kd_QzDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class zj_Report_Kd_Qz_Gis_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\Kd_Qz_Gis\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\Kd_Qz_Gis\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\Kd_Qz_Gis\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\Kd_Qz_Gis\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="鄞战2022-冲刺630";

    public static  final  String wechartPictureAdress="D:\\test\\Kd_Qz_Gis\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\Kd_Qz_Gis\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\Kd_Qz_Gis\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\Kd_Qz_Gis\\SOUCE\\";
    //到达备份名称
    public static  final  String tableName="XFY_KD_ASSET_BAK_BEF";
    //复制结算文件地址
    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\Kd_Qz_Gis\\";

    //取数导出excle
    public static void report_Kd_Qz_Gis_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_Kd_QzDao zj_Report_Kd_QzDao = sqlSession.getMapper(zj_Report_Kd_QzDao.class);

        dealTime dealTime=new dealTime();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //宽带新增数据-KD
        List<zj_Report_Kd_Qz_Temp_Gis> selectZj_Report_Kd_Qz_Temp_Gis =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Temp_Gis();

        //系统时间
        String maxDateString=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Gis_TEMP_MaxTime();

        //系统时间
        String maxXGPONDateString=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Gis_XGPON_TEMP_MaxTime();

        sqlSession.close();

        zj_Report_Kd_Qz_Temp_Gis Zj_Report_Kd_Qz_Temp_Gis=new zj_Report_Kd_Qz_Temp_Gis();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //宽带新增 1
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Temp_Gis,inExcleFile,OutExcleFile,1,Zj_Report_Kd_Qz_Temp_Gis);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateString,inExcleFile,OutExcleFile, 2);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxXGPONDateString,inExcleFile,OutExcleFile, 3);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"锁定GIS千兆XGPON提升通报"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
        System.out.println("复制文件成功成功");
        String OutPictureFileNew=OutPictureFile+"picture"+"_"+nowDayYYYYMMDD+".png";

        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);

        System.out.println("图片转化成功");

        //将图片发送微信
        // 1是文字，2是图片
        DealSendMessage.searchMyFriendAndSend(wechartSendName,2,OutPictureFileNew);

        System.out.println("发送微信成功");

        //文字后续在加，不急，预留
        String contextQz=report_Kd_Qz_Temp_Gis_DoDetail_Context(selectZj_Report_Kd_Qz_Temp_Gis);
        String contextXGPON=report_Kd_Qz_Temp_Gis_XGPON_DoDetail_Context(selectZj_Report_Kd_Qz_Temp_Gis);
        String context=contextQz+"\n"+contextXGPON;
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="锁定GIS千兆XGPON提升通报"+nowDayYYYYMMDD+"详见附件";
        String content="锁定GIS千兆XGPON提升通报"+nowDayYYYYMMDD+"详见附件";

        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");

    }

    //新增处理
    public static String report_Kd_Qz_Temp_Gis_DoDetail_Context( List<zj_Report_Kd_Qz_Temp_Gis> selectZj_Report_Kd_Qz_Temp_Gis)  {

        zj_Report_Kd_Qz_Temp_Gis heji=selectZj_Report_Kd_Qz_Temp_Gis.get(selectZj_Report_Kd_Qz_Temp_Gis.size()-1);

        List<zj_Report_Kd_Qz_Temp_Gis> detailDone =selectZj_Report_Kd_Qz_Temp_Gis;
        detailDone.remove(selectZj_Report_Kd_Qz_Temp_Gis.size()-1);
        for(int i=0;i<detailDone.size();i++){
            String s=detailDone.get(i).getZj_Name().replace("鄞州","");
            s=s.replace("支局","");
            s=s.replace("综合","");
            detailDone.get(i).setZj_Name(s);
        }
        String context="";
        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getBb_Qz_Rate() > detailDone.get(j + 1).getBb_Qz_Rate()) {
                    zj_Report_Kd_Qz_Temp_Gis temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州锁定GIS内千兆提升共："+(int) Math.floor(heji.getBb_Qz_Num())+",整体千兆率："+nf.format(heji.getBb_Qz_Rate())+"。"+"\n"+"鄞州锁定GIS千兆提升后三支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()
                +"。\n"+"鄞州锁定GIS千兆提升前三支局："+ detailDone.get(detailDone.size()-1).getZj_Name()+","
                + detailDone.get(detailDone.size()-2).getZj_Name()+"," + detailDone.get(detailDone.size()-3).getZj_Name()+"。";
        return context;
    }

    //新增处理
    public static String report_Kd_Qz_Temp_Gis_XGPON_DoDetail_Context(List<zj_Report_Kd_Qz_Temp_Gis> selectZj_Report_Kd_Qz_Temp_Gis)  {

        zj_Report_Kd_Qz_Temp_Gis heji=selectZj_Report_Kd_Qz_Temp_Gis.get(selectZj_Report_Kd_Qz_Temp_Gis.size()-1);

        List<zj_Report_Kd_Qz_Temp_Gis> detailDone =selectZj_Report_Kd_Qz_Temp_Gis;
        detailDone.remove(selectZj_Report_Kd_Qz_Temp_Gis.size()-1);
        for(int i=0;i<detailDone.size();i++){
            String s=detailDone.get(i).getZj_Name().replace("鄞州","");
            s=s.replace("支局","");
            s=s.replace("综合","");
            detailDone.get(i).setZj_Name(s);
        }
        String context="";
        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getXGPON_Rate() > detailDone.get(j + 1).getXGPON_Rate()) {
                    zj_Report_Kd_Qz_Temp_Gis temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州锁定GIS内XGPON新增共："+(int) Math.floor(heji.getXGPON_Num())+",整体XGPON率："+nf.format(heji.getXGPON_Rate())+"。"
                +"\n"+"XGPON完成率后三支局："+ detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","
                +detailDone.get(2).getZj_Name()+"。\n"+"XGPON完成率前三支局："+ detailDone.get(detailDone.size()-1).getZj_Name()
                +","+ detailDone.get(detailDone.size()-2).getZj_Name()+"," + detailDone.get(detailDone.size()-3).getZj_Name()+"。";
        return context;
    }

}
