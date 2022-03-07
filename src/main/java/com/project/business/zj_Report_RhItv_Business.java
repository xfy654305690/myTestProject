package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_KdDao;
import com.project.view.zj_Report_RhItvDao;
import com.project.view.zj_Report_TcfDao;
import com.project.view.zj_Report_WyjDao;
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

public class zj_Report_RhItv_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="C:\\Test\\RhItv\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="C:\\Test\\RhItv\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="C:\\Test\\RhItv\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="C:\\test\\RhItv\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="aiaiai";
    public static  final  String wechartPictureAdress="C:\\test\\RhItv\\";
    //微信群名称
    public static  final  String inExcleDataFile="C:\\Test\\RhItv\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="C:\\Test\\RhItv\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="C:\\Test\\RhItv\\SOUCE\\";


    //取数导出excle
    public static void report_RhItv_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_RhItvDao zj_Report_RhItvDao = sqlSession.getMapper(zj_Report_RhItvDao.class);

        dealTime dealTime=new dealTime();

        //获取当前季度一号，返回日期格式
        Date startDate=dealTime.get_nowQuarter_FirstDay_ByDate();
        //获取当前季度最后一号，返回日期格式
        Date endDate=dealTime.get_nowQuarter_LastDay_ByDate();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();

        //融合数据
        List<zj_Report_RhItv_Zj> zj_Report_RhItv_List_Zj =
                zj_Report_RhItvDao.select_zj_Report_RhItv_Zj(startDate,endDate);


        String maxDate=zj_Report_RhItvDao.select_zj_Report_RhItv_Zj_MaxTime();

        sqlSession.close();

        zj_Report_RhItv_Zj zj_Report_RhItv_Zj=new zj_Report_RhItv_Zj();

        dealExcle DealExcle =new dealExcle();
        dealEmail DealEmail=new dealEmail();
        dealSendMessage DealSendMessage=new dealSendMessage();

        //处理支局奖扣（1.处理缺口2.处理奖扣）
        report_Wyj_Zj_DoDetail(zj_Report_RhItv_List_Zj);

        //宽带奖扣 1
        DealExcle.cpoyToExcle(zj_Report_RhItv_List_Zj,inExcleFile,OutExcleFile,1,zj_Report_RhItv_Zj);

        //处理时间
        DealExcle.cpoyToExcleSingle(maxDate,inExcleFile,OutExcleFile, 2);

        System.out.println("数据处理成功");

        //复制文件
        String OutExcleSouceFilenew =OutExcleSouceFile+"融合未渗透ITV"+nowDayYYYYMMDD+".xlsx";
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

        String title ="融合未渗透ITV"+nowDayYYYYMMDD+"详见附件";
        String content="融合未渗透ITV"+nowDayYYYYMMDD+"详见附件";

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
            zj_Report_RhItvDao zj_Report_RhItvDaoDealData = sqlSessionDealData.getMapper(zj_Report_RhItvDao.class);

            for (int i=0;i<zj_Report_Public_List.size();i++){

                zj_Report_RhItv_Data zj_Report_RhItv_Data=new zj_Report_RhItv_Data();

                List<zj_Report_RhItv_Data> Zj_Report_RhItv_Data_List =
                        zj_Report_RhItvDaoDealData.select_Zj_Report_RhItv_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

                String  OutExcleDataFileNew;
                String titleMailSingle ;
                String contentMailSingle;

                if(Zj_Report_RhItv_Data_List.size()!=0){
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"融合未渗透ITV数据详见附件"+nowDayYYYYMMDD;
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"融合未渗透ITV数据详见附件"+nowDayYYYYMMDD;
                    OutExcleDataFileNew=OutExcleDataFile+str+"RhItv"+nowDayYYYYMMDD+".xlsx";
                    System.out.printf(OutExcleDataFileNew);
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(Zj_Report_RhItv_Data_List,null,OutExcleDataFileNew,0,zj_Report_RhItv_Data);
                    System.out.printf("复制成功");
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

                }else{
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本季度暂无ITV未渗透清单"+nowDayYYYYMMDD;
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本季度暂无ITV未渗透清单"+nowDayYYYYMMDD;
                    OutExcleDataFileNew=null;
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
                }

            }

            sqlSessionDealData.close();

        }

    }


    //处理支局奖扣
    public static List<zj_Report_RhItv_Zj> report_Wyj_Zj_DoDetail( List<zj_Report_RhItv_Zj> zj_Report_RhItv_List_Zj )  {

        for(int i=0;i<zj_Report_RhItv_List_Zj.size();i++){
            if (!zj_Report_RhItv_List_Zj.get(i).getZj_Name().equals("合计")){
                //计算缺口
                if(zj_Report_RhItv_List_Zj.get(i).getRh_Add_rate()>=0.85){
                    zj_Report_RhItv_List_Zj.get(i).setRh_Reword(zj_Report_RhItv_List_Zj.get(i).getRh_Amt()*20);
                }
                if(zj_Report_RhItv_List_Zj.get(i).getRh_Add_rate()>=0.7 && zj_Report_RhItv_List_Zj.get(i).getRh_Add_rate()<0.85){
                    zj_Report_RhItv_List_Zj.get(i).setRh_Reword(0);
                }
                if(zj_Report_RhItv_List_Zj.get(i).getRh_Add_rate()>=0.6&& zj_Report_RhItv_List_Zj.get(i).getRh_Add_rate()<0.7){
                    zj_Report_RhItv_List_Zj.get(i).setRh_Reword((int) Math.ceil(zj_Report_RhItv_List_Zj.get(i).getRh_Amt()*0.8-zj_Report_RhItv_List_Zj.get(i).getRh_Add_Amt())*-40);
                }
                if(zj_Report_RhItv_List_Zj.get(i).getRh_Add_rate()<0.6){
                    zj_Report_RhItv_List_Zj.get(i).setRh_Reword((int) Math.ceil(zj_Report_RhItv_List_Zj.get(i).getRh_Amt()*0.8-zj_Report_RhItv_List_Zj.get(i).getRh_Add_Amt())*-50);
                }
            }

        }
        return zj_Report_RhItv_List_Zj;

    }


}