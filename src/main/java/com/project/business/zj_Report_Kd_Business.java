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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class zj_Report_Kd_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="C:\\Test\\KD\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="C:\\Test\\KD\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="C:\\Test\\KD\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="C:\\test\\KD\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="aiaiai";
    public static  final  String wechartPictureAdress="C:\\test\\KD\\";
    //微信群名称
    public static  final  String inExcleDataFile="C:\\Test\\KD\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="C:\\Test\\KD\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="C:\\Test\\KD\\SOUCE\\";
    //到达备份名称
    public static  final  String tableName="XFY_KD_ASSET_BAK_BEF";

    //取数导出excle
    public static void report_Kd_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_KdDao Zj_Report_KdDao = sqlSession.getMapper(zj_Report_KdDao.class);
        zj_Report_TcfDao Zj_Report_TcfDao = sqlSession.getMapper(zj_Report_TcfDao.class);

        dealTime dealTime=new dealTime();

        //获取下月一号，返回日期格式
        Date startDate=dealTime.get_nowQuarter_FirstDay_ByDate();
        //获取下月一号，返回日期格式
        Date endDate=dealTime.get_nowMonth_LastDay_ByDate();
        //上个季度的最后一天
        String lastQuarterMonth =dealTime.get_lastQuarter_LastDay_ByDate_YYYYMM();
        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //获取下月一号，返回日期格式
        Date nowMonthFirstDay =dealTime.get_nowMonth_FirstDay_ByDate();
        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();

        //宽带新增数据-KD
        List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_New_List_Zj =
                Zj_Report_KdDao.selectZj_Report_Kd_New_Zj(startDate,endDate);
        //表明
        String tableNameNew=tableName+lastQuarterMonth;
        //宽带净增数据-KD
        List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_List_Zj =
                Zj_Report_KdDao.selectZj_Report_Kd_Jz_Zj(tableNameNew);

        //系统时间
        String maxDateString=Zj_Report_TcfDao.selectZj_Report_Tcf_Itv_MaxTime();

        sqlSession.close();

        zj_Report_Kd_New_Zj Zj_Report_Kd_New_Zj=new zj_Report_Kd_New_Zj();
        zj_Report_Kd_Jz_Zj Zj_Report_Kd_Jz_Zj=new zj_Report_Kd_Jz_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理时间差
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate =  simpleDateFormatYMD.parse(maxDateString);
        Integer differenceDay=dealTime.get_date_Difference_Values(dealTime.get_nowQuarter_FirstDay_ByDate(),maxDate);
        //处理季度日期差
        Integer quarterDay=dealTime.get_date_Difference_Values(dealTime.get_nowQuarter_FirstDay_ByDate(),dealTime.get_nowQuarter_LastDay_ByDate());

       //处理支局新增
        List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_New_List_Zj_Deal =  report_Kd_New_DoDetail(selectZj_Report_Kd_New_List_Zj,differenceDay,quarterDay);
        //宽带新增 1
        DealExcle.cpoyToExcle(selectZj_Report_Kd_New_List_Zj_Deal,inExcleFile,OutExcleFile,1,Zj_Report_Kd_New_Zj);

        //处理支局奖扣
        List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_List_Zj_Deal =  report_Kd_Jz_DoDetail(selectZj_Report_Kd_Jz_List_Zj,differenceDay,quarterDay);
        //宽带净增 2
        DealExcle.cpoyToExcle(selectZj_Report_Kd_Jz_List_Zj_Deal,inExcleFile,OutExcleFile,2,Zj_Report_Kd_Jz_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDateString,inExcleFile,OutExcleFile, 3);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"宽带新增净增通报"+nowDayYYYYMMDD+".xlsx";
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

        String title ="宽带新增净增通报"+nowDayYYYYMMDD+"详见附件";
        String content="宽带新增净增通报"+nowDayYYYYMMDD+"详见附件";

        //邮件发送附件图片*****************************
        DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,OutPictureFileNew);

        System.out.println("邮件发送成功");

        //发送数据给支局长 *********这里乱码没有结解决
        //if (nowDay.equals("5")||nowDay.equals("10")||nowDay.equals("15")||nowDay.equals("20")||nowDay.equals("25")){
        if (0>1){

            InputStream inDealData= Resources.getResourceAsStream(config);
            SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
            SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
            SqlSession sqlSessionDealData=factoryDealData.openSession();
            zj_Report_KdDao Zj_Report_KdDaoDealData = sqlSessionDealData.getMapper(zj_Report_KdDao.class);

            for (int i=0;i<zj_Report_Public_List.size();i++){

                zj_Report_Kd_Jz_Data Zj_report_kd_jz_data=new zj_Report_Kd_Jz_Data();


                List<zj_Report_Kd_Jz_Data> Zj_report_kd_jz_data_List =
                        Zj_Report_KdDaoDealData.selectZj_Report_Kd_Jz_Data(tableNameNew,zj_Report_Public_List.get(i).getZj_Abbr_Name());

                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

                String  OutExcleDataFileNew;
                String titleMailSingle ;
                String contentMailSingle;

                if(Zj_report_kd_jz_data_List.size()!=0){
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带净增中离网、拆机、移出清单详见附件"+nowDayYYYYMMDD;
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带净增中离网、拆机、移出清单详见附件"+nowDayYYYYMMDD;
                    OutExcleDataFileNew=OutExcleDataFile+str+"KDJZ"+nowDayYYYYMMDD+".xlsx";
                    System.out.printf(OutExcleDataFileNew);
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(Zj_report_kd_jz_data_List,null,OutExcleDataFileNew,0,Zj_report_kd_jz_data);
                    System.out.printf("复制成功");
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

                }else{
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带暂无离网、拆机、移出清单数据"+nowDayYYYYMMDD;
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"宽带暂无离网、拆机、移出清单数据"+nowDayYYYYMMDD;
                    OutExcleDataFileNew=null;
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
                }

            }

            sqlSession.close();

        }

    }

    //新增处理
    public static List<zj_Report_Kd_New_Zj> report_Kd_New_DoDetail( List<zj_Report_Kd_New_Zj> Zj_Report_Kd_New_Zj,Integer differenceDay,Integer quarterDay)  {

        for(int i=0;i<Zj_Report_Kd_New_Zj.size();i++){
                //日均新增
                Zj_Report_Kd_New_Zj.get(i).setBb_Amt_Avg( (Zj_Report_Kd_New_Zj.get(i).getBb_Amt()/differenceDay));
                //季度缺口
                Zj_Report_Kd_New_Zj.get(i).setBb_Amt_Gap((int) Math.ceil(Zj_Report_Kd_New_Zj.get(i).getBb_Amt_Avg_Tar()*quarterDay-Zj_Report_Kd_New_Zj.get(i).getBb_Amt()));
                //季度指标
                Zj_Report_Kd_New_Zj.get(i).setBb_Amt_Tar((int) Math.ceil(Zj_Report_Kd_New_Zj.get(i).getBb_Amt_Avg_Tar() *quarterDay));
                //完成率
                Zj_Report_Kd_New_Zj.get(i).setBb_Amt_Rate(((Zj_Report_Kd_New_Zj.get(i).getBb_Amt()/differenceDay))/Zj_Report_Kd_New_Zj.get(i).getBb_Amt_Avg_Tar());

        }
        return Zj_Report_Kd_New_Zj;
    }

    //净增处理
    public static List<zj_Report_Kd_Jz_Zj> report_Kd_Jz_DoDetail( List<zj_Report_Kd_Jz_Zj> Zj_Report_Kd_Jz_Zj,Integer differenceDay,Integer quarterDay)  {

        for(int i=0;i<Zj_Report_Kd_Jz_Zj.size();i++){
            //日均新增
            Zj_Report_Kd_Jz_Zj.get(i).setBb_Amt_Avg_Jz((Zj_Report_Kd_Jz_Zj.get(i).getBb_Amt_Jz()/differenceDay));
            //季度缺口
            Zj_Report_Kd_Jz_Zj.get(i).setBb_Amt_Gap_Jz((int) Math.ceil(Zj_Report_Kd_Jz_Zj.get(i).getBb_Amt_Avg_Tar_Jz()*quarterDay-Zj_Report_Kd_Jz_Zj.get(i).getBb_Amt_Jz()));
            //完成率
            Zj_Report_Kd_Jz_Zj.get(i).setBb_Amt_Rate_Jz(((Zj_Report_Kd_Jz_Zj.get(i).getBb_Amt_Jz()/differenceDay))/Zj_Report_Kd_Jz_Zj.get(i).getBb_Amt_Avg_Tar_Jz());
            //季度指标
            Zj_Report_Kd_Jz_Zj.get(i).setBb_Amt_Tar_Jz((int) Math.ceil(Zj_Report_Kd_Jz_Zj.get(i).getBb_Amt_Avg_Jz() *quarterDay));

        }
        return Zj_Report_Kd_Jz_Zj;
    }

}