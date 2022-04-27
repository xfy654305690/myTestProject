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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class zj_Report_Xubao_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="D:\\Test\\XB\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="D:\\Test\\XB\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="D:\\Test\\XB\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="D:\\test\\XB\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="春季营销冲锋行动（鄞战2022）";

    public static  final  String wechartPictureAdress="D:\\test\\XB\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\XB\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\XB\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\XB\\SOUCE\\";
    //复制导出文件地址
    public static  final  String OutExcleAccountsFile="D:\\Test\\XB\\ACCOUNT\\";
    //复制结算导出文件地址
    public static  final  String OutExcleAccountsFile_JS="D:\\Test\\JS_ALL\\XB\\";

    public static void report_Xubao_Zj_Js() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_XubaoDao Zj_Report_XubaoDao = sqlSession.getMapper(zj_Report_XubaoDao.class);
        dealTime dealTime=new dealTime();
        //获取当月一号，返回日期格式
        Date endDate=dealTime.get_nowMonth_FirstDay_ByDate();
        //获取上月日期YYYYMM格式
        String lastMonth=dealTime.get_lastMonth_By_String_YYYYMM();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //续包支局数据
        List<zj_Report_Xubao_Zj> zj_Report_XubaoList_Zj =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Zj(endDate,lastMonth );

        //续包县份数据
        List<zj_Report_Xubao_Xf> zj_Report_XubaoList_Xf =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Xf(endDate,lastMonth );

        //续包条线数据
        List<zj_Report_Xubao_Tx> zj_Report_XubaoList_Tx =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Tx(endDate,lastMonth );

        String maxTime=dealTime.get_firstDate_By_String_YYYY_MM_DD();

        sqlSession.close();

        zj_Report_Xubao_Zj Zj_Report_Xubao_Zj=new zj_Report_Xubao_Zj();
        zj_Report_Xubao_Xf Zj_Report_Xubao_Xf=new zj_Report_Xubao_Xf();
        zj_Report_Xubao_Tx Zj_Report_Xubao_Tx=new zj_Report_Xubao_Tx();

        dealExcle DealExcle =new dealExcle();
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        //处理支局奖扣（1.处理缺口2.处理奖扣）
        report_Xubao_Zj_DoDetail(zj_Report_XubaoList_Zj);

        //处理EXCLE 支局1
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Zj,inExcleFile,OutExcleFile,1,Zj_Report_Xubao_Zj);

        //处理EXCLE 县份2
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Xf,inExcleFile,OutExcleFile,2,Zj_Report_Xubao_Xf);

        //处理条线奖扣（1.处理缺口2.处理奖扣）
        report_Xubao_Tx_DoDetail(zj_Report_XubaoList_Tx);

        //处理EXCLE 条线3
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Tx,inExcleFile,OutExcleFile,3,Zj_Report_Xubao_Tx);

        //处理EXCLE 时间4
        DealExcle.cpoyToExcleSingle(maxTime,inExcleFile,OutExcleFile,4);

        System.out.println("数据处理成功");

        String OutExcleAccountsFileNew =OutExcleAccountsFile_JS+"续包"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleAccountsFileNew);
        System.out.println("复制文件成功成功");

        String OutPictureFileNew=OutExcleAccountsFile_JS+"picture"+"_"+nowDayYYYYMMDD+".png";

        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);

        System.out.println("图片转化成功");

    }

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

        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //续包支局数据
        List<zj_Report_Xubao_Zj> zj_Report_XubaoList_Zj =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Zj(endDate,nowMonth );


        //续包县份数据
        List<zj_Report_Xubao_Xf> zj_Report_XubaoList_Xf =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Xf(endDate,nowMonth );

        //续包条线数据
        List<zj_Report_Xubao_Tx> zj_Report_XubaoList_Tx =
                Zj_Report_XubaoDao.selectZj_Report_Xubao_Tx(endDate,nowMonth );

        //续包时间数据
        String maxTime=Zj_Report_XubaoDao.selectZj_Report_Xubao_MaxTime();

        sqlSession.close();

        zj_Report_Xubao_Zj Zj_Report_Xubao_Zj=new zj_Report_Xubao_Zj();
        zj_Report_Xubao_Xf Zj_Report_Xubao_Xf=new zj_Report_Xubao_Xf();
        zj_Report_Xubao_Tx Zj_Report_Xubao_Tx=new zj_Report_Xubao_Tx();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理支局奖扣（1.处理缺口2.处理奖扣）
        report_Xubao_Zj_DoDetail(zj_Report_XubaoList_Zj);

        //处理EXCLE 支局1
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Zj,inExcleFile,OutExcleFile,1,Zj_Report_Xubao_Zj);

        //处理EXCLE 县份2
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Xf,inExcleFile,OutExcleFile,2,Zj_Report_Xubao_Xf);

        //处理条线奖扣（1.处理缺口2.处理奖扣）
        report_Xubao_Tx_DoDetail(zj_Report_XubaoList_Tx);

        //处理EXCLE 条线3
        DealExcle.cpoyToExcle(zj_Report_XubaoList_Tx,inExcleFile,OutExcleFile,3,Zj_Report_Xubao_Tx);

        //处理EXCLE 时间4
        DealExcle.cpoyToExcleSingle(maxTime,inExcleFile,OutExcleFile,4);

        System.out.println("数据处理成功");

        //
        String OutExcleSouceFilenew =OutExcleSouceFile+"续包"+"_"+nowDayYYYYMMDD+".xlsx";
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
        String contextXf=report_Heji_DoDetail_Context(zj_Report_XubaoList_Xf);
        String contextXb=report_Xubao_DoDetail_Context(zj_Report_XubaoList_Zj);
        String contextSb=report_shouBao_DoDetail_Context(zj_Report_XubaoList_Zj);
        String context=contextXf+"\n"+contextXb+"\n"+contextSb;
        DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="续包通报"+nowDayYYYYMMDD+"详见附件";
        String content="续包通报"+nowDayYYYYMMDD+"详见附件";
        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");


    }

    public static void report_Xubao_Zj_DoData_NowMonth() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        //获取当前日期YYYYMM格式
        String nowMonth=dealTime.get_date_By_String_YYYYMM();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Xubao_Zj_DoData(nowMonth,nowDayYYYYMMDD,"");

    }
    public static void report_Xubao_Zj_DoData_LastMonth() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        //获取上月日期YYYYMM格式
        String lastMonth=dealTime.get_lastMonth_By_String_YYYYMM();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        report_Xubao_Zj_DoData(lastMonth,nowDayYYYYMMDD,"上月结算:");

    }

    public static void report_Xubao_Zj_DoData(String nowMonth,String nowDayYYYYMMDD,String strContent) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        //发送数据给支局长 *********这里乱码没有结解决
        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        InputStream inDealData= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
        SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
        SqlSession sqlSessionDealData=factoryDealData.openSession();
        zj_Report_XubaoDao Zj_Report_XubaoDaoDealData = sqlSessionDealData.getMapper(zj_Report_XubaoDao.class);

        for (int i=0;i<zj_Report_Public_List.size();i++){

            System.out.println(zj_Report_Public_List.get(i).getZj_Abbr_Name());

            zj_Report_Xubao_Data Zj_Report_Xubao_Data=new zj_Report_Xubao_Data();

            //续包县份数据
            List<zj_Report_Xubao_Data> zj_Report_Xubao_Data_List =
                    Zj_Report_XubaoDaoDealData.selectZj_Report_Xubao_Data(nowMonth,zj_Report_Public_List.get(i).getZj_Abbr_Name());

            String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

            String  OutExcleDataFileNew=OutExcleDataFile+"_"+str+nowDayYYYYMMDD+".xlsx";
            String titleMailSingle =zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"续包数据详见附件"+nowDayYYYYMMDD;
            String contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+strContent+"续包数据详见附件"+nowDayYYYYMMDD;
            System.out.println(OutExcleDataFileNew);
            System.out.println(titleMailSingle);
            //复制值,并且另存为
            DealExcle.cpoyToExcle(zj_Report_Xubao_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Xubao_Data);
            //读取附件并且发送
            DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

        }
        sqlSessionDealData.close();

    }


        //处理支局奖扣
    public static List<zj_Report_Xubao_Zj> report_Xubao_Zj_DoDetail( List<zj_Report_Xubao_Zj>  zj_Report_Xubao_Zj_List)  {

        zj_Report_Xubao_Zj_List.forEach((e) -> {

            if(e.getZj_Name().equals("鄞州潘火工业区支局")||e.getZj_Name().equals("鄞州南商支局")||e.getZj_Name().equals("鄞州政企部")){

                //计算缺口
                e.setBb_Amt_gap((int) Math.ceil(e.getBb_Amt()*(0.83+0.03))-e.getBb_Amt_Com());
                //奖励
                if(e.getBb_Com_Rate()>=(0.83+0.03)){
                    if(e.getBb_Com_Rate_Income()>=0.93){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*1.2));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.85&&e.getBb_Com_Rate_Income()<0.9){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.8));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.8&&e.getBb_Com_Rate_Income()<0.85){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.5));
                    }
                    if(e.getBb_Com_Rate_Income()<0.8){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.3));
                    }
                }
                //扣罚 83
                if(e.getBb_Com_Rate()<(0.83+0.03)&&e.getBb_Com_Rate()>(0.7+0.03)){
                    if(e.getBb_Com_Rate_Income()>=0.93){
                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*0.3));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*0.5));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.85&&e.getBb_Com_Rate_Income()<0.9){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*0.8));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.8&&e.getBb_Com_Rate_Income()<0.85){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*1));
                    }
                    if(e.getBb_Com_Rate_Income()<0.8){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*1.2));
                    }
                }

                //扣罚 70
                if(e.getBb_Com_Rate()<(0.7+0.03)){
                    if(e.getBb_Com_Rate_Income()>=0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*0.3));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*0.5));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.88&&e.getBb_Com_Rate_Income()<0.9){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*0.8));
                    }
                    if(e.getBb_Com_Rate_Income()<0.88){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*-1*50*3));
                    }
                }

            }else if(e.getZj_Name().equals("鄞州潘火支局")){

                //计算缺口
                e.setBb_Amt_gap((int) Math.ceil(e.getBb_Amt()*(0.83-0.03)-e.getBb_Amt_Com()));
                //奖励
                if(e.getBb_Com_Rate()>=(0.83-0.03)){
                    if(e.getBb_Com_Rate_Income()>=0.93){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*1.2));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.85&&e.getBb_Com_Rate_Income()<0.9){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.8));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.8&&e.getBb_Com_Rate_Income()<0.85){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.5));
                    }
                    if(e.getBb_Com_Rate_Income()<0.8){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.3));
                    }
                }
                //扣罚 83
                if(e.getBb_Com_Rate()<(0.83-0.03)&&e.getBb_Com_Rate()>(0.7-0.03)){
                    if(e.getBb_Com_Rate_Income()>=0.93){
                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.3*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.5*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.85&&e.getBb_Com_Rate_Income()<0.9){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.8*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.8&&e.getBb_Com_Rate_Income()<0.85){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*1*-1));
                    }
                    if(e.getBb_Com_Rate_Income()<0.8){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*1.2*-1));
                    }
                }

                //扣罚 70
                if(e.getBb_Com_Rate()<(0.7-0.03)){
                    if(e.getBb_Com_Rate_Income()>=0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.3*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.5*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.88&&e.getBb_Com_Rate_Income()<0.9){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.8*-1));
                    }
                    if(e.getBb_Com_Rate_Income()<0.88){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*3*-1));
                    }
                }


            }else {

                //计算缺口
                e.setBb_Amt_gap((int) Math.ceil(e.getBb_Amt()*0.83)-e.getBb_Amt_Com());
                //奖励
                if(e.getBb_Com_Rate()>=0.83){
                    if(e.getBb_Com_Rate_Income()>=0.93){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*1.2));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.85&&e.getBb_Com_Rate_Income()<0.9){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.8));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.8&&e.getBb_Com_Rate_Income()<0.85){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.5));
                    }
                    if(e.getBb_Com_Rate_Income()<0.8){
                        e.setReward((int)Math.floor(e.getBb_Amt_Com()*10*0.3));
                    }
                }
                //扣罚 83
                if(e.getBb_Com_Rate()<0.83&&e.getBb_Com_Rate()>0.7){
                    if(e.getBb_Com_Rate_Income()>=0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.3*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.5*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.85&&e.getBb_Com_Rate_Income()<0.9){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.8*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.8&&e.getBb_Com_Rate_Income()<0.85){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*1*-1));
                    }
                    if(e.getBb_Com_Rate_Income()<0.8){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*1.2*-1));
                    }
                }

                //扣罚 70
                if(e.getBb_Com_Rate()<0.7){
                    if(e.getBb_Com_Rate_Income()>=0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.3*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.9&&e.getBb_Com_Rate_Income()<0.93){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.5*-1));
                    }
                    if(e.getBb_Com_Rate_Income()>=0.88&&e.getBb_Com_Rate_Income()<0.9){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*0.8*-1));
                    }
                    if(e.getBb_Com_Rate_Income()<0.88){

                        e.setReward((int)Math.ceil((e.getBb_Amt_gap())*50*3*-1));
                    }
                }

            }

            //最后加一个奖扣最多3000
            if (e.getReward()>3000){
                e.setReward(3000);
            }
            if (e.getReward()<-3000){
                e.setReward(-3000);
            }

        });

        return zj_Report_Xubao_Zj_List;

    }

    //处理条线奖扣
    public static List<zj_Report_Xubao_Tx> report_Xubao_Tx_DoDetail( List<zj_Report_Xubao_Tx>  zj_Report_Xubao_Tx_List)  {

        zj_Report_Xubao_Tx_List.forEach((e) -> {
            if(e.getZj_Name().equals("公众")){
                //奖励
                if(e.getBb_Com_Rate()>=0.86 ){
                    e.setReward(2000);
                }else if(e.getBb_Com_Rate()>=0.8){
                    e.setReward(1000);
                }else {
                    e.setReward(0);
                }
                //缺口
                e.setBb_Amt_gap((int) Math.ceil(e.getBb_Amt()*(0.83-0.03))-e.getBb_Amt_Com());

            }
            else
            if(e.getZj_Name().equals("政企")){
                //奖励
                if(e.getBb_Com_Rate()>=0.92 ){
                    e.setReward(2000);
                }else if(e.getBb_Com_Rate()>=0.86){
                    e.setReward(1000);
                }else {
                    e.setReward(0);
                }
                //缺口
                e.setBb_Amt_gap((int) Math.ceil(e.getBb_Amt()*(0.83+0.03))-e.getBb_Amt_Com());

            }
        });

        return zj_Report_Xubao_Tx_List;
    }

    public static String report_Heji_DoDetail_Context( List<zj_Report_Xubao_Xf> zj_Report_XubaoList_Xf)  {
        String context="";
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context="鄞州整体续包率："+nf.format(zj_Report_XubaoList_Xf.get(0).getBb_Com_Rate())+"\n"+
                "鄞州整体收保率："+nf.format(zj_Report_XubaoList_Xf.get(0).getBb_Com_Rate_Income())+"\n";

        return context;
    }


    public static String report_Xubao_DoDetail_Context( List<zj_Report_Xubao_Zj> zj_Report_XubaoList_Zj)  {

        //zj_Report_Xubao_Zj heji=zj_Report_XubaoList_Zj.get(zj_Report_XubaoList_Zj.size()-1);

        List<zj_Report_Xubao_Zj> detailDone =zj_Report_XubaoList_Zj;
        detailDone.remove(zj_Report_XubaoList_Zj.size()-1);
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
                if(detailDone.get(j).getBb_Com_Rate() > detailDone.get(j + 1).getBb_Com_Rate()) {
                    zj_Report_Xubao_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context=
                //"鄞州整体续包率："+nf.format(heji.getBb_Com_Rate())+"\n"+
                        "续包率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n"+"续包率前五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+ detailDone.get(detailDone.size()-2).getZj_Name()+","
                + detailDone.get(detailDone.size()-3).getZj_Name()+","+ detailDone.get(detailDone.size()-4).getZj_Name()+","
                + detailDone.get(detailDone.size()-5).getZj_Name()+"。"
        ;
        return context;
    }
    public static String report_shouBao_DoDetail_Context( List<zj_Report_Xubao_Zj> zj_Report_XubaoList_Zj)  {

        //zj_Report_Xubao_Zj heji=zj_Report_XubaoList_Zj.get(zj_Report_XubaoList_Zj.size()-1);

        List<zj_Report_Xubao_Zj> detailDone =zj_Report_XubaoList_Zj;
        detailDone.remove(zj_Report_XubaoList_Zj.size()-1);
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
                if(detailDone.get(j).getBb_Com_Rate_Income() > detailDone.get(j + 1).getBb_Com_Rate_Income()) {
                    zj_Report_Xubao_Zj temp= detailDone.get(j);
                    detailDone.set(j, detailDone.get(j + 1));detailDone.set(j + 1, temp);
                }
            }
        }

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);

        context=
                //"鄞州整体收保率："+nf.format(heji.getBb_Com_Rate_Income())+"\n"+
                        "收保率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n"+"收保率前五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+ detailDone.get(detailDone.size()-2).getZj_Name()+","
                + detailDone.get(detailDone.size()-3).getZj_Name()+","+ detailDone.get(detailDone.size()-4).getZj_Name()+","
                + detailDone.get(detailDone.size()-5).getZj_Name()+"。"
        ;
        return context;
    }

}
