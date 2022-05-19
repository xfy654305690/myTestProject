package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_KdDao;
import com.project.view.zj_Report_TcfDao;
import com.project.view.zj_Report_ZtDao;
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

public class zj_Report_Zt_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\ZT\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\ZT\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\ZT\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\ZT\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="季度营销冲锋行动（鄞战2022）";
    public static  final  String wechartPictureAdress="D:\\test\\ZT\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\ZT\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\ZT\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\ZT\\SOUCE\\";
    //复制导出文件地址
    public static  final  String OutExcleAccountsFile="D:\\Test\\ZT\\ACCOUNT\\";
    //到达备份名称
    public static  final  String tableName="XFY_RPT_ZLYW_ACT_ASSET";

    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\ZT\\";

    //取数导出excle
    public static void report_Zt_Zj_Js() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_ZtDao Zj_Report_ZtDao = sqlSession.getMapper(zj_Report_ZtDao.class);
        dealTime dealTime=new dealTime();

//        //获取上月一号，返回日期格式
//        Date lastStartDate=dealTime.get_lastlastMonth_FirstDay_ByDate();
//        //获取上月最后一号，返回日期格式
//        Date lastEndDate=dealTime.get_lastlastMonth_LastDay_ByDate();
//        //获取本月一号，返回日期格式
//        Date nowStartDate=dealTime.get_lastMonth_FirstDay_ByDate();
//        //获取本月最后一号，返回日期格式
//        Date nowEndDate=dealTime.get_lastMonth_LastDay_ByDate();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        String lastMonth=dealTime.get_lastMonth_By_String_YYYYMM();
        //表明
        String tableNameNew=tableName+lastMonth;
        //宽带质态
        List<zj_Report_Zt_Kd_Zj> selectZj_Report_Zt_Kd_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Kd_Zj_Js(tableNameNew);
                //Zj_Report_ZtDao.selectZj_Report_Zt_Kd_Zj_Js(lastStartDate,lastEndDate,nowStartDate,nowEndDate,tableNameNew);
        //宽带净增数据-KD
        List<zj_Report_Zt_Cdma_Zj> selectZj_Report_Zt_Cmda_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Cmda_Zj_Js(tableNameNew);
                //Zj_Report_ZtDao.selectZj_Report_Zt_Cmda_Zj_Js(lastStartDate,lastEndDate,nowStartDate,nowEndDate,tableNameNew);
        //宽带ITV数据-KD
        List<zj_Report_Zt_Itv_Zj> selectZj_Report_Zt_Itv_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Itv_Zj_Js(tableNameNew);
                //Zj_Report_ZtDao.selectZj_Report_Zt_Itv_Zj_Js(lastStartDate,lastEndDate,nowStartDate,nowEndDate,tableNameNew);

        String maxTime=dealTime.get_firstDate_By_String_YYYY_MM_DD();
        sqlSession.close();

        zj_Report_Zt_Kd_Zj Zj_Report_Zt_Kd_Zj=new zj_Report_Zt_Kd_Zj();
        zj_Report_Zt_Cdma_Zj Zj_Report_Zt_Cdma_Zj=new zj_Report_Zt_Cdma_Zj();
        zj_Report_Zt_Itv_Zj Zj_Report_Zt_Itv_Zj=new zj_Report_Zt_Itv_Zj();

        dealExcle DealExcle =new dealExcle();

        //处理支局KD
        List<zj_Report_Zt_Kd_Zj>selectZj_Report_Zt_Kd_Zj_Deal = report_Kd_Zt_DoDetail(selectZj_Report_Zt_Kd_Zj);
        //宽带 1
        DealExcle.cpoyToExcle(selectZj_Report_Zt_Kd_Zj_Deal,inExcleFile,OutExcleFile,1,Zj_Report_Zt_Kd_Zj);

        //处理支局CDMA
        List<zj_Report_Zt_Cdma_Zj>selectZj_Report_Zt_Cmda_Zj_Deal =report_Cdma_Zt_DoDetail(selectZj_Report_Zt_Cmda_Zj);
        //CDMA 2
        DealExcle.cpoyToExcle(selectZj_Report_Zt_Cmda_Zj_Deal,inExcleFile,OutExcleFile,2,Zj_Report_Zt_Cdma_Zj);

        //处理支局ITV
        List<zj_Report_Zt_Itv_Zj>selectZj_Report_Zt_Itv_Zj_Deal =report_Itv_Zt_DoDetail(selectZj_Report_Zt_Itv_Zj);
        //ITV 3
        DealExcle.cpoyToExcle(selectZj_Report_Zt_Itv_Zj_Deal,inExcleFile,OutExcleFile,3,Zj_Report_Zt_Itv_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxTime,inExcleFile,OutExcleFile, 4);
        System.out.println("数据处理成功");

        //复制文件
        String OutExcleAccountsFileNew =OutExcleAccountsFile_JS+"质态"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleAccountsFileNew);


    }


    //取数导出excle
    public static void report_Zt_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_ZtDao Zj_Report_ZtDao = sqlSession.getMapper(zj_Report_ZtDao.class);
        zj_Report_TcfDao Zj_Report_TcfDao = sqlSession.getMapper(zj_Report_TcfDao.class);

        dealTime dealTime=new dealTime();

        //获取上月一号，返回日期格式
        Date lastStartDate=dealTime.get_lastMonth_FirstDay_ByDate();
        //获取上月最后一号，返回日期格式
        Date lastEndDate=dealTime.get_lastMonth_LastDay_ByDate();
        //获取本月一号，返回日期格式
        Date nowStartDate=dealTime.get_nowMonth_FirstDay_ByDate();
        //获取本月最后一号，返回日期格式
        Date nowEndDate=dealTime.get_nowMonth_LastDay_ByDate();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();

        //宽带质态
        List<zj_Report_Zt_Kd_Zj> selectZj_Report_Zt_Kd_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Kd_Zj();
                //Zj_Report_ZtDao.selectZj_Report_Zt_Kd_Zj(lastStartDate,lastEndDate,nowStartDate,nowEndDate);
        //宽带净增数据-KD
        List<zj_Report_Zt_Cdma_Zj> selectZj_Report_Zt_Cmda_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Cmda_Zj();
                //Zj_Report_ZtDao.selectZj_Report_Zt_Cmda_Zj(lastStartDate,lastEndDate,nowStartDate,nowEndDate);
        //宽带ITV数据-KD
        List<zj_Report_Zt_Itv_Zj> selectZj_Report_Zt_Itv_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Itv_Zj();
                //Zj_Report_ZtDao.selectZj_Report_Zt_Itv_Zj(lastStartDate,lastEndDate,nowStartDate,nowEndDate);

        //系统时间
        String maxDateString=Zj_Report_TcfDao.selectZj_Report_Tcf_Itv_MaxTime();

        sqlSession.close();

        zj_Report_Zt_Kd_Zj Zj_Report_Zt_Kd_Zj=new zj_Report_Zt_Kd_Zj();
        zj_Report_Zt_Cdma_Zj Zj_Report_Zt_Cdma_Zj=new zj_Report_Zt_Cdma_Zj();
        zj_Report_Zt_Itv_Zj Zj_Report_Zt_Itv_Zj=new zj_Report_Zt_Itv_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

       //处理支局KD
        List<zj_Report_Zt_Kd_Zj>selectZj_Report_Zt_Kd_Zj_Deal = report_Kd_Zt_DoDetail(selectZj_Report_Zt_Kd_Zj);
        //宽带 1
        DealExcle.cpoyToExcle(selectZj_Report_Zt_Kd_Zj_Deal,inExcleFile,OutExcleFile,1,Zj_Report_Zt_Kd_Zj);

        //处理支局CDMA
        List<zj_Report_Zt_Cdma_Zj>selectZj_Report_Zt_Cmda_Zj_Deal =report_Cdma_Zt_DoDetail(selectZj_Report_Zt_Cmda_Zj);
        //CDMA 2
        DealExcle.cpoyToExcle(selectZj_Report_Zt_Cmda_Zj_Deal,inExcleFile,OutExcleFile,2,Zj_Report_Zt_Cdma_Zj);

        //处理支局ITV
        List<zj_Report_Zt_Itv_Zj>selectZj_Report_Zt_Itv_Zj_Deal =report_Itv_Zt_DoDetail(selectZj_Report_Zt_Itv_Zj);
        //ITV 3
        DealExcle.cpoyToExcle(selectZj_Report_Zt_Itv_Zj_Deal,inExcleFile,OutExcleFile,3,Zj_Report_Zt_Itv_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateString,inExcleFile,OutExcleFile, 4);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"质态"+"_"+nowDayYYYYMMDD+".xlsx";
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

        String contextKd=report_Kd_Zt_DoDetail_Context(selectZj_Report_Zt_Kd_Zj_Deal);
        String contextCmda=report_Cdma_Zt_DoDetail_Context(selectZj_Report_Zt_Cmda_Zj_Deal);
        String contextItv=report_Itv_Zt_DoDetail_Context(selectZj_Report_Zt_Itv_Zj_Deal);
        String context=contextKd+contextCmda+contextItv;
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="质态通报"+nowDayYYYYMMDD+"详见附件";
        String content="质态通报"+nowDayYYYYMMDD+"详见附件";
        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");

    }

    public static void report_Tcf_Zj_DoData_NowMonth() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Zt_Zj_DoData(nowDayYYYYMMDD,"",null);

    }

    public static void report_Xubao_Zj_DoData_LastMonth() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {
        String lastMonth=dealTime.get_lastMonth_By_String_YYYYMM();
        //表明
        String tableNameNew=tableName+lastMonth;
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Zt_Zj_DoData(nowDayYYYYMMDD,"上月结算",tableNameNew);

    }

    //取数导出excle
    public static void report_Zt_Zj_DoData(String nowDayYYYYMMDD,String strContent,String tableName) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        //发送数据给支局长 *********这里乱码没有结解决
        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        InputStream inDealData= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
        SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
        SqlSession sqlSessionDealData=factoryDealData.openSession();
        zj_Report_ZtDao zj_Report_ZtDaoDealData = sqlSessionDealData.getMapper(zj_Report_ZtDao.class);

        for (int i=0;i<zj_Report_Public_List.size();i++){

            zj_Report_Zt_Data Zj_Report_Zt_Data=new zj_Report_Zt_Data();
            List<zj_Report_Zt_Data> Zj_Report_Zt_Data_List =null;
            if("".equals(strContent)){
                Zj_Report_Zt_Data_List=zj_Report_ZtDaoDealData.selectZj_Report_Zt_Data(zj_Report_Public_List.get(i).getZj_Abbr_Name());
                //zj_Report_ZtDaoDealData.selectZj_Report_Zt_Data(lastStartDate,nowEndDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());
            }
            if(!"".equals(strContent)){
                Zj_Report_Zt_Data_List=zj_Report_ZtDaoDealData.selectZj_Report_Zt_Data_Js(zj_Report_Public_List.get(i).getZj_Abbr_Name(),tableName);
                //zj_Report_ZtDaoDealData.selectZj_Report_Zt_Data(lastStartDate,nowEndDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());
            }
            String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

            String  OutExcleDataFileNew;
            String titleMailSingle ;
            String contentMailSingle;

            if(Zj_Report_Zt_Data_List.size()!=0){
                titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"质态不活跃清单详见附件"+nowDayYYYYMMDD;
                contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"质态不活跃清单详见附件"+nowDayYYYYMMDD;
                OutExcleDataFileNew=OutExcleDataFile+str+"ZT"+"_"+nowDayYYYYMMDD+".xlsx";
                System.out.printf(OutExcleDataFileNew);
                //复制值,并且另存为
                DealExcle.cpoyToExcle(Zj_Report_Zt_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Zt_Data);
                System.out.printf("复制成功");
                //读取附件并且发送
                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

            }else{
                titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"暂无质态清单数据"+nowDayYYYYMMDD;
                contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"暂无质态清单数据"+nowDayYYYYMMDD;
                OutExcleDataFileNew=null;
                //读取附件并且发送
                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
            }

        }

        sqlSessionDealData.close();
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,strContent+"质态不活跃数据已经下发EIP邮件，请及时促活。");
    }

        //KD处理
    public static List<zj_Report_Zt_Kd_Zj> report_Kd_Zt_DoDetail( List<zj_Report_Zt_Kd_Zj> selectZj_Report_Zt_Kd_Zj )  {

        for(int i=0;i<selectZj_Report_Zt_Kd_Zj.size();i++){
            //当月分数
            Double nowGrade=(selectZj_Report_Zt_Kd_Zj.get(i).getBb_Now_Amt_act_rate()-0.95)*10;
            selectZj_Report_Zt_Kd_Zj.get(i).setBb_Now_Grade(nowGrade);
            //上月分数
            Double lastGrade=(selectZj_Report_Zt_Kd_Zj.get(i).getBb_Las_Amt_act_rate()-0.9)*10;
            selectZj_Report_Zt_Kd_Zj.get(i).setBb_Last_Grade(lastGrade);
            //合计分数
            Double grade=lastGrade+nowGrade;
            if(lastGrade+nowGrade>1){
                grade= Double.valueOf(1);
            }
            if(lastGrade+nowGrade<-1){
                grade= Double.valueOf(-1);
            }
            selectZj_Report_Zt_Kd_Zj.get(i).setBb_Grade(grade);
            //上月奖扣
            if(selectZj_Report_Zt_Kd_Zj.get(i).getBb_Las_Amt_act_rate()>=0.9){
                selectZj_Report_Zt_Kd_Zj.get(i).setBb_Last_Reward((double) 0);
            }else {
                selectZj_Report_Zt_Kd_Zj.get(i).setBb_Last_Reward(-1*selectZj_Report_Zt_Kd_Zj.get(i).getBb_Last_Reward());
            }
        }
        return selectZj_Report_Zt_Kd_Zj;
    }

    //CDMA处理
    public static List<zj_Report_Zt_Cdma_Zj> report_Cdma_Zt_DoDetail( List<zj_Report_Zt_Cdma_Zj> selectZj_Report_Zt_Cmda_Zj)  {

        for(int i=0;i<selectZj_Report_Zt_Cmda_Zj.size();i++){

            //当月分数
            Double  nowGrade=(selectZj_Report_Zt_Cmda_Zj.get(i).getCdma_Now_Amt_act_rate()-0.9)*10;
            selectZj_Report_Zt_Cmda_Zj.get(i).setCdma_Now_Grade(nowGrade);
            //上月分数
            Double lastGrade=(selectZj_Report_Zt_Cmda_Zj.get(i).getCdma_Las_Amt_act_rate()-0.8)*10;
            selectZj_Report_Zt_Cmda_Zj.get(i).setCdma_Last_Grade(lastGrade);
            //合计分数
            Double grade=lastGrade+nowGrade;
            if(lastGrade+nowGrade>2){
                grade= Double.valueOf(2);
            }
            selectZj_Report_Zt_Cmda_Zj.get(i).setCdma_Grade(grade);
            //上月奖扣
            if(selectZj_Report_Zt_Cmda_Zj.get(i).getCdma_Las_Amt_act_rate()>=0.8){
                selectZj_Report_Zt_Cmda_Zj.get(i).setCdma_Last_Reward((double) 0);
            }else {
                selectZj_Report_Zt_Cmda_Zj.get(i).setCdma_Last_Reward(-1*selectZj_Report_Zt_Cmda_Zj.get(i).getCdma_Last_Reward());
            }

        }
        return selectZj_Report_Zt_Cmda_Zj;
    }

    //ITV处理
    public static List<zj_Report_Zt_Itv_Zj> report_Itv_Zt_DoDetail( List<zj_Report_Zt_Itv_Zj> selectZj_Report_Zt_Itv_Zj)  {

        for(int i=0;i<selectZj_Report_Zt_Itv_Zj.size();i++){
            //当月分数
            Double nowGrade=(selectZj_Report_Zt_Itv_Zj.get(i).getItv_Now_Amt_act_rate()-0.95)*10;
            selectZj_Report_Zt_Itv_Zj.get(i).setItv_Now_Grade(nowGrade);
            //上月分数
            Double lastGrade=(selectZj_Report_Zt_Itv_Zj.get(i).getItv_Las_Amt_act_rate()-0.9)*10;
            selectZj_Report_Zt_Itv_Zj.get(i).setItv_Last_Grade(lastGrade);
            //合计分数
            Double grade=lastGrade+nowGrade;
            if(lastGrade+nowGrade>1){
                grade= Double.valueOf(1);
            }
            if(lastGrade+nowGrade<-1){
                grade= Double.valueOf(-1);
            }
            selectZj_Report_Zt_Itv_Zj.get(i).setItv_Grade(grade);
            //上月奖扣
            if(selectZj_Report_Zt_Itv_Zj.get(i).getItv_Las_Amt_act_rate()>=0.8){
                selectZj_Report_Zt_Itv_Zj.get(i).setItv_Last_Reward((double) 0);
            }else {
                selectZj_Report_Zt_Itv_Zj.get(i).setItv_Last_Reward(-1*selectZj_Report_Zt_Itv_Zj.get(i).getItv_Last_Reward());
            }

        }
        return selectZj_Report_Zt_Itv_Zj;
    }

    //新增处理
    public static String report_Kd_Zt_DoDetail_Context( List<zj_Report_Zt_Kd_Zj> selectZj_Report_Zt_Kd_Zj_Deal)  {

        zj_Report_Zt_Kd_Zj heji=selectZj_Report_Zt_Kd_Zj_Deal.get(selectZj_Report_Zt_Kd_Zj_Deal.size()-1);

        List<zj_Report_Zt_Kd_Zj> detailDone =selectZj_Report_Zt_Kd_Zj_Deal;
        detailDone.remove(selectZj_Report_Zt_Kd_Zj_Deal.size()-1);
        for(int i=0;i<detailDone.size();i++){
            String s=detailDone.get(i).getZj_Name().replace("鄞州","");
            s=s.replace("支局","");
            s=s.replace("综合","");
            detailDone.get(i).setZj_Name(s);
        }
        String contextKdDy="";
        String contextKdSy="";
        String context="";
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getBb_Now_Amt_act_rate() > detailDone.get(j + 1).getBb_Now_Amt_act_rate()) {
                    zj_Report_Zt_Kd_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        contextKdDy="鄞州当月宽带整体活跃率："+nf.format(heji.getBb_Now_Amt_act_rate())+"。"+"\n"+"当月宽带活跃率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n";


        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getBb_Las_Amt_act_rate() > detailDone.get(j + 1).getBb_Las_Amt_act_rate()) {
                    zj_Report_Zt_Kd_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        contextKdSy="鄞州上月宽带整体活跃率："+nf.format(heji.getBb_Las_Amt_act_rate())+"。"+"\n"+"上月宽带活跃率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n";

        context=contextKdDy+contextKdSy;

        return context;
    }

    //新增处理
    public static String report_Cdma_Zt_DoDetail_Context( List<zj_Report_Zt_Cdma_Zj>selectZj_Report_Zt_Cmda_Zj_Deal)  {

        zj_Report_Zt_Cdma_Zj heji=selectZj_Report_Zt_Cmda_Zj_Deal.get(selectZj_Report_Zt_Cmda_Zj_Deal.size()-1);

        List<zj_Report_Zt_Cdma_Zj> detailDone =selectZj_Report_Zt_Cmda_Zj_Deal;
        detailDone.remove(selectZj_Report_Zt_Cmda_Zj_Deal.size()-1);
        for(int i=0;i<detailDone.size();i++){
            String s=detailDone.get(i).getZj_Name().replace("鄞州","");
            s=s.replace("支局","");
            s=s.replace("综合","");
            detailDone.get(i).setZj_Name(s);
        }
        String contextKdDy="";
        String contextKdSy="";
        String context="";
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getCdma_Now_Amt_act_rate() > detailDone.get(j + 1).getCdma_Now_Amt_act_rate()) {
                    zj_Report_Zt_Cdma_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        contextKdDy="鄞州当月C网整体活跃率："+nf.format(heji.getCdma_Now_Amt_act_rate())+"。"+"\n"+"当月宽C网活跃率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n";


        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getCdma_Las_Amt_act_rate() > detailDone.get(j + 1).getCdma_Las_Amt_act_rate()) {
                    zj_Report_Zt_Cdma_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        contextKdSy="鄞州上月C网整体活跃率："+nf.format(heji.getCdma_Las_Amt_act_rate())+"。"+"\n"+"上月C网活跃率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n";

        context=contextKdDy+contextKdSy;

        return context;
    }

    //新增处理
    public static String report_Itv_Zt_DoDetail_Context( List<zj_Report_Zt_Itv_Zj>selectZj_Report_Zt_Itv_Zj_Deal)  {

        zj_Report_Zt_Itv_Zj heji=selectZj_Report_Zt_Itv_Zj_Deal.get(selectZj_Report_Zt_Itv_Zj_Deal.size()-1);

        List<zj_Report_Zt_Itv_Zj> detailDone =selectZj_Report_Zt_Itv_Zj_Deal;
        detailDone.remove(selectZj_Report_Zt_Itv_Zj_Deal.size()-1);
        for(int i=0;i<detailDone.size();i++){
            String s=detailDone.get(i).getZj_Name().replace("鄞州","");
            s=s.replace("支局","");
            s=s.replace("综合","");
            detailDone.get(i).setZj_Name(s);
        }
        String contextKdDy="";
        String contextKdSy="";
        String context="";
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getItv_Now_Amt_act_rate() > detailDone.get(j + 1).getItv_Now_Amt_act_rate()) {
                    zj_Report_Zt_Itv_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        contextKdDy="鄞州当月ITV整体活跃率："+nf.format(heji.getItv_Now_Amt_act_rate())+"。"+"\n"+"当月宽ITV活跃率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n";


        for(int i=0;i<detailDone.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<detailDone.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(detailDone.get(j).getItv_Las_Amt_act_rate() > detailDone.get(j + 1).getItv_Las_Amt_act_rate()) {
                    zj_Report_Zt_Itv_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        contextKdSy="鄞州上月ITV整体活跃率："+nf.format(heji.getItv_Las_Amt_act_rate())+"。"+"\n"+"上月ITV活跃率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n";

        context=contextKdDy+contextKdSy;

        return context;
    }



}
