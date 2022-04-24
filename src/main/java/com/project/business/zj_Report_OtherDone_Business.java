package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_OtherDoneDao;
import com.project.view.zj_Report_RhItvDao;
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

public class zj_Report_OtherDone_Business {

    public static  final  String config="mybatis.xml";

    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\OtherDone\\DATA\\";

    //取数导出excle
    public static void report_OtherDone_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException, ParseException {
        //获取当前日期DD格式
        String nowDay=dealTime.get_date_By_String_DD();
        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();
        //发送数据给支局长 *********这里乱码没有结解决
        //if (nowDay.equals("5")||nowDay.equals("10")||nowDay.equals("15")||nowDay.equals("20")||nowDay.equals("25")){
        //if (0>1){
            InputStream inDealData= Resources.getResourceAsStream(config);
            SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
            SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
            SqlSession sqlSessionDealData=factoryDealData.openSession();
            zj_Report_OtherDoneDao zj_Report_OtherDoneDao = sqlSessionDealData.getMapper(zj_Report_OtherDoneDao.class);
            //获取当前季度最后一日，返回日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse("2022-01-21");//String-->Date
            //获取当前季度最后一日，返回日期格式
            Date endDate=dealTime.get_nowQuarter_LastDay_ByDate();
            //获取当前日期DD格式
            String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();
            dealEmail DealEmail=new dealEmail();
            dealExcle DealExcle=new dealExcle();

            for (int i=0;i<zj_Report_Public_List.size();i++){

                zj_Report_OtherDone_Cdma_Data zj_Report_OtherDone_Cdma_Data=new zj_Report_OtherDone_Cdma_Data();
                zj_Report_OtherDone_Kd_Data zj_Report_OtherDone_Kd_Data=new zj_Report_OtherDone_Kd_Data();
                zj_Report_OtherDone_Gt_Data zj_Report_OtherDone_Gt_Data=new zj_Report_OtherDone_Gt_Data();
                zj_Report_OtherDone_Zd_Data zj_Report_OtherDone_Zd_Data=new zj_Report_OtherDone_Zd_Data();

                //CDMA数据
                List<zj_Report_OtherDone_Cdma_Data> zj_Report_OtherDone_Cdma_Data_List =
                        zj_Report_OtherDoneDao.selectZj_Report_OtherDone_Cdma_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());
                //Kd数据
                List<zj_Report_OtherDone_Kd_Data> zj_Report_OtherDone_Kd_Data_List =
                        zj_Report_OtherDoneDao.selectZj_Report_OtherDone_Kd_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());
                //Gt数据
                List<zj_Report_OtherDone_Gt_Data> zj_Report_OtherDone_Gt_Data_List =
                        zj_Report_OtherDoneDao.selectZj_Report_OtherDone_Gt_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());
                //Gt数据
                List<zj_Report_OtherDone_Zd_Data> zj_Report_OtherDone_Zd_Data_List =
                        zj_Report_OtherDoneDao.selectZj_Report_OtherDone_Zd_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

                String  OutExcleDataFileNew;
                String titleMailSingle ;
                String contentMailSingle;
                titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"非本支局发展数据详见附件"+nowDayYYYYMMDD;
                contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"非本支局发展数据详见附件"+nowDayYYYYMMDD;
                OutExcleDataFileNew=OutExcleDataFile+str+"OtherDone"+nowDayYYYYMMDD+".xlsx";

                if(zj_Report_OtherDone_Cdma_Data_List.size()!=0){
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(zj_Report_OtherDone_Cdma_Data_List,null,OutExcleDataFileNew,0,zj_Report_OtherDone_Cdma_Data);
                }else{
                    //处理时间
                    DealExcle.cpoyToExcleSingle("暂无CDMA数据",null,OutExcleDataFileNew, 0);
                }

                if(zj_Report_OtherDone_Kd_Data_List.size()!=0){
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(zj_Report_OtherDone_Kd_Data_List,OutExcleDataFileNew,OutExcleDataFileNew,1,zj_Report_OtherDone_Kd_Data);
                }else{
                    //处理时间
                    DealExcle.cpoyToExcleSingle("暂无KD数据",OutExcleDataFileNew,OutExcleDataFileNew, 1);
                }

                if(zj_Report_OtherDone_Gt_Data_List.size()!=0){
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(zj_Report_OtherDone_Gt_Data_List,OutExcleDataFileNew,OutExcleDataFileNew,2,zj_Report_OtherDone_Gt_Data);
                }else{
                    //处理时间
                    DealExcle.cpoyToExcleSingle("暂无GT数据",OutExcleDataFileNew,OutExcleDataFileNew, 2);
                }

                if(zj_Report_OtherDone_Zd_Data_List.size()!=0){
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(zj_Report_OtherDone_Zd_Data_List,OutExcleDataFileNew,OutExcleDataFileNew,3,zj_Report_OtherDone_Zd_Data);
                }else{
                    //处理时间
                    DealExcle.cpoyToExcleSingle("暂无ZD数据",OutExcleDataFileNew,OutExcleDataFileNew, 3);
                }

                //读取附件并且发送
                DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

            }

            sqlSessionDealData.close();

        //}

    }


}
