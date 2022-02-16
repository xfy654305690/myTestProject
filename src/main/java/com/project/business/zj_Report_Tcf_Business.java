package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_TcfDao;
import com.project.view.zj_Report_XubaoDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
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
    public static  final  String wechartSendName="aiaiai";
    public static  final  String wechartPictureAdress="D:\\test\\Tcf\\";
    //微信群名称
    public static  final  String inExcleDataFile="D:\\Test\\Tcf\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="D:\\Test\\Tcf\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="D:\\Test\\Tcf\\SOUCE\\";

    //取数导出excle
    public static void report_Tcf_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_TcfDao Zj_Report_TcfDao = sqlSession.getMapper(zj_Report_TcfDao.class);

        dealTime dealTime=new dealTime();

        //获取下月一号，返回日期格式
        Date startDate=dealTime.get_nowMonth_FirstDay_ByDate();
        //获取下月一号，返回日期格式
        Date endDate=dealTime.get_nowMonth_LastDay_ByDate();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //调测费数据-KD
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_KDList_Zj =
                Zj_Report_TcfDao.selectZj_Report_Tcf_Kd_Zj(startDate,endDate);

//        zj_Report_Tcf_KDList_Zj.forEach(zj_Report_Tcf_Zj->{
//            System.out.println(zj_Report_Tcf_Zj.toString()+"/n");
//        });

        //调测费数据-ITV
        List<zj_Report_Tcf_Zj> zj_Report_Tcf_ItvList_Zj =
                Zj_Report_TcfDao.selectZj_Report_Tcf_Itv_Zj(startDate,endDate);

        zj_Report_Tcf_ItvList_Zj.forEach(zj_Report_Tcf_Zj->{
            System.out.println(zj_Report_Tcf_Zj.toString()+"/n");
        });

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
        String OutExcleSouceFilenew =OutExcleSouceFile+"调测费"+nowDayYYYYMMDD+".xlsx";
        DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
        System.out.println("复制文件成功成功");
        String OutPictureFileNew=OutPictureFile+"picture"+nowDayYYYYMMDD+".png";

        //将exlce处理成图片
        DealExcle.excleToPng(inPictureFile,OutPictureFileNew);

        System.out.println("图片转化成功");

        //将图片发送微信
        // 1是文字，2是图片
        //DealSendMessage.searchMyFriendAndSend(wechartSendName,2,OutPictureFileNew);

        System.out.println("发送微信成功");

        //文字后续在加，不急，预留

        //获取支局长邮箱地址
        List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();

        String title ="调测费通报"+nowDayYYYYMMDD+"详见附件";
        String content="调测费通报"+nowDayYYYYMMDD+"详见附件";

        //邮件发送附件图片*****************************
        //DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,OutPictureFileNew);

        System.out.println("邮件发送成功");

        //发送数据给支局长 *********这里乱码没有结解决
        //if (nowDay.equals("5")||nowDay.equals("10")||nowDay.equals("15")||nowDay.equals("20")||nowDay.equals("25")){
        if (0>1){

            InputStream inDealData= Resources.getResourceAsStream(config);
            SqlSessionFactoryBuilder builderDealData=new SqlSessionFactoryBuilder();
            SqlSessionFactory factoryDealData = builderDealData.build(inDealData);
            SqlSession sqlSessionDealData=factoryDealData.openSession();
            zj_Report_TcfDao Zj_Report_TcfDaoDealData = sqlSessionDealData.getMapper(zj_Report_TcfDao.class);

            for (int i=0;i<zj_Report_Public_List.size();i++){

                //System.out.println(zj_Report_Public_List.get(i).getZj_Abbr_Name());

                zj_Report_Tcf_Kd_Data Zj_Report_Tcf_Kd_Data=new zj_Report_Tcf_Kd_Data();


                List<zj_Report_Tcf_Kd_Data> zj_Report_Tcf_Kd_Data_List =
                        Zj_Report_TcfDaoDealData.selectZj_Report_Tcf_Kd_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

                String  OutExcleDataFileNew;
                String titleMailSingle ;
                String contentMailSingle;

                if(zj_Report_Tcf_Kd_Data_List.size()!=0){
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"调测费宽带未收数据详见附件";
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"调测费宽带未收数据详见附件";
                    OutExcleDataFileNew=OutExcleDataFile+str+"KD"+nowDayYYYYMMDD+".xlsx";
                    System.out.printf(OutExcleDataFileNew);
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(zj_Report_Tcf_Kd_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Tcf_Kd_Data);
                    System.out.printf("复制成功");
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);

                }else{
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本月暂无宽带未收调测费清单";
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本月暂无宽带未收调测费清单";
                    OutExcleDataFileNew=null;
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
                }

            }

            for (int i=0;i<zj_Report_Public_List.size();i++){

                //System.out.println(zj_Report_Public_List.get(i).getZj_Abbr_Name());

                zj_Report_Tcf_Itv_Data Zj_Report_Tcf_Itv_Data=new zj_Report_Tcf_Itv_Data();

                //续包县份数据
                List<zj_Report_Tcf_Itv_Data> zj_Report_Tcf_Itv_Data_List =
                        Zj_Report_TcfDaoDealData.selectZj_Report_Tcf_Itv_Data(startDate,endDate,zj_Report_Public_List.get(i).getZj_Abbr_Name());

                String str = new String(zj_Report_Public_List.get(i).getZj_Full_Name().getBytes(),"UTF-8");

                String  OutExcleDataFileNew;
                String titleMailSingle ;
                String contentMailSingle;

                if(zj_Report_Tcf_Itv_Data_List.size()!=0) {
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"调测费ITV未收数据详见附件";
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"调测费ITV未收数据详见附件";
                    OutExcleDataFileNew=OutExcleDataFile+str+"KD"+nowDayYYYYMMDD+".xlsx";
                    //复制值,并且另存为
                    DealExcle.cpoyToExcle(zj_Report_Tcf_Itv_Data_List,null,OutExcleDataFileNew,0,Zj_Report_Tcf_Itv_Data);
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
                }else{
                    titleMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本月暂无ITV未收调测费清单";
                    contentMailSingle=zj_Report_Public_List.get(i).getZj_Full_Name()+"本月暂无ITV未收调测费清单";
                    OutExcleDataFileNew=null;
                    //读取附件并且发送
                    DealEmail.ctreatMailSingle(zj_Report_Public_List.get(i),null,null,titleMailSingle,contentMailSingle,OutExcleDataFileNew);
                }
            }

            sqlSession.close();

        }

    }

    //处理支局奖扣
    public static List<zj_Report_Tcf_Zj> report_Tcf_Zj_DoDetail( List<zj_Report_Tcf_Zj> zj_Report_Tcf_List_Zj)  {

        zj_Report_Tcf_List_Zj.forEach(zj_Report_Tcf_Zj->{
            System.out.println(zj_Report_Tcf_Zj.toString()+"/n");
        });

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


//        zj_Report_Tcf_List_Zj.forEach((e) -> {
//
//            if (e.getZj_Name().equals("合计")){
//             //计算缺口
//             e.setTcf_amt_gap((int) Math.ceil(e.getTcf_Amt()*(0.9)-e.getTcf_amt_cha()));
//
//            }
//            //计算缺口
//            e.setTcf_amt_gap((int) Math.ceil(e.getTcf_Amt()*(0.95)-e.getTcf_amt_cha()));
//            //计算扣罚
//            if(e.getTcf_amt_rate()<0.95){
//                e.setReward(e.getTcf_amt_gap()*50);
//            }
//        });

        return zj_Report_Tcf_List_Zj;

    }

}