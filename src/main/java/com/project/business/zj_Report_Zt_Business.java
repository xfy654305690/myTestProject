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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class zj_Report_Zt_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="C:\\Test\\ZT\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="C:\\Test\\ZT\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="C:\\Test\\ZT\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="C:\\test\\ZT\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="春季营销冲锋行动（鄞战2022）";
    public static  final  String wechartPictureAdress="C:\\test\\ZT\\";
    //微信群名称
    public static  final  String inExcleDataFile="C:\\Test\\ZT\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="C:\\Test\\ZT\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="C:\\Test\\ZT\\SOUCE\\";
    //复制导出文件地址
    public static  final  String OutExcleAccountsFile="C:\\Test\\ZT\\ACCOUNT\\";
    //到达备份名称
    public static  final  String tableName="XFY_RPT_ZLYW_ACT_ASSET";

    //取数导出excle
    public static void report_Zt_Zj_Js() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_ZtDao Zj_Report_ZtDao = sqlSession.getMapper(zj_Report_ZtDao.class);
        dealTime dealTime=new dealTime();

        //获取上月一号，返回日期格式
        Date lastStartDate=dealTime.get_lastlastMonth_FirstDay_ByDate();
        //获取上月最后一号，返回日期格式
        Date lastEndDate=dealTime.get_lastlastMonth_LastDay_ByDate();
        //获取本月一号，返回日期格式
        Date nowStartDate=dealTime.get_lastMonth_FirstDay_ByDate();
        //获取本月最后一号，返回日期格式
        Date nowEndDate=dealTime.get_lastMonth_LastDay_ByDate();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        String lastMonth=dealTime.get_lastMonth_By_String_YYYYMM();
        //表明
        String tableNameNew=tableName+lastMonth;
        //宽带质态
        List<zj_Report_Zt_Kd_Zj> selectZj_Report_Zt_Kd_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Kd_Zj_Js(lastStartDate,lastEndDate,nowStartDate,nowEndDate,tableNameNew);
        //宽带净增数据-KD
        List<zj_Report_Zt_Cdma_Zj> selectZj_Report_Zt_Cmda_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Cmda_Zj_Js(lastStartDate,lastEndDate,nowStartDate,nowEndDate,tableNameNew);
        //宽带ITV数据-KD
        List<zj_Report_Zt_Itv_Zj> selectZj_Report_Zt_Itv_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Itv_Zj_Js(lastStartDate,lastEndDate,nowStartDate,nowEndDate,tableNameNew);

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
        String OutExcleAccountsFileNew =OutExcleAccountsFile+"质态"+nowDayYYYYMMDD+".xlsx";
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
                Zj_Report_ZtDao.selectZj_Report_Zt_Kd_Zj(lastStartDate,lastEndDate,nowStartDate,nowEndDate);
        //宽带净增数据-KD
        List<zj_Report_Zt_Cdma_Zj> selectZj_Report_Zt_Cmda_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Cmda_Zj(lastStartDate,lastEndDate,nowStartDate,nowEndDate);
        //宽带ITV数据-KD
        List<zj_Report_Zt_Itv_Zj> selectZj_Report_Zt_Itv_Zj =
                Zj_Report_ZtDao.selectZj_Report_Zt_Itv_Zj(lastStartDate,lastEndDate,nowStartDate,nowEndDate);

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
        String OutExcleSouceFilenew =OutExcleSouceFile+"质态"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
        System.out.println("复制文件成功成功");
        String OutPictureFileNew=OutPictureFile+"picture"+nowDayYYYYMMDD+".png";

        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);

        System.out.println("图片转化成功");

        //将图片发送微信
        // 1是文字，2是图片
        DealSendMessage.searchMyFriendAndSend(wechartSendName,2,OutPictureFileNew);

        System.out.println("发送微信成功");

        //文字后续在加，不急，预留

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="质态通报"+nowDayYYYYMMDD+"详见附件";
        String content="质态通报"+nowDayYYYYMMDD+"详见附件";

        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,OutPictureFileNew);

        System.out.println("邮件发送成功");

        //发送数据给支局长 *********这里乱码没有结解决
        if (nowDay.equals("07")||nowDay.equals("11")||nowDay.equals("15")||nowDay.equals("19")||nowDay.equals("23")||nowDay.equals("26")||nowDay.equals("28")||nowDay.equals("30")){
        //if (0>1){

            InputStream inDealData= Resources.getResourceAsStream(config);
            SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
            SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
            SqlSession sqlSessionDealData=factoryDealData.openSession();
            zj_Report_ZtDao zj_Report_ZtDaoDealData = sqlSessionDealData.getMapper(zj_Report_ZtDao.class);

            for (int i=0;i<zj_Report_Public_List.size();i++){

                zj_Report_Zt_Data Zj_Report_Zt_Data=new zj_Report_Zt_Data();

                List<zj_Report_Zt_Data> Zj_Report_Zt_Data_List =
                        zj_Report_ZtDaoDealData.selectZj_Report_Zt_Data(lastStartDate,nowEndDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

                String  OutExcleDataFileNew;
                String titleMailSingle ;
                String contentMailSingle;

                if(Zj_Report_Zt_Data_List.size()!=0){
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带净增中离网、拆机、移出清单详见附件"+nowDayYYYYMMDD;
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带净增中离网、拆机、移出清单详见附件"+nowDayYYYYMMDD;
                    OutExcleDataFileNew=OutExcleDataFile+str+"ZT"+nowDayYYYYMMDD+".xlsx";
                    System.out.printf(OutExcleDataFileNew);
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(Zj_Report_Zt_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Zt_Data);
                    System.out.printf("复制成功");
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

                }else{
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"暂无质态清单数据"+nowDayYYYYMMDD;
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"暂无质态清单数据"+nowDayYYYYMMDD;
                    OutExcleDataFileNew=null;
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
                }

            }

            sqlSessionDealData.close();

        }

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
}
