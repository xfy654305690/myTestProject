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
    public static  final  String wechartSendName="鄞战2022-冲刺630";
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
        String tableNameNew=tableName+lastQuarterMonth;

        List<zj_Report_Kd_Qz_Jz_Zj> selectZj_Report_Kd_Qz_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Jz_Zj(tableNameNew);

        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_New_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_List_Rh_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Rh_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_List_Cl_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_List_New_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_New_Tx(startDate,endDate);

        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_List_Rh_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Rh_Tx(startDate,endDate);

        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_List_Cl_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_Tx(startDate,endDate);

        String maxDateStringQz=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_MaxTime();

        String maxDateStringQzCl=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_MaxTime();

        sqlSession.close();

        zj_Report_Kd_Qz_Jz_Zj Zj_Report_Kd_Qz_Jz_Zj=new zj_Report_Kd_Qz_Jz_Zj();
        zj_Report_Kd_Qz_New_Zj Zj_Report_Kd_Qz_New_Zj=new zj_Report_Kd_Qz_New_Zj();
        zj_Report_Kd_Qz_Cl_Zj Zj_Report_Kd_Qz_Cl_Zj=new zj_Report_Kd_Qz_Cl_Zj();
        zj_Report_Kd_Qz_Rh_Zj Zj_Report_Kd_Qz_Rh_Zj=new zj_Report_Kd_Qz_Rh_Zj();


        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理时间差
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate =  simpleDateFormatYMD.parse(maxDateStringQz);
        Integer differenceDay=dealTime.get_date_Difference_Values(dealTime.get_nowQuarter_FirstDay_ByDate(),maxDate);
        //处理季度日期差
        Integer monthDay=dealTime.get_date_Difference_Values(dealTime.get_nowQuarter_FirstDay_ByDate(),dealTime.get_nowQuarter_LastDay_ByDate());

        //处理支局新增
        List<zj_Report_Kd_Qz_Jz_Zj> Zj_Report_Kd_Qz_Jz_Zj_Deal =  report_Kd_Qz_Jz_DoDetail(selectZj_Report_Kd_Qz_List_Zj,differenceDay,monthDay);
        //宽带新增 1
        DealExcle.cpoyToExcle(Zj_Report_Kd_Qz_Jz_Zj_Deal,inExcleFile,OutExcleFile,1,Zj_Report_Kd_Qz_Jz_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj_Deal =  report_Kd_Qz_New_DoDetail(selectZj_Report_Kd_Qz_New_List_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_New_List_Zj_Deal,inExcleFile,OutExcleFile,2,Zj_Report_Kd_Qz_New_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_List_Zj_Deal =  report_Kd_Qz_Cl_DoDetail(selectZj_Report_Kd_Qz_List_Cl_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Cl_List_Zj_Deal,inExcleFile,OutExcleFile,3,Zj_Report_Kd_Qz_Cl_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_List_Zj_Deal =  report_Kd_Qz_Rh_DoDetail(selectZj_Report_Kd_Qz_List_Rh_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Rh_List_Zj_Deal,inExcleFile,OutExcleFile,4,Zj_Report_Kd_Qz_Rh_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Tx_Deal =  report_Kd_Qz_New_DoDetail(selectZj_Report_Kd_Qz_List_New_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_New_List_Tx_Deal,inExcleFile,OutExcleFile,5,Zj_Report_Kd_Qz_New_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_List_Tx_Deal =  report_Kd_Qz_Cl_DoDetail(selectZj_Report_Kd_Qz_List_Cl_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Cl_List_Tx_Deal,inExcleFile,OutExcleFile,6,Zj_Report_Kd_Qz_Cl_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_List_Tx_Deal =  report_Kd_Qz_Rh_DoDetail(selectZj_Report_Kd_Qz_List_Rh_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Rh_List_Tx_Deal,inExcleFile,OutExcleFile,7,Zj_Report_Kd_Qz_Rh_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateStringQz,inExcleFile,OutExcleFile, 8);
        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateStringQzCl,inExcleFile,OutExcleFile, 9);

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

    //新增处理
    public static List<zj_Report_Kd_Qz_Jz_Zj> report_Kd_Qz_Jz_DoDetail( List<zj_Report_Kd_Qz_Jz_Zj> Zj_Report_Kd_Qz_Jz_Zj,Integer differenceDay,Integer monthDay)  {

        for(int i=0;i<Zj_Report_Kd_Qz_Jz_Zj.size();i++){
            //千兆日均净增数量
            Zj_Report_Kd_Qz_Jz_Zj.get(i).setQz_Avg_Num((Zj_Report_Kd_Qz_Jz_Zj.get(i).getQz_Num()/differenceDay));
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

    //净增处理
    public static List<zj_Report_Kd_Qz_Cl_Zj> report_Kd_Qz_Cl_DoDetail( List<zj_Report_Kd_Qz_Cl_Zj> Zj_Report_Kd_Cl_Zj,Integer differenceDay,Integer quarterDay)  {

        for(int i=0;i<Zj_Report_Kd_Cl_Zj.size();i++){
        }

        return Zj_Report_Kd_Cl_Zj;
    }

    //净增处理
    public static List<zj_Report_Kd_Qz_Rh_Zj> report_Kd_Qz_Rh_DoDetail( List<zj_Report_Kd_Qz_Rh_Zj> Zj_Report_Kd_Qz_List_Rh_Zj,Integer differenceDay,Integer quarterDay)  {

        for(int i=0;i<Zj_Report_Kd_Qz_List_Rh_Zj.size();i++){
        }

        return Zj_Report_Kd_Qz_List_Rh_Zj;
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

        context="鄞州整体宽带新增千兆共："+(int) Math.floor(heji.getBb_Qz_Num())+",整体宽带新增千兆完成率："+nf.format(heji.getQz_Rate())+"。"+"\n"+"宽带新增千兆占比后五支局："+
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

        context="鄞州整体宽带千兆净增共："+(int) Math.floor(heji.getQz_Num())+",整体完成率："+nf.format(heji.getQz_Rate())+"。"+"\n"+"宽带千兆净增完成率后五支局："+
                detailDone.get(0).getZj_Name()+","+detailDone.get(1).getZj_Name()+","+detailDone.get(2).getZj_Name()+","
                +detailDone.get(3).getZj_Name()+","+detailDone.get(4).getZj_Name()+"。\n"+"宽带千兆净增完成率前五支局："+
                detailDone.get(detailDone.size()-1).getZj_Name()+","+ detailDone.get(detailDone.size()-2).getZj_Name()+","
                + detailDone.get(detailDone.size()-3).getZj_Name()+","+ detailDone.get(detailDone.size()-4).getZj_Name()+","
                + detailDone.get(detailDone.size()-5).getZj_Name()+"。";
        return context;
    }


    //取数导出excle
    public static void report_Kd_Qz_Zj_630() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_Kd_QzDao zj_Report_Kd_QzDao = sqlSession.getMapper(zj_Report_Kd_QzDao.class);

        dealTime dealTime=new dealTime();

        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        String StartString = "2022-06-15 ";
        Date startDate = formatter.parse(StartString);

        String EndString = "2022-06-30 ";
        Date endDate = formatter.parse(EndString);
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //表明
        String tableNameNew="XFY_KD_ASSET_BAK_20220615";

        List<zj_Report_Kd_Qz_Jz_Zj> selectZj_Report_Kd_Qz_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Jz_Zj(tableNameNew);

        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_New_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_List_Rh_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Rh_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_List_Cl_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_List_New_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_New_Tx(startDate,endDate);

        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_List_Rh_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Rh_Tx(startDate,endDate);

        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_List_Cl_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_Tx(startDate,endDate);

        String maxDateStringQz=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_MaxTime();

        String maxDateStringQzCl=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_MaxTime();

        sqlSession.close();

        zj_Report_Kd_Qz_Jz_Zj Zj_Report_Kd_Qz_Jz_Zj=new zj_Report_Kd_Qz_Jz_Zj();
        zj_Report_Kd_Qz_New_Zj Zj_Report_Kd_Qz_New_Zj=new zj_Report_Kd_Qz_New_Zj();
        zj_Report_Kd_Qz_Cl_Zj Zj_Report_Kd_Qz_Cl_Zj=new zj_Report_Kd_Qz_Cl_Zj();
        zj_Report_Kd_Qz_Rh_Zj Zj_Report_Kd_Qz_Rh_Zj=new zj_Report_Kd_Qz_Rh_Zj();


        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理时间差
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate =  simpleDateFormatYMD.parse(maxDateStringQz);
        Integer differenceDay=dealTime.get_date_Difference_Values(startDate,maxDate);
        //处理季度日期差
        //Integer monthDay=dealTime.get_date_Difference_Values(dealTime.get_nowMonth_FirstDay_ByDate(),dealTime.get_nowMonth_LastDay_ByDate());
        Integer monthDay=15;

        //处理支局新增
        List<zj_Report_Kd_Qz_Jz_Zj> Zj_Report_Kd_Qz_Jz_Zj_Deal =  report_Kd_Qz_Jz_DoDetail(selectZj_Report_Kd_Qz_List_Zj,differenceDay,monthDay);
        //宽带新增 1
        DealExcle.cpoyToExcle(Zj_Report_Kd_Qz_Jz_Zj_Deal,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx",1,Zj_Report_Kd_Qz_Jz_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj_Deal =  report_Kd_Qz_New_DoDetail(selectZj_Report_Kd_Qz_New_List_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_New_List_Zj_Deal,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx",2,Zj_Report_Kd_Qz_New_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_List_Zj_Deal =  report_Kd_Qz_Cl_DoDetail(selectZj_Report_Kd_Qz_List_Cl_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Cl_List_Zj_Deal,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx",3,Zj_Report_Kd_Qz_Cl_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_List_Zj_Deal =  report_Kd_Qz_Rh_DoDetail(selectZj_Report_Kd_Qz_List_Rh_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Rh_List_Zj_Deal,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx",4,Zj_Report_Kd_Qz_Rh_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Tx_Deal =  report_Kd_Qz_New_DoDetail(selectZj_Report_Kd_Qz_List_New_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_New_List_Tx_Deal,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx",5,Zj_Report_Kd_Qz_New_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_List_Tx_Deal =  report_Kd_Qz_Cl_DoDetail(selectZj_Report_Kd_Qz_List_Cl_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Cl_List_Tx_Deal,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx",6,Zj_Report_Kd_Qz_Cl_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_List_Tx_Deal =  report_Kd_Qz_Rh_DoDetail(selectZj_Report_Kd_Qz_List_Rh_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Rh_List_Tx_Deal,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx",7,Zj_Report_Kd_Qz_Rh_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateStringQz,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx", 8);
        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateStringQzCl,"D:\\Test\\Kd_Qz\\test_630.xlsx","D:\\Test\\Kd_Qz\\test_630.xlsx", 9);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"宽带千兆新增净增通报630专项活动"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
        System.out.println("复制文件成功成功");
        String OutPictureFileNew=OutPictureFile+"picture_630专项活动"+"_"+nowDayYYYYMMDD+".png";

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

        String title ="宽带千兆新增净增通报630专项活动"+nowDayYYYYMMDD+"详见附件";
        String content="宽带千兆新增净增通报630专项活动"+nowDayYYYYMMDD+"详见附件";

        List<String> FileList=new ArrayList();
        FileList.add(OutPictureFileNew);
        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,FileList);

        System.out.println("邮件发送成功");

    }


    //取数导出excle
    public static void report_Kd_Qz_Zj_dangyue() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_Kd_QzDao zj_Report_Kd_QzDao = sqlSession.getMapper(zj_Report_Kd_QzDao.class);

        dealTime dealTime=new dealTime();

        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        String StartString = "2022-06-01 ";
        Date startDate = formatter.parse(StartString);

        String EndString = "2022-06-30 ";
        Date endDate = formatter.parse(EndString);
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //表明
        String tableNameNew="XFY_KD_ASSET_BAK_BEF202205";

        List<zj_Report_Kd_Qz_Jz_Zj> selectZj_Report_Kd_Qz_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Jz_Zj(tableNameNew);

        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_New_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_List_Rh_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Rh_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_List_Cl_Zj =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_Zj(startDate,endDate);

        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_List_New_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_New_Tx(startDate,endDate);

        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_List_Rh_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Rh_Tx(startDate,endDate);

        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_List_Cl_Tx =
                zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_Tx(startDate,endDate);

        String maxDateStringQz=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_MaxTime();

        String maxDateStringQzCl=zj_Report_Kd_QzDao.selectZj_Report_Kd_Qz_Cl_MaxTime();

        sqlSession.close();

        zj_Report_Kd_Qz_Jz_Zj Zj_Report_Kd_Qz_Jz_Zj=new zj_Report_Kd_Qz_Jz_Zj();
        zj_Report_Kd_Qz_New_Zj Zj_Report_Kd_Qz_New_Zj=new zj_Report_Kd_Qz_New_Zj();
        zj_Report_Kd_Qz_Cl_Zj Zj_Report_Kd_Qz_Cl_Zj=new zj_Report_Kd_Qz_Cl_Zj();
        zj_Report_Kd_Qz_Rh_Zj Zj_Report_Kd_Qz_Rh_Zj=new zj_Report_Kd_Qz_Rh_Zj();


        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理时间差
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate =  simpleDateFormatYMD.parse(maxDateStringQz);
        Integer differenceDay=dealTime.get_date_Difference_Values(startDate,maxDate);
        //处理季度日期差
        //Integer monthDay=dealTime.get_date_Difference_Values(dealTime.get_nowMonth_FirstDay_ByDate(),dealTime.get_nowMonth_LastDay_ByDate());
        Integer monthDay=15;

        //处理支局新增
        List<zj_Report_Kd_Qz_Jz_Zj> Zj_Report_Kd_Qz_Jz_Zj_Deal =  report_Kd_Qz_Jz_DoDetail(selectZj_Report_Kd_Qz_List_Zj,differenceDay,monthDay);
        //宽带新增 1
        DealExcle.cpoyToExcle(Zj_Report_Kd_Qz_Jz_Zj_Deal,inExcleFile,OutExcleFile,1,Zj_Report_Kd_Qz_Jz_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Zj_Deal =  report_Kd_Qz_New_DoDetail(selectZj_Report_Kd_Qz_New_List_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_New_List_Zj_Deal,inExcleFile,OutExcleFile,2,Zj_Report_Kd_Qz_New_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_List_Zj_Deal =  report_Kd_Qz_Cl_DoDetail(selectZj_Report_Kd_Qz_List_Cl_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Cl_List_Zj_Deal,inExcleFile,OutExcleFile,3,Zj_Report_Kd_Qz_Cl_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_List_Zj_Deal =  report_Kd_Qz_Rh_DoDetail(selectZj_Report_Kd_Qz_List_Rh_Zj,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Rh_List_Zj_Deal,inExcleFile,OutExcleFile,4,Zj_Report_Kd_Qz_Rh_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_List_Tx_Deal =  report_Kd_Qz_New_DoDetail(selectZj_Report_Kd_Qz_List_New_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_New_List_Tx_Deal,inExcleFile,OutExcleFile,5,Zj_Report_Kd_Qz_New_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_List_Tx_Deal =  report_Kd_Qz_Cl_DoDetail(selectZj_Report_Kd_Qz_List_Cl_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Cl_List_Tx_Deal,inExcleFile,OutExcleFile,6,Zj_Report_Kd_Qz_Cl_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_List_Tx_Deal =  report_Kd_Qz_Rh_DoDetail(selectZj_Report_Kd_Qz_List_Rh_Tx,differenceDay,monthDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Qz_Rh_List_Tx_Deal,inExcleFile,OutExcleFile,7,Zj_Report_Kd_Qz_Rh_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateStringQz,inExcleFile,OutExcleFile, 8);
        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateStringQzCl,inExcleFile,OutExcleFile, 9);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"宽带千兆新增净增通报630专项活动"+"_"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
        System.out.println("复制文件成功成功");
        String OutPictureFileNew=OutPictureFile+"picture_630专项活动"+"_"+nowDayYYYYMMDD+".png";

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

        System.out.println("邮件发送成功");

    }


}
