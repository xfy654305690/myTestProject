package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_WyjDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class zj_Report_Wyj_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\WYJ\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\WYJ\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\WYJ\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\WYJ\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="鄞战2022-冲刺630";
    public static  final  String wechartPictureAdress="D:\\test\\Tcf\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\WYJ\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\WYJ\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\WYJ\\SOUCE\\";
    //复制导出文件地址
    public static  final  String OutExcleAccountsFile="D:\\Test\\WYJ\\ACCOUNT\\";
    //复制导出文件地址
    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\WYJ\\";

    public static void report_Wyj_Zj_Js() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_WyjDao Zj_Report_WyjDao = sqlSession.getMapper(zj_Report_WyjDao.class);

        dealTime dealTime=new dealTime();
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        //获取上季度一号，返回日期格式
        Date startDate=dealTime.getLastQuarterFirstDay();
        //获取上季度最后一号，返回日期格式
        Date endDate=dealTime.getLastQuarterLastDay();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //获取当前日期DD格式

        //违约金
        List<zj_Report_Wyj_Zj> zj_Report_Wyj_List_Zj =
                Zj_Report_WyjDao.selectZj_Report_Wyj_Zj(startDate,endDate);

        //续包时间数据
        String maxTime=dealTime.get_firstDate_By_String_YYYY_MM_DD();

        sqlSession.close();

        zj_Report_Wyj_Zj Zj_Report_Wyj_Zj=new zj_Report_Wyj_Zj();

        dealExcle DealExcle =new dealExcle();

        //处理支局奖扣（1.处理缺口2.处理奖扣）
        report_Wyj_Zj_DoDetail(zj_Report_Wyj_List_Zj);

        //宽带奖扣 1
        DealExcle.cpoyToExcle(zj_Report_Wyj_List_Zj,inExcleFile,OutExcleFile,1,Zj_Report_Wyj_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxTime,inExcleFile,OutExcleFile, 2);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleAccountsFileNew =OutExcleAccountsFile_JS+"违约金"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleAccountsFileNew);

        String OutPictureFileNew=OutExcleAccountsFile_JS+"picture"+"_"+nowDayYYYYMMDD+".png";
        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);
        System.out.println("图片转化成功");
    }

    //取数导出excle
    public static void report_Wyj_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_WyjDao Zj_Report_WyjDao = sqlSession.getMapper(zj_Report_WyjDao.class);

        dealTime dealTime=new dealTime();

        //获取当前季度一号，返回日期格式
        Date startDate=dealTime.get_nowQuarter_FirstDay_ByDate();
        //获取当前季度最后一号，返回日期格式
        Date endDate=dealTime.get_nowQuarter_LastDay_ByDate();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();

        //违约金
        List<zj_Report_Wyj_Zj> zj_Report_Wyj_List_Zj =
                Zj_Report_WyjDao.selectZj_Report_Wyj_Zj(startDate,endDate);


        String maxDate=Zj_Report_WyjDao.selectZj_Report_Wyj_MaxTime();

        sqlSession.close();

        zj_Report_Wyj_Zj Zj_Report_Wyj_Zj=new zj_Report_Wyj_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理支局奖扣（1.处理缺口2.处理奖扣）
        report_Wyj_Zj_DoDetail(zj_Report_Wyj_List_Zj);

        //宽带奖扣 1
        DealExcle.cpoyToExcle(zj_Report_Wyj_List_Zj,inExcleFile,OutExcleFile,1,Zj_Report_Wyj_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDate,inExcleFile,OutExcleFile, 2);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"违约金"+"_"+nowDayYYYYMMDD+".xlsx";
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
        String context=report_Wyj_DoDetail_Context(zj_Report_Wyj_List_Zj);
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="违约金通报"+nowDayYYYYMMDD+"详见附件";
        String content="违约金通报"+nowDayYYYYMMDD+"详见附件";
        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");

