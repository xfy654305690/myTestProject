package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_KdDao;
import com.project.view.zj_Report_TcfDao;
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

public class zj_Report_Kd_Wg_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\KD_WG\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\KD_WG\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\KD_WG\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\KD_WG\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="春季营销冲锋行动（鄞战2022）";
    public static  final  String wechartPictureAdress="D:\\test\\KD_WG\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\KD_WG\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\KD_WG\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\KD_WG\\SOUCE\\";
    //到达备份名称
    public static  final  String tableName="XFY_KD_ASSET_BAK_BEF";
    //复制结算文件地址
    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\Kd_WG\\";

    //取数导出excle
    public static void report_Kd_Zj_Gis() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {
        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_KdDao Zj_Report_KdDao = sqlSession.getMapper(zj_Report_KdDao.class);

        dealTime dealTime=new dealTime();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //上个季度的最后一天
        String lastQuarterMonth =dealTime.get_lastQuarter_LastDay_ByDate_YYYYMM();
        //表明
        String tableNameNew=tableName+lastQuarterMonth;
        //宽带新增数据-KD
        List<zj_Report_Kd_Jz_Gis> selectZj_Report_Kd_Jz_Gis =
                Zj_Report_KdDao.selectZj_Report_Kd_Jz_Gis(tableNameNew);

        //宽带净增数据-KD
        List<zj_Report_Kd_Jz_Gis_Zj> selectZj_Report_Kd_Jz_Gis_Zj =
                Zj_Report_KdDao.selectZj_Report_Kd_Jz_Gis_Zj(tableNameNew);
        //系统时间
        String maxDateString=Zj_Report_KdDao.selectZj_Report_Kd_Jz_MaxTime();

        sqlSession.close();

        zj_Report_Kd_Jz_Gis zj_Report_Kd_Jz_Gis=new zj_Report_Kd_Jz_Gis();
        zj_Report_Kd_Jz_Gis_Zj zj_Report_Kd_Jz_Gis_Zj=new zj_Report_Kd_Jz_Gis_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();
        //宽带新增 1
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Jz_Gis_Zj,inExcleFile,OutExcleFile,1,zj_Report_Kd_Jz_Gis_Zj);
        //宽带新增 1
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Jz_Gis,inExcleFile,OutExcleFile,2,zj_Report_Kd_Jz_Gis);
        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateString,inExcleFile,OutExcleFile, 3);
        System.out.println("数据处理成功");
        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"宽带净增网格通报"+"_"+nowDayYYYYMMDD+".xlsx";
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
        String context=report_Kd_New_DoDetail_Context(selectZj_Report_Kd_Jz_Gis_Zj);
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="宽带网格负净增通报"+nowDayYYYYMMDD+"详见附件";
        String content="宽带网格负净增通报"+nowDayYYYYMMDD+"详见附件";
        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        FileList.add(OutExcleSouceFilenew);

        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");


    }

    //新增处理
    public static String report_Kd_New_DoDetail_Context(List<zj_Report_Kd_Jz_Gis_Zj> selectZj_Report_Kd_Jz_Gis_Zj)  {

        zj_Report_Kd_Jz_Gis_Zj heji=selectZj_Report_Kd_Jz_Gis_Zj.get(selectZj_Report_Kd_Jz_Gis_Zj.size()-1);

        List<zj_Report_Kd_Jz_Gis_Zj> detailDone =selectZj_Report_Kd_Jz_Gis_Zj;
        detailDone.remove(selectZj_Report_Kd_Jz_Gis_Zj.size()-1);
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
                if(detailDone.get(j).getFJZ_RATE() > detailDone.get(j + 1).getFJZ_RATE()) {
                    zj_Report_Kd_Jz_Gis_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州整体负净增率："+nf.format(heji.getFJZ_RATE())+"。"+"\n"+"净增率前五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n"+"净增率后五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+ detailDone.get(detailDone.size()-2).getZj_Name()+","
                + detailDone.get(detailDone.size()-3).getZj_Name()+","+ detailDone.get(detailDone.size()-4).getZj_Name()+","
                + detailDone.get(detailDone.size()-5).getZj_Name()+"。";;
        return context;
    }


}
