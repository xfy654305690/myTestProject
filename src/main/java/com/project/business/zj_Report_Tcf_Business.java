package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class zj_Report_Tcf_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\Tcf\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\Tcf\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\Tcf\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\Tcf\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="鄞战2022“一根筋做融合”";
    public static  final  String wechartPictureAdress="D:\\test\\Tcf\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\Tcf\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\Tcf\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\Tcf\\SOUCE\\";
    //复制导出文件地址
    public static  final  String OutExcleAccountsFile="D:\\Test\\Tcf\\ACCOUNT\\";
    //复制结算文件地址
    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\Tcf\\";

    //取数导出excle
    public static void report_Tcf_Zj_Js() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_TcfDao Zj_Report_TcfDao = sqlSession.getMapper(zj_Report_TcfDao.class);

        dealTime dealTime=new dealTime();

        //获取上月一号，返回日期格式
        Date startDate=dealTime.get_lastMonth_FirstDay_ByDate();
        //获取上月最后一号，返回日期格式
        Date endDate=dealTime.get_lastMonth_LastDay_ByDate();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //调测费数据-KD
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_KDList_Zj =
                Zj_Report_TcfDao.selectZj_Report_Tcf_Kd_Zj(startDate,endDate);

        //调测费数据-ITV
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_ItvList_Zj =
                Zj_Report_TcfDao.selectZj_Report_Tcf_Itv_Zj(startDate,endDate);

        String maxTime=dealTime.get_firstDate_By_String_YYYY_MM_DD();

        sqlSession.close();

        zj_Report_Tcf_Zj Zj_Report_Tcf_Zj=new zj_Report_Tcf_Zj();

        dealExcle DealExcle =new dealExcle();

        //处理支局奖扣
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_KDList_Zj_New =  report_Tcf_Zj_DoDetail(zj_Report_Tcf_KDList_Zj);
        //宽带奖扣 1
        DealExcle.cpoyToExcle(zj_Report_Tcf_KDList_Zj_New,inExcleFile,OutExcleFile,1,Zj_Report_Tcf_Zj);

        //处理支局奖扣
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_ItvList_Zj_New =report_Tcf_Zj_DoDetail(zj_Report_Tcf_ItvList_Zj);

        //ITV奖扣 2
        DealExcle.cpoyToExcle(zj_Report_Tcf_ItvList_Zj_New,inExcleFile,OutExcleFile,2,Zj_Report_Tcf_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxTime,inExcleFile,OutExcleFile, 3);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleAccountsFileNew =OutExcleAccountsFile_JS+"调测费"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleAccountsFileNew);

        String OutPictureFileNew=OutExcleAccountsFile_JS+"picture"+"_"+nowDayYYYYMMDD+".png";
        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);

        System.out.println("图片转化成功");

    }

    //取数导出excle
    public static void report_Tcf_Zj() throws Exception {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_TcfDao Zj_Report_TcfDao = sqlSession.getMapper(zj_Report_TcfDao.class);

        dealTime dealTime=new dealTime();

        //获取当月一号，返回日期格式
        Date startDate=dealTime.get_nowMonth_FirstDay_ByDate();
        //获取当月最后一号，返回日期格式
        Date endDate=dealTime.get_nowMonth_LastDay_ByDate();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();

        //调测费数据-KD
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_KDList_Zj =
                Zj_Report_TcfDao.selectZj_Report_Tcf_Kd_Zj(startDate,endDate);

        //调测费数据-ITV
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_ItvList_Zj =
                Zj_Report_TcfDao.selectZj_Report_Tcf_Itv_Zj(startDate,endDate);

//        zj_Report_Tcf_ItvList_Zj.forEach(zj_Report_Tcf_Zj->{
//            System.out.println(zj_Report_Tcf_Zj.toString()+"/n");
//        });

        String maxDate=Zj_Report_TcfDao.selectZj_Report_Tcf_Itv_MaxTime();

        sqlSession.close();

        zj_Report_Tcf_Zj Zj_Report_Tcf_Zj=new zj_Report_Tcf_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理支局奖扣
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_KDList_Zj_New =  report_Tcf_Zj_DoDetail(zj_Report_Tcf_KDList_Zj);
        //宽带奖扣 1
        DealExcle.cpoyToExcle(zj_Report_Tcf_KDList_Zj_New,inExcleFile,OutExcleFile,1,Zj_Report_Tcf_Zj);

        //处理支局奖扣
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_ItvList_Zj_New =report_Tcf_Zj_DoDetail(zj_Report_Tcf_ItvList_Zj);

        //ITV奖扣 2
        DealExcle.cpoyToExcle(zj_Report_Tcf_ItvList_Zj_New,inExcleFile,OutExcleFile,2,Zj_Report_Tcf_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDate,inExcleFile,OutExcleFile, 3);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"调测费"+"_"+nowDayYYYYMMDD+".xlsx";
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
        String contextKd=report_KdTcf_DoDetail_Context(zj_Report_Tcf_KDList_Zj_New);
        String contextItv=report_ItvTcf_DoDetail_Context(zj_Report_Tcf_ItvList_Zj_New);
        String context=contextKd+"\n"+contextItv;
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="调测费通报"+nowDayYYYYMMDD+"详见附件";
        String content="调测费通报"+nowDayYYYYMMDD+"详见附件";

        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");

    }

    public static void report_Tcf_Zj_DoData_NowMonth() throws Exception {
        //获取当月一号，返回日期格式
        Date startDate=dealTime.get_nowMonth_FirstDay_ByDate();
        //获取当月最后一号，返回日期格式
        Date endDate=dealTime.get_nowMonth_LastDay_ByDate();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Tcf_Zj_DoData(startDate,endDate,nowDayYYYYMMDD,"");

    }

    public static void report_Xubao_Zj_DoData_LastMonth() throws Exception {
        //获取上月一号，返回日期格式
        Date startDate=dealTime.get_lastMonth_FirstDay_ByDate();
        //获取上月最后一号，返回日期格式
        Date endDate=dealTime.get_lastMonth_LastDay_ByDate();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Tcf_Zj_DoData(startDate,endDate,nowDayYYYYMMDD,"上月结算");

    }
    public static void report_Tcf_Zj_DoData(Date startDate,Date endDate,String nowDayYYYYMMDD,String strContent) throws Exception {

        dealEmail DealEmail=new dealEmail();
        dealExcle DealExcle =new dealExcle();
        dealSendMessage DealSendMessage=new dealSendMessage();
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        InputStream inDealData= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
        SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
        SqlSession sqlSessionDealData=factoryDealData.openSession();
        zj_Report_TcfDao Zj_Report_TcfDaoDealData = sqlSessionDealData.getMapper(zj_Report_TcfDao.class);

        for (int i=0;i<zj_Report_Public_List.size();i++){

            zj_Report_Tcf_Kd_Data Zj_Report_Tcf_Kd_Data=new zj_Report_Tcf_Kd_Data();
            List<zj_Report_Tcf_Kd_Data> zj_Report_Tcf_Kd_Data_List =
                    Zj_Report_TcfDaoDealData.selectZj_Report_Tcf_Kd_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

            zj_Report_Tcf_Itv_Data Zj_Report_Tcf_Itv_Data=new zj_Report_Tcf_Itv_Data();
            List<zj_Report_Tcf_Itv_Data> zj_Report_Tcf_Itv_Data_List =
                    Zj_Report_TcfDaoDealData.selectZj_Report_Tcf_Itv_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

            String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

            String  OutExcleDataFileNew;
            String titleMailSingle ;
            String contentMailSingle;
            titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"调测费宽带、ITV未收数据详见附件"+nowDayYYYYMMDD;
            contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"调测费宽带、ITV未收数据详见附件"+nowDayYYYYMMDD;
            OutExcleDataFileNew=OutExcleDataFile+str+"_"+"TCF"+"_"+nowDayYYYYMMDD+".xlsx";

            if(zj_Report_Tcf_Kd_Data_List.size()!=0){
                DealExcle.cpoyToExcle(zj_Report_Tcf_Kd_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Tcf_Kd_Data);
            }else{
                //处理时间
                DealExcle.cpoyToExcleSingle(strContent+"暂无宽带未收数据",null,OutExcleDataFileNew, 0);
            }

            if(zj_Report_Tcf_Itv_Data_List.size()!=0) {
                //复制值,并且另存为
                DealExcle.cpoyToExcle(zj_Report_Tcf_Itv_Data_List,OutExcleDataFileNew,OutExcleDataFileNew,1,Zj_Report_Tcf_Itv_Data);
            }else{
                //处理时间
                DealExcle.cpoyToExcleSingle(strContent+"暂无ITV未收数据",OutExcleDataFileNew,OutExcleDataFileNew, 1);
            }
            //读取附件并且发送
            DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
        }

        sqlSessionDealData.close();
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,strContent+"调测费宽带、ITV未收数据已经下发EIP邮件，请及时关注。");

    }

        //处理支局奖扣
    public static List<zj_Report_Tcf_Zj> report_Tcf_Zj_DoDetail( List<zj_Report_Tcf_Zj> zj_Report_Tcf_List_Zj)  {


        for(int i=0;i<zj_Report_Tcf_List_Zj.size();i++){
            if (zj_Report_Tcf_List_Zj.get(i).getZj_Name().equals("合计")){

                //计算缺口
                zj_Report_Tcf_List_Zj.get(i).setTcf_amt_gap((int) Math.ceil(zj_Report_Tcf_List_Zj.get(i).getTcf_Amt()*(0.9)-zj_Report_Tcf_List_Zj.get(i).getTcf_amt_cha()));

            }
            //计算缺口
            zj_Report_Tcf_List_Zj.get(i).setTcf_amt_gap((int) Math.ceil(zj_Report_Tcf_List_Zj.get(i).getTcf_Amt()*(0.95)-zj_Report_Tcf_List_Zj.get(i).getTcf_amt_cha()));
            //计算扣罚
            if(zj_Report_Tcf_List_Zj.get(i).getTcf_amt_rate()<0.95){
                zj_Report_Tcf_List_Zj.get(i).setReward(zj_Report_Tcf_List_Zj.get(i).getTcf_amt_gap()*50*-1);
            }else{
                zj_Report_Tcf_List_Zj.get(i).setReward(0);
            }

        }

        return zj_Report_Tcf_List_Zj;
    }

    public static String report_KdTcf_DoDetail_Context( List<zj_Report_Tcf_Zj> zj_Report_Tcf_KDList_Zj)  {

        zj_Report_Tcf_Zj heji=zj_Report_Tcf_KDList_Zj.get(zj_Report_Tcf_KDList_Zj.size()-1);

        List<zj_Report_Tcf_Zj> detailDone =zj_Report_Tcf_KDList_Zj;
        detailDone.remove(zj_Report_Tcf_KDList_Zj.size()-1);
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
                if(detailDone.get(j).getTcf_amt_rate() > detailDone.get(j + 1).getTcf_amt_rate()) {
                    zj_Report_Tcf_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州宽带调测费整体合计："+nf.format(heji.getTcf_amt_rate())+"。"+"\n"+"完成率后五支局："+
                detailDone.get(0).getZj_Name()+","+ detailDone.get(1).getZj_Name()+","
                + detailDone.get(2).getZj_Name()+","+ detailDone.get(3).getZj_Name()+","
                + detailDone.get(4).getZj_Name()+","
        ;
        return context;
    }

    public static String report_ItvTcf_DoDetail_Context( List<zj_Report_Tcf_Zj> zj_Report_Tcf_KDList_Zj)  {

        zj_Report_Tcf_Zj heji=zj_Report_Tcf_KDList_Zj.get(zj_Report_Tcf_KDList_Zj.size()-1);

        List<zj_Report_Tcf_Zj> detailDone =zj_Report_Tcf_KDList_Zj;
        detailDone.remove(zj_Report_Tcf_KDList_Zj.size()-1);
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
                if(detailDone.get(j).getTcf_amt_rate() > detailDone.get(j + 1).getTcf_amt_rate()) {
                    zj_Report_Tcf_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州ITV调测费整体合计："+nf.format(heji.getTcf_amt_rate())+"。"+"\n"+"收取率后五支局："+
                detailDone.get(0).getZj_Name()+","+ detailDone.get(1).getZj_Name()+","
                + detailDone.get(2).getZj_Name()+","+ detailDone.get(3).getZj_Name()+","
                + detailDone.get(4).getZj_Name()+"。"
        ;
        return context;
    }



}