//        //发送数据给支局长 *********这里乱码没有结解决
//        if (nowDay.equals("07")||nowDay.equals("11")||nowDay.equals("15")||nowDay.equals("19")||nowDay.equals("23")||nowDay.equals("26")||nowDay.equals("28")||nowDay.equals("30")){
//            //if (0>1){
//            InputStream inDealData= Resources.getResourceAsStream(config);
//            SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
//            SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
//            SqlSession sqlSessionDealData=factoryDealData.openSession();
//            zj_Report_WyjDao Zj_Report_WyjDaoDealData = sqlSessionDealData.getMapper(zj_Report_WyjDao.class);
//
//            for (int i=0;i<zj_Report_Public_List.size();i++){
//
//                zj_Report_Wyj_Data Zj_Report_Wyj_Data=new zj_Report_Wyj_Data();
//
//                List<zj_Report_Wyj_Data> Zj_Report_Wyj_Data_List =
//                        Zj_Report_WyjDaoDealData.selectZj_Report_Wyj_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());
//
//                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");
//
//                String  OutExcleDataFileNew;
//                String titleMailSingle ;
//                String contentMailSingle;
//
//                if(Zj_Report_Wyj_Data_List.size()!=0){
//                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"违约金产生数据详见附件"+nowDayYYYYMMDD;
//                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"违约金产生数据详见附件"+nowDayYYYYMMDD;
//                    OutExcleDataFileNew=OutExcleDataFile+str+"Wyj"+nowDayYYYYMMDD+".xlsx";
//                    System.out.printf(OutExcleDataFileNew);
//                    //复制值,并且另存为
//                    DealExcle.cpoyToExcle(Zj_Report_Wyj_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Wyj_Data);
//                    System.out.printf("复制成功");
//                    //读取附件并且发送
//                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
//
//                }else{
//                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本季度暂无违约金减免清单"+nowDayYYYYMMDD;
//                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本季度暂无违约金减免清单"+nowDayYYYYMMDD;
//                    OutExcleDataFileNew=null;
//                    //读取附件并且发送
//                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
//                }
//
//            }
//
//            sqlSessionDealData.close();
//            DealSendMessage.searchMyFriendAndSend(wechartSendName,1,"违约金产生数据已经下发EIP邮件，请及时关注。");
//
//
//        }

    }
    public static void report_Wyj_Zj_DoData_NowMonth() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {
        //获取当月一号，返回日期格式
        Date startDate=dealTime.get_nowQuarter_FirstDay_ByDate();
        //获取当月最后一号，返回日期格式
        Date endDate=dealTime.get_nowMonth_LastDay_ByDate();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Wyj_Zj_DoData(startDate,endDate,nowDayYYYYMMDD,"");

    }
    public static void report_Wyj_Zj_DoData_LastMonth() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {
        //获取上季度一号，返回日期格式
        Date startDate=dealTime.getLastQuarterFirstDay();
        //获取上季度最后一号，返回日期格式
        Date endDate=dealTime.getLastQuarterLastDay();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Wyj_Zj_DoData(startDate,endDate,nowDayYYYYMMDD,"上月结算");

    }


    public static void report_Wyj_Zj_DoData(Date startDate,Date endDate,String nowDayYYYYMMDD,String strContent) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        InputStream inDealData= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
        SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
        SqlSession sqlSessionDealData=factoryDealData.openSession();
        zj_Report_WyjDao Zj_Report_WyjDaoDealData = sqlSessionDealData.getMapper(zj_Report_WyjDao.class);

        for (int i=0;i<zj_Report_Public_List.size();i++){

            zj_Report_Wyj_Data Zj_Report_Wyj_Data=new zj_Report_Wyj_Data();
            List<zj_Report_Wyj_Data> Zj_Report_Wyj_Data_List =
                    Zj_Report_WyjDaoDealData.selectZj_Report_Wyj_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

            String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

            String  OutExcleDataFileNew;
            String titleMailSingle ;
            String contentMailSingle;

            if(Zj_Report_Wyj_Data_List.size()!=0){
                titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"违约金产生数据详见附件"+nowDayYYYYMMDD;
                contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"违约金产生数据详见附件"+nowDayYYYYMMDD;
                OutExcleDataFileNew=OutExcleDataFile+str+"_"+"Wyj"+"_"+nowDayYYYYMMDD+".xlsx";
                System.out.printf(OutExcleDataFileNew);
                //复制值,并且另存为
                DealExcle.cpoyToExcle(Zj_Report_Wyj_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Wyj_Data);
                System.out.printf("复制成功");
                //读取附件并且发送
                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

            }else{
                titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"本季度暂无违约金减免清单"+nowDayYYYYMMDD;
                contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"本季度暂无违约金减免清单"+nowDayYYYYMMDD;
                OutExcleDataFileNew=null;
                //读取附件并且发送
                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
            }

        }
        sqlSessionDealData.close();
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,strContent+"违约金产生数据已经下发EIP邮件，请及时关注。");

    }

        //处理支局奖扣
    public static List<zj_Report_Wyj_Zj> report_Wyj_Zj_DoDetail( List<zj_Report_Wyj_Zj> zj_Report_Wyj_List_Zj)  {

        for(int i=0;i<zj_Report_Wyj_List_Zj.size();i++){
            if (!zj_Report_Wyj_List_Zj.get(i).getZj_Name().equals("合计")){
                //计算缺口
                if(zj_Report_Wyj_List_Zj.get(i).getAmt_UnCom()>=0){
                    zj_Report_Wyj_List_Zj.get(i).setReward(0);
                }else {
                    zj_Report_Wyj_List_Zj.get(i).setReward(zj_Report_Wyj_List_Zj.get(i).getAmt_UnCom());
                }
            }

        }
        return zj_Report_Wyj_List_Zj;

    }

    public static String report_Wyj_DoDetail_Context( List<zj_Report_Wyj_Zj> zj_Report_Wyj_List_Zj)  {

        zj_Report_Wyj_Zj heji=zj_Report_Wyj_List_Zj.get(zj_Report_Wyj_List_Zj.size()-1);

        List<zj_Report_Wyj_Zj> detailDone =zj_Report_Wyj_List_Zj;
        detailDone.remove(zj_Report_Wyj_List_Zj.size()-1);
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
                if(detailDone.get(j).getAmt_Rate() > detailDone.get(j + 1).getAmt_Rate()) {
                    zj_Report_Wyj_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州违约金整体共产生金额："+(int) Math.floor(heji.getAmt_Com())+"。使用率："+nf.format(heji.getAmt_Rate())+"。"+"\n"+"使用率前五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+detailDone.get(detailDone.size()-2).getZj_Name()+","+detailDone.get(detailDone.size()-3).getZj_Name()+","
                +detailDone.get(detailDone.size()-4).getZj_Name()+","+detailDone.get(detailDone.size()-5).getZj_Name();
        ;
        return context;
    }


}
