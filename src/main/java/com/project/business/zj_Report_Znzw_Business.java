package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_KdDao;
import com.project.view.zj_Report_TcfDao;
import com.project.view.zj_Report_Znzw_Dao;
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

public class zj_Report_Znzw_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\ZNZW\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\ZNZW\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\ZNZW\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\ZNZW\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="鄞战2022-冲刺630";
    public static  final  String wechartPictureAdress="D:\\test\\ZNZW\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\ZNZW\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\ZNZW\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\ZNZW\\SOUCE\\";
    //复制结算文件地址
    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\ZNZW\\";


    //取数导出excle
    public static void report_Znzw_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_Znzw_Dao zj_Report_Znzw_Dao = sqlSession.getMapper(zj_Report_Znzw_Dao.class);

        dealTime dealTime=new dealTime();

        //获取当前季度一号，返回日期格式
        Date startDate=dealTime.get_nowQuarter_FirstDay_ByDate();
        //获取当前季度最后一日，返回日期格式
        Date endDate=dealTime.get_nowQuarter_LastDay_ByDate();
        //上个季度的最后一天
        String lastQuarterMonth =dealTime.get_lastQuarter_LastDay_ByDate_YYYYMM();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();


        //宽带新增数据-KD
        List<zj_Report_Znzw_New_Zj> selectZj_Report_Znzw_New_List_Zj =
                zj_Report_Znzw_Dao.selectZj_Report_Znzw_New_Zj(startDate,endDate);
        //宽带净增数据-KD
        List<zj_Report_Znzw_Rh_Zj> selectZj_Report_Znzw_Rh_List_Zj =
                zj_Report_Znzw_Dao.selectZj_Report_Znzw_Rh_Zj(startDate,endDate);
        //宽带净增数据-KD
        List<zj_Report_Znzw_Kd_Zj> selectZj_Report_Znzw_Kd_List_Zj =
                zj_Report_Znzw_Dao.selectZj_Report_Znzw_Kd_Zj(startDate,endDate);

        //系统时间
        String maxDateString=zj_Report_Znzw_Dao.selectZj_Report_Znzw_Kd_MaxTime();

        sqlSession.close();

        zj_Report_Znzw_New_Zj zj_Report_Znzw_New_Zj=new zj_Report_Znzw_New_Zj();
        zj_Report_Znzw_Rh_Zj zj_Report_Znzw_Rh_Zj=new zj_Report_Znzw_Rh_Zj();
        zj_Report_Znzw_Kd_Zj zj_Report_Znzw_Kd_Zj=new zj_Report_Znzw_Kd_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理时间差
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate =  simpleDateFormatYMD.parse(maxDateString);
        Integer differenceDay=dealTime.get_date_Difference_Values(dealTime.get_nowQuarter_FirstDay_ByDate(),maxDate);

        //处理季度日期差
        Integer quarterDay=dealTime.get_date_Difference_Values(dealTime.get_nowQuarter_FirstDay_ByDate(),dealTime.get_nowQuarter_LastDay_ByDate());

        //
        List<zj_Report_Znzw_New_Zj> selectZj_Report_Znzw_New_List_Zj_Deal =  report_Znzw_New_DoDetail(selectZj_Report_Znzw_New_List_Zj,differenceDay,quarterDay);
        //
        DealExcle.cpoyToExcle(selectZj_Report_Znzw_New_List_Zj_Deal,inExcleFile,OutExcleFile,1,zj_Report_Znzw_New_Zj);
        //
        DealExcle.cpoyToExcle(selectZj_Report_Znzw_Rh_List_Zj,inExcleFile,OutExcleFile,2,zj_Report_Znzw_Rh_Zj);
        //
        DealExcle.cpoyToExcle(selectZj_Report_Znzw_Kd_List_Zj,inExcleFile,OutExcleFile,3,zj_Report_Znzw_Kd_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateString,inExcleFile,OutExcleFile, 4);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"全屋wifi通报"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
        System.out.println("复制文件成功成功");
        String OutPictureFileNew=OutPictureFile+"picture"+"_"+nowDayYYYYMMDD+".png";

        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);

        System.out.println("图片转化成功");

        //将图片发送微信
        // 1是文字，2是图片
        //DealSendMessage.searchMyFriendAndSend(wechartSendName,2,OutPictureFileNew);

        System.out.println("发送微信成功");

        //文字后续在加，不急，预留
        String contextNew=report_Znzw_DoDetail_Context(selectZj_Report_Znzw_New_List_Zj_Deal);
        String context=contextNew+"\n";
        //DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="宽带新增净增通报"+nowDayYYYYMMDD+"详见附件";
        String content="宽带新增净增通报"+nowDayYYYYMMDD+"详见附件";

        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        //DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");

    }

    //新增处理
    public static List<zj_Report_Znzw_New_Zj> report_Znzw_New_DoDetail( List<zj_Report_Znzw_New_Zj> Zj_Report_Znzw_New_List_Zj,Integer differenceDay,Integer quarterDay)  {

        for(int i=0;i<Zj_Report_Znzw_New_List_Zj.size();i++){
            //日均新增
            Zj_Report_Znzw_New_List_Zj.get(i).setZnzw_Amt_Avg( (Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt()/differenceDay));
            //季度缺口
            Zj_Report_Znzw_New_List_Zj.get(i).setZnzw_Amt_Gap((int) Math.ceil(Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt_Avg_Tar()*quarterDay-Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt()));
            //季度指标
            Zj_Report_Znzw_New_List_Zj.get(i).setZnzw_Amt_Tar((int) Math.ceil(Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt_Avg_Tar() *quarterDay));
            //完成率
            if(((Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt()/differenceDay))/Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt_Avg_Tar()<=1.2){
                Zj_Report_Znzw_New_List_Zj.get(i).setZnzw_Amt_Rate(((Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt()/differenceDay))/Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt_Avg_Tar());
            }
            if(((Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt()/differenceDay))/Zj_Report_Znzw_New_List_Zj.get(i).getZnzw_Amt_Avg_Tar()>1.2){
                Zj_Report_Znzw_New_List_Zj.get(i).setZnzw_Amt_Rate(1.2);
            }

        }
        return Zj_Report_Znzw_New_List_Zj;
    }

    //新增处理
    public static String report_Znzw_DoDetail_Context( List<zj_Report_Znzw_New_Zj> selectZj_Report_Znzw_New_List_Zj_Deal)  {

        zj_Report_Znzw_New_Zj heji=selectZj_Report_Znzw_New_List_Zj_Deal.get(selectZj_Report_Znzw_New_List_Zj_Deal.size()-1);

        List<zj_Report_Znzw_New_Zj> detailDone =selectZj_Report_Znzw_New_List_Zj_Deal;
        detailDone.remove(selectZj_Report_Znzw_New_List_Zj_Deal.size()-1);
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
                if(detailDone.get(j).getZnzw_Amt_Rate() > detailDone.get(j + 1).getZnzw_Amt_Rate()) {
                    zj_Report_Znzw_New_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州宽带整体新增共："+(int) Math.floor(heji.getZnzw_Amt())+",整体完成率："+nf.format(heji.getZnzw_Amt_Rate())+"。"+"\n"+"新增完成率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n"+"新增完成率前五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+ detailDone.get(detailDone.size()-2).getZj_Name()+","
                + detailDone.get(detailDone.size()-3).getZj_Name()+","+ detailDone.get(detailDone.size()-4).getZj_Name()+","
                + detailDone.get(detailDone.size()-5).getZj_Name()+"。";
        return context;
    }



}
