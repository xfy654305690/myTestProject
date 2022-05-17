package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_KdDao;
import com.project.view.zj_Report_Kd_QzDao;
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

public class zj_Report_Kd_Qz_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\Kd_Qz\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\Kd_Qz\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\Kd_Qz\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\Kd_Qz\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="春季营销冲锋行动（鄞战2022）";
    public static  final  String wechartPictureAdress="D:\\test\\Kd_Qz\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\Kd_Qz\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\Kd_Qz\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\Kd_Qz\\SOUCE\\";
    //到达备份名称
    public static  final  String tableName="XFY_KD_ASSET_BAK_BEF";
    //复制结算文件地址
    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\Kd_Qz\\";

    //取数导出excle
//    public static void report_Kd_Zj_Js() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {
//
//        InputStream in= Resources.getResourceAsStream(config);
//        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(in);
//        SqlSession sqlSession=factory.openSession();
//
//        zj_Report_KdDao Zj_Report_KdDao = sqlSession.getMapper(zj_Report_KdDao.class);
//
//        dealTime dealTime=new dealTime();
//
//
//
//        //获取上季度一号，返回日期格式
//        Date startDate=dealTime.getLastQuarterFirstDay();
//        //获取上季度最后一号，返回日期格式
//        Date endDate=dealTime.getLastQuarterLastDay();
//        //上个季度的最后一天
//        String lastQuarterMonth =dealTime.get_lastLastQuarter_LastDay_ByDate_YYYYMM();
//        //上上个季度的最后一天
//        String secondlastQuarterMonth =dealTime.get_second_lastQuarter_LastDay_ByDate_YYYYMM();
//
//        //获取当前日期DD格式
//        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
//
//        //宽带新增数据-KD
//        List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_New_List_Zj =
//                Zj_Report_KdDao.selectZj_Report_Kd_New_Zj(startDate,endDate);
//        //表明
//        String tableNameNew=tableName+lastQuarterMonth;
//        //表明
//        String tableNameOld=tableName+secondlastQuarterMonth;
//        //宽带净增数据-KD
//        List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_List_Zj =
//                Zj_Report_KdDao.selectZj_Report_Kd_Jz_Zj_Js(tableNameOld,tableNameNew);
//
//        //宽带净增数据-KD
//        List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_Year_List_Zj =
//                Zj_Report_KdDao.selectZj_Report_Kd_Jz_Year_Zj_Js(tableNameNew);
//
//        //系统时间
//        String maxDateString=dealTime.get_firstDate_By_String_YYYY_MM_DD();
//
//        sqlSession.close();
//
//        zj_Report_Kd_New_Zj Zj_Report_Kd_New_Zj=new zj_Report_Kd_New_Zj();
//        zj_Report_Kd_Jz_Zj Zj_Report_Kd_Jz_Zj=new zj_Report_Kd_Jz_Zj();
//
//        dealExcle DealExcle =new dealExcle();
//
//        //处理时间差
//        Integer differenceDay=dealTime.get_date_Difference_Values(startDate,endDate);
//        //处理季度日期差
//        Integer quarterDay=dealTime.get_date_Difference_Values(dealTime.get_nowQuarter_FirstDay_ByDate(),dealTime.get_nowQuarter_LastDay_ByDate());
//
//        Integer differenceDayYear=dealTime.get_date_Difference_Values(dealTime.get_nowYear_FirstDay_ByDate(),endDate);
//
//        //处理支局新增
//        List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_New_List_Zj_Deal =  report_Kd_New_DoDetail(selectZj_Report_Kd_New_List_Zj,differenceDay,quarterDay);
//        //宽带新增 1
//        DealExcle.cpoyToExcle(selectZj_Report_Kd_New_List_Zj_Deal,inExcleFile,OutExcleFile,1,Zj_Report_Kd_New_Zj);
//
//        //处理支局奖扣
//        List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_List_Zj_Deal =  report_Kd_Jz_DoDetail(selectZj_Report_Kd_Jz_List_Zj,differenceDay,quarterDay);
//        //宽带净增 2
//        DealExcle.cpoyToExcle(selectZj_Report_Kd_Jz_List_Zj_Deal,inExcleFile,OutExcleFile,2,Zj_Report_Kd_Jz_Zj);
//
//        //处理支局奖扣
//        List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_List_Zj_Deal_Year=  report_Kd_Jz_Year_DoDetail(selectZj_Report_Kd_Jz_Year_List_Zj,differenceDayYear);
//        //宽带净增 2
//        DealExcle.cpoyToExcle(selectZj_Report_Kd_Jz_List_Zj_Deal_Year,inExcleFile,OutExcleFile,3,Zj_Report_Kd_Jz_Zj);
//        //处理时间
//        DealExcle.cpoyToExcleSingle(maxDateString,inExcleFile,OutExcleFile, 4);
//
//        System.out.println("数据处理成功");
//
//        //复制文件
//        String OutExcleSouceFilenew =OutExcleAccountsFile_JS+"宽带新增净增通报"+"_"+nowDayYYYYMMDD+".xlsx";
//        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
//
//    }

    //取数导出excle
    public static void report_Kd_Qz_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_Kd_QzDao zj_Report_Kd_QzDao = sqlSession.getMapper(zj_Report_Kd_QzDao.class);

        dealTime dealTime=new dealTime();

        //获取当前季度一号，返回日期格式
        Date startDate=dealTime.get_nowQuarter_FirstDay_ByDate();
        //获取当前季度最后一日，返回日期格式
        Date endDate=dealTime.get_nowQuarter_LastDay_ByDate();
        //上个季度的最后一天
        String lastQuarterMonth =dealTime.get_lastQuarter_LastDay_ByDate_YYYYMM();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        String lastMonth=dealTime.get_lastMonth_By_String_YYYYMM();
        //表明
        String tableNameNew=tableName+lastMonth;

        //宽带新增数据-KD
        List<zj_Report_Kd_Qz_Jz_Zj> selectZj_Report_Kd_Qz_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Jz_Zj(tableNameNew);

        //宽带净增数据-KD
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_New_Zj(startDate,endDate);

        //系统时间
        String maxDateString=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_MaxTime();

        sqlSession.close();

        zj_Report_Kd_Qz_Jz_Zj Zj_Report_Kd_Qz_Jz_Zj=new zj_Report_Kd_Qz_Jz_Zj();
        zj_Report_Kd_Qz_New_Zj Zj_Report_Kd_Qz_New_Zj=new zj_Report_Kd_Qz_New_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理时间差
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate =  simpleDateFormatYMD.parse(maxDateString);
        Integer differenceDay=dealTime.get_date_Difference_Values(dealTime.get_nowMonth_FirstDay_ByDate(),maxDate);
        //处理季度日期差
        Integer monthDay=dealTime.get_date_Difference_Values(dealTime.get_nowMonth_FirstDay_ByDate(),dealTime.get_nowMonth_LastDay_ByDate());

        //处理支局新增
        List<zj_Report_Kd_Qz_Jz_Zj> Zj_Report_Kd_Qz_Jz_Zj_Deal =  report_Kd_Qz_Jz_DoDetail(selectZj_Report_Kd_Qz_List_Zj,differenceDay,monthDay);
        //宽带新增 1
        DealExcle.cpoyToExcle(Zj_Report_Kd_Qz_Jz_Zj_Deal,inExcleFile,OutExcleFile,1,Zj_Report_Kd_Qz_Jz_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj_Deal =  report_Kd_Qz_New_DoDetail(selectZj_Report_Kd_Qz_New_List_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_New_List_Zj_Deal,inExcleFile,OutExcleFile,2,Zj_Report_Kd_Qz_New_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateString,inExcleFile,OutExcleFile, 3);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"宽带千兆新增净增通报"+"_"+nowDayYYYYMMDD+".xlsx";
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
        String contextNew=report_Kd_Qz_New_DoDetail_Context(selectZj_Report_Kd_Qz_New_List_Zj_Deal);
        String contextJz=report_Kd_Qz_Jz_DoDetail_Context(Zj_Report_Kd_Qz_Jz_Zj_Deal);
        String context=contextNew+"\n"+contextJz;
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="宽带千兆新增净增通报"+nowDayYYYYMMDD+"详见附件";
        String content="宽带千兆新增净增通报"+nowDayYYYYMMDD+"详见附件";

        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");

    }

//    //取数导出excle
//    public static void report_Kd_Zj_DoData_NowMonth() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {
//        report_Kd_Zj_DoData();
//    }

//    public static void report_Kd_Zj_DoData() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {
//
//        //上个季度的最后一天
//        String lastQuarterMonth =dealTime.get_lastQuarter_LastDay_ByDate_YYYYMM();
//        //表明
//        String tableNameNew=tableName+lastQuarterMonth;
//        //获取当前日期DD格式
//        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
//
//        dealEmail DealEmail=new dealEmail();
//        dealExcle DealExcle =new dealExcle();
//        dealSendMessage DealSendMessage=new dealSendMessage();
//        //获取支局长邮箱地址
//        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();
//
//        InputStream inDealData= Resources.getResourceAsStream(config);
//        SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
//        SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
//        SqlSession sqlSessionDealData=factoryDealData.openSession();
//        zj_Report_KdDao Zj_Report_KdDaoDealData = sqlSessionDealData.getMapper(zj_Report_KdDao.class);
//
//        for (int i=0;i<zj_Report_Public_List.size();i++){
//
//            zj_Report_Kd_Jz_Data Zj_report_kd_jz_data=new zj_Report_Kd_Jz_Data();
//
//            List<zj_Report_Kd_Jz_Data> Zj_report_kd_jz_data_List =
//                    Zj_Report_KdDaoDealData.selectZj_Report_Kd_Jz_Data(tableNameNew,zj_Report_Public_List.get(i).getZj_Abbr_Name());
//
//            String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");
//
//            String  OutExcleDataFileNew;
//            String titleMailSingle ;
//            String contentMailSingle;
//
//            if(Zj_report_kd_jz_data_List.size()!=0){
//                titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带净增中离网、拆机、移出清单详见附件"+nowDayYYYYMMDD;
//                contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带净增中离网、拆机、移出清单详见附件"+nowDayYYYYMMDD;
//                OutExcleDataFileNew=OutExcleDataFile+str+"KDJZ"+"_"+nowDayYYYYMMDD+".xlsx";
//                System.out.printf(OutExcleDataFileNew);
//                //复制值,并且另存为
//                DealExcle.cpoyToExcle(Zj_report_kd_jz_data_List,null,OutExcleDataFileNew,0,Zj_report_kd_jz_data);
//                System.out.printf("复制成功");
//                //读取附件并且发送
//                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
//
//            }else{
//                titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带暂无离网、拆机、移出清单数据"+nowDayYYYYMMDD;
//                contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带暂无离网、拆机、移出清单数据"+nowDayYYYYMMDD;
//                OutExcleDataFileNew=null;
//                //读取附件并且发送
//                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
//            }
//
//        }
//
//        sqlSessionDealData.close();
//        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,"宽带净增中离网、拆机、移出清单已经下发EIP邮件，请及时维系，挽留。");
//    }


    //新增处理
    public static List<zj_Report_Kd_Qz_Jz_Zj> report_Kd_Qz_Jz_DoDetail( List<zj_Report_Kd_Qz_Jz_Zj> Zj_Report_Kd_Qz_Jz_Zj,Integer differenceDay,Integer monthDay)  {

        for(int i=0;i<Zj_Report_Kd_Qz_Jz_Zj.size();i++){
            //千兆日均净增数量
            Zj_Report_Kd_Qz_Jz_Zj.get(i).setQz_Avg_Num((double) (Zj_Report_Kd_Qz_Jz_Zj.get(i).getQz_Num()/differenceDay));
            //月度千兆指标
            Zj_Report_Kd_Qz_Jz_Zj.get(i).setZj_Kd_Qz_Jz_Tar((int) Math.ceil(Zj_Report_Kd_Qz_Jz_Zj.get(i).getZj_Kd_Qz_Jz_Avg_Tar()*monthDay));
            //月度千兆指标缺口
            Zj_Report_Kd_Qz_Jz_Zj.get(i).setQz_Gap((int) Math.ceil(Zj_Report_Kd_Qz_Jz_Zj.get(i).getZj_Kd_Qz_Jz_Avg_Tar()*monthDay-Zj_Report_Kd_Qz_Jz_Zj.get(i).getQz_Num()));
            //完成率
            Zj_Report_Kd_Qz_Jz_Zj.get(i).setQz_Rate(((Zj_Report_Kd_Qz_Jz_Zj.get(i).getQz_Num()/differenceDay))/Zj_Report_Kd_Qz_Jz_Zj.get(i).getZj_Kd_Qz_Jz_Avg_Tar());

        }
        return Zj_Report_Kd_Qz_Jz_Zj;
    }

    //净增处理
    public static List<zj_Report_Kd_Qz_New_Zj> report_Kd_Qz_New_DoDetail( List<zj_Report_Kd_Qz_New_Zj> Zj_Report_Kd_Jz_Zj,Integer differenceDay,Integer quarterDay)  {

        for(int i=0;i<Zj_Report_Kd_Jz_Zj.size();i++){
           }

        return Zj_Report_Kd_Jz_Zj;
    }

    //新增处理
    public static String report_Kd_Qz_New_DoDetail_Context( List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj_Deal)  {

        zj_Report_Kd_Qz_New_Zj heji=selectZj_Report_Kd_Qz_New_List_Zj_Deal.get(selectZj_Report_Kd_Qz_New_List_Zj_Deal.size()-1);

        List<zj_Report_Kd_Qz_New_Zj> detailDone =selectZj_Report_Kd_Qz_New_List_Zj_Deal;
        detailDone.remove(selectZj_Report_Kd_Qz_New_List_Zj_Deal.size()-1);
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
                if(detailDone.get(j).getQz_Rate() > detailDone.get(j + 1).getQz_Rate() ) {
                    zj_Report_Kd_Qz_New_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州整体宽带新增千兆共："+(int) Math.floor(heji.getBb_Amt())+",整体宽带新增千兆完成率："+nf.format(heji.getQz_Rate())+"。"+"\n"+"宽带新增千兆占比后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n"+"宽带新增千兆占比前五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+ detailDone.get(detailDone.size()-2).getZj_Name()+","
                + detailDone.get(detailDone.size()-3).getZj_Name()+","+ detailDone.get(detailDone.size()-4).getZj_Name()+","
                + detailDone.get(detailDone.size()-5).getZj_Name()+"。";
        return context;
    }

    //新增处理
    public static String report_Kd_Qz_Jz_DoDetail_Context( List<zj_Report_Kd_Qz_Jz_Zj> selectZj_Report_Kd_Qz_Jz_List_Zj_Deal)  {

        zj_Report_Kd_Qz_Jz_Zj heji=selectZj_Report_Kd_Qz_Jz_List_Zj_Deal.get(selectZj_Report_Kd_Qz_Jz_List_Zj_Deal.size()-1);

        List<zj_Report_Kd_Qz_Jz_Zj> detailDone =selectZj_Report_Kd_Qz_Jz_List_Zj_Deal;
        detailDone.remove(selectZj_Report_Kd_Qz_Jz_List_Zj_Deal.size()-1);
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
                if(detailDone.get(j).getQz_Rate() > detailDone.get(j + 1).getQz_Rate()) {
                    zj_Report_Kd_Qz_Jz_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州整体宽带千兆净增共："+(int) Math.floor(heji.getQz_Rate())+",整体完成率："+nf.format(heji.getQz_Rate())+"。"+"\n"+"宽带千兆净增完成率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n"+"宽带千兆净增完成率前五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+ detailDone.get(detailDone.size()-2).getZj_Name()+","
                + detailDone.get(detailDone.size()-3).getZj_Name()+","+ detailDone.get(detailDone.size()-4).getZj_Name()+","
                + detailDone.get(detailDone.size()-5).getZj_Name()+"。";
        return context;
    }

}
