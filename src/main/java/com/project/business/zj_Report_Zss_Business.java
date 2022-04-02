package com.project.business;

import com.project.model.*;
import com.project.util.dealEmail;
import com.project.util.dealExcle;
import com.project.util.dealSendMessage;
import com.project.util.dealTime;
import com.project.view.zj_Report_ZssDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class zj_Report_Zss_Business {

    public static  final  String config="mybatis.xml";
    //读取EXCLE目录
    public static  final  String inExcleFile="C:\\Test\\ZSS\\test.xlsx";
    //输出EXCLE目录
    public static  final  String OutExcleFile="C:\\Test\\ZSS\\test.xlsx";
    //转化图片源文件
    public static  final  String inPictureFile="C:\\Test\\ZSS\\test.xlsx";
    //图片地址跟目录
    public static  final  String OutPictureFile="C:\\test\\ZSS\\PICTURE\\";
    //微信群名称
    public static  final  String wechartSendName="春季营销冲锋行动（鄞战2022）";
    public static  final  String wechartPictureAdress="C:\\test\\Tcf\\";
    //微信群名称
    public static  final  String inExcleDataFile="C:\\Test\\ZSS\\test.xlsx";
    //导出数据地址
    public static  final  String OutExcleDataFile="C:\\Test\\ZSS\\DATA\\";
    //复制导出文件地址
    public static  final  String OutExcleSouceFile="C:\\Test\\ZSS\\SOUCE\\";
    //复制导出文件地址
    public static  final  String OutExcleAccountsFile="C:\\Test\\ZSS\\ACCOUNT\\";

    //取数导出excle
    public static void report_Zss_Zj() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MessagingException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_ZssDao zj_Report_ZssDao = sqlSession.getMapper(zj_Report_ZssDao.class);

        dealTime dealTime=new dealTime();

        //获取当前季度一号，返回日期格式
        Date startDate=dealTime.get_date_ByNowDate_MinTime();
        //获取当前季度最后一号，返回日期格式
        Date endDate=dealTime.get_date_ByNowDate_MaxTime();

        //获取当前日期DD格式
        String nowDayYYYYMMDD=dealTime.get_date_By_String_YYYYMMDD();

        //ZJ
        List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj =
                zj_Report_ZssDao.selectZj_Report_Zss_Zj(startDate,endDate);

        //TX
        List<zj_Report_Zss_Zj> zj_Report_Zss_List_Tx =
                zj_Report_ZssDao.selectZj_Report_Zss_Tx(startDate,endDate);

        //LEADER
        List<zj_Report_Zss_Zj> zj_Report_Zss_List_Leader =
                zj_Report_ZssDao.selectZj_Report_Zss_Leader(startDate,endDate);

        //系统时间
        Date maxDate=zj_Report_ZssDao.selectZj_Report_Zss_MaxTime();
        SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String maxDateStringYYYYMMDDHHDDSS=simpleDateFormatYMD.format(maxDate.getTime());

        sqlSession.close();

        zj_Report_Zss_Zj zj_Report_Zss_Zj=new zj_Report_Zss_Zj();

        dealExcle DealExcle =new dealExcle();
        dealSendMessage DealSendMessage=new dealSendMessage();

        SimpleDateFormat simpleDateFormatYM = new SimpleDateFormat("yyyyMMdd");//注意月份是MM
        Date nowDate = new Date();// 获取当前时间
        String maxDateStringYYYYMMDD  =simpleDateFormatYM.format(maxDate);
        String nowDateString  =simpleDateFormatYM.format(nowDate);
        if(!nowDateString.equals(maxDateStringYYYYMMDD)){
            DealSendMessage.searchMyFriendAndSend(wechartSendName,1,"准实时暂未更新至今日");
            return;
        }
        if(nowDateString.equals(maxDateStringYYYYMMDD)){

            //处理支局奖扣
            List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj_DealZj=report_Zss_Zj_DoDetail_Zj(zj_Report_Zss_List_Zj);
            //处理支局奖扣
            List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj_DealTx=report_Zss_Zj_DoDetail_Tx(zj_Report_Zss_List_Tx);
            //处理支局奖扣
            List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj_DealLeader=report_Zss_Zj_DoDetail_Leader(zj_Report_Zss_List_Leader);

            //准实时 1
            DealExcle.cpoyToExcle(zj_Report_Zss_List_Zj_DealZj,inExcleFile,OutExcleFile,1,zj_Report_Zss_Zj);
            //准实时 2
            DealExcle.cpoyToExcle(zj_Report_Zss_List_Zj_DealTx,inExcleFile,OutExcleFile,2,zj_Report_Zss_Zj);
            //准实时 3
            DealExcle.cpoyToExcle(zj_Report_Zss_List_Zj_DealLeader,inExcleFile,OutExcleFile,3,zj_Report_Zss_Zj);
            //处理时间
            DealExcle.cpoyToExcleSingle(maxDateStringYYYYMMDDHHDDSS,inExcleFile,OutExcleFile, 4);

            System.out.println("数据处理成功");
            //复制文件
            String OutExcleSouceFilenew =OutExcleSouceFile+"准实时"+nowDayYYYYMMDD+".xlsx";
            DealExcle.copyExcleToOtherExcle(OutExcleFile,OutExcleSouceFilenew);
            System.out.println("复制文件成功成功");
            String OutPictureFileNew=OutPictureFile+"picture"+nowDayYYYYMMDD+".png";

            //将exlce处理成图片
            DealExcle.excleToPng(inPictureFile,OutPictureFileNew);
            System.out.println("图片转化成功");

            //将图片发送微信
            DealSendMessage.searchMyFriendAndSend(wechartSendName,2,OutPictureFileNew);
            System.out.println("发送微信成功");

            //文字后续在加，不急，预留
            String context=report_Zss_DoDetail_Context(zj_Report_Zss_List_Zj_DealZj);
            DealSendMessage.searchMyFriendAndSend(wechartSendName,1,context);
            //获取支局长邮箱地址
//            List<zj_Report_Public> zj_Report_Public_List =zj_Report_Public_Business.zj_Report_Public_Business();
//            String title ="准实时通报"+nowDayYYYYMMDD+"详见附件";
//            String content="准实时通报"+nowDayYYYYMMDD+"详见附件";

            //邮件发送附件图片*****************************
            //DealEmail.ctreatMailMore(zj_Report_Public_List,null,null,title,content,OutPictureFileNew);

            //System.out.println("邮件发送成功");

        }

    }

    //处理支局奖扣
    public static List<zj_Report_Zss_Zj> report_Zss_Zj_DoDetail_Zj(List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj)  {
        //处理完成率
        for(int i=0;i<zj_Report_Zss_List_Zj.size();i++){
            zj_Report_Zss_List_Zj.get(i).setCdma_Amt_Rate(zj_Report_Zss_List_Zj.get(i).getCdmaNew()/zj_Report_Zss_List_Zj.get(i).getCdry_Cdma_Target());
            zj_Report_Zss_List_Zj.get(i).setBb_Amt_Rate(zj_Report_Zss_List_Zj.get(i).getBbNew()/zj_Report_Zss_List_Zj.get(i).getCdry_Bb_Target());
            zj_Report_Zss_List_Zj.get(i).setRh_Amt_Rate(zj_Report_Zss_List_Zj.get(i).getRhNew()/zj_Report_Zss_List_Zj.get(i).getCdry_Rh_Target());
            zj_Report_Zss_List_Zj.get(i).setGt_Amt_Rate(zj_Report_Zss_List_Zj.get(i).getGtNew()/zj_Report_Zss_List_Zj.get(i).getCdry_Gt_Target());
            zj_Report_Zss_List_Zj.get(i).setZd_Amt_Rate(zj_Report_Zss_List_Zj.get(i).getZdNew()/zj_Report_Zss_List_Zj.get(i).getCdry_Zd_Target());
            zj_Report_Zss_List_Zj.get(i).setCom_Rate(zj_Report_Zss_List_Zj.get(i).getCdma_Amt_Rate()*0.2+zj_Report_Zss_List_Zj.get(i).getBb_Amt_Rate()*0.3
            +zj_Report_Zss_List_Zj.get(i).getGt_Amt_Rate()*0.3+zj_Report_Zss_List_Zj.get(i).getZd_Amt_Rate()*0.2);

        }
        List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj_DealA =new ArrayList<>() ;
        List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj_DealB =new ArrayList<>();
        List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj_DealN =new ArrayList<>();
        List<zj_Report_Zss_Zj>  zj_Report_Zss_Zj_Heji=new ArrayList<>();

        //把城市A,城市B,农村,合计分开
        for(int i=0;i<zj_Report_Zss_List_Zj.size();i++){
            if(zj_Report_Zss_List_Zj.get(i).getZj_Property().equals("城市A")){
                zj_Report_Zss_List_Zj_DealA.add(zj_Report_Zss_List_Zj.get(i));
            }
            if(zj_Report_Zss_List_Zj.get(i).getZj_Property().equals("城市B")){
                zj_Report_Zss_List_Zj_DealB.add(zj_Report_Zss_List_Zj.get(i));
            }
            if(zj_Report_Zss_List_Zj.get(i).getZj_Property().equals("农村")){
                zj_Report_Zss_List_Zj_DealN.add(zj_Report_Zss_List_Zj.get(i));
            }
            if(zj_Report_Zss_List_Zj.get(i).getZj_Name().equals("合计")){
                zj_Report_Zss_Zj_Heji.add(zj_Report_Zss_List_Zj.get(i));
            }
        }
        //城市A,城市B,农村排名分开
        for(int i=0;i<zj_Report_Zss_List_Zj_DealA.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<zj_Report_Zss_List_Zj_DealA.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(zj_Report_Zss_List_Zj_DealA.get(j).getCom_Rate() > zj_Report_Zss_List_Zj_DealA.get(j + 1).getCom_Rate()) {
                    zj_Report_Zss_Zj temp= zj_Report_Zss_List_Zj_DealA.get(j);
                    zj_Report_Zss_List_Zj_DealA.set(j, zj_Report_Zss_List_Zj_DealA.get(j + 1));zj_Report_Zss_List_Zj_DealA.set(j + 1, temp);
                }
            }
        }
        for(int i=0;i<zj_Report_Zss_List_Zj_DealB.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<zj_Report_Zss_List_Zj_DealB.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(zj_Report_Zss_List_Zj_DealB.get(j).getCom_Rate() > zj_Report_Zss_List_Zj_DealB.get(j + 1).getCom_Rate()) {
                    zj_Report_Zss_Zj temp= zj_Report_Zss_List_Zj_DealB.get(j);
                    zj_Report_Zss_List_Zj_DealB.set(j, zj_Report_Zss_List_Zj_DealB.get(j + 1));zj_Report_Zss_List_Zj_DealB.set(j + 1, temp);
                }
            }
        }
        for(int i=0;i<zj_Report_Zss_List_Zj_DealN.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<zj_Report_Zss_List_Zj_DealN.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(zj_Report_Zss_List_Zj_DealN.get(j).getCom_Rate() > zj_Report_Zss_List_Zj_DealN.get(j + 1).getCom_Rate()) {
                    zj_Report_Zss_Zj temp= zj_Report_Zss_List_Zj_DealN.get(j);
                    zj_Report_Zss_List_Zj_DealN.set(j, zj_Report_Zss_List_Zj_DealN.get(j + 1));zj_Report_Zss_List_Zj_DealN.set(j + 1, temp);
                }
            }
        }
        //设置排名后加入新的数组
        List<zj_Report_Zss_Zj> zj_Report_Zss_List_Zj_new=new ArrayList<>();
        for(int i=0;i<zj_Report_Zss_List_Zj_DealA.size();i++){//外层循环控制排序趟数
            zj_Report_Zss_List_Zj_DealA.get(i).setCom_Rank(zj_Report_Zss_List_Zj_DealA.size()-i);
            zj_Report_Zss_List_Zj_new.add(zj_Report_Zss_List_Zj_DealA.get(i));
        }
        for(int i=0;i<zj_Report_Zss_List_Zj_DealB.size();i++){//外层循环控制排序趟数
            zj_Report_Zss_List_Zj_DealB.get(i).setCom_Rank(zj_Report_Zss_List_Zj_DealB.size()-i);
            zj_Report_Zss_List_Zj_new.add(zj_Report_Zss_List_Zj_DealB.get(i));
        }
        for(int i=0;i<zj_Report_Zss_List_Zj_DealN.size();i++){//外层循环控制排序趟数
            zj_Report_Zss_List_Zj_DealN.get(i).setCom_Rank(zj_Report_Zss_List_Zj_DealN.size()-i);
            zj_Report_Zss_List_Zj_new.add(zj_Report_Zss_List_Zj_DealN.get(i));
        }
        zj_Report_Zss_List_Zj_new.add(zj_Report_Zss_Zj_Heji.get(0));

        //按照指定顺序排序
        List regulationOrder = Arrays.asList("鄞州潘火公众支局","鄞州潘火政企支局","鄞州钟公庙综合支局","鄞州长丰综合支局","鄞州新城综合支局","鄞州新城文化中心综合支局","鄞州首南综合支局","鄞州南商政企支局",
                "鄞州东郊综合支局","鄞州下应综合支局","鄞州中河城东综合支局","鄞州东柳综合支局","鄞州福明综合支局","鄞州中河城西综合支局","鄞州明楼综合支局","鄞州白鹤综合支局",
                "鄞州东胜综合支局","鄞州百丈综合支局","鄞州姜山综合支局","鄞州东钱湖综合支局","鄞州邱隘综合支局","鄞州五乡综合支局","鄞州云龙综合支局","鄞州横溪综合支局",
                "鄞州塘溪综合支局","鄞州瞻岐综合支局","鄞州东吴综合支局","鄞州咸祥综合支局","合计");
        zj_Report_Zss_List_Zj_new.sort(Comparator.comparing(e -> regulationOrder.indexOf(e.getZj_Name())));
        
        return zj_Report_Zss_List_Zj_new;

    }

    public static List<zj_Report_Zss_Zj> report_Zss_Zj_DoDetail_Tx(List<zj_Report_Zss_Zj> zj_Report_Zss_List_Tx)  {
        //处理完成率
        for(int i=0;i<zj_Report_Zss_List_Tx.size();i++){
            zj_Report_Zss_List_Tx.get(i).setCdma_Amt_Rate(zj_Report_Zss_List_Tx.get(i).getCdmaNew()/zj_Report_Zss_List_Tx.get(i).getCdry_Cdma_Target());
            zj_Report_Zss_List_Tx.get(i).setBb_Amt_Rate(zj_Report_Zss_List_Tx.get(i).getBbNew()/zj_Report_Zss_List_Tx.get(i).getCdry_Bb_Target());
            zj_Report_Zss_List_Tx.get(i).setRh_Amt_Rate(zj_Report_Zss_List_Tx.get(i).getRhNew()/zj_Report_Zss_List_Tx.get(i).getCdry_Rh_Target());
            zj_Report_Zss_List_Tx.get(i).setGt_Amt_Rate(zj_Report_Zss_List_Tx.get(i).getGtNew()/zj_Report_Zss_List_Tx.get(i).getCdry_Gt_Target());
            if(!zj_Report_Zss_List_Tx.get(i).getCdry_Zd_Target().equals(Double.valueOf(0))){
                zj_Report_Zss_List_Tx.get(i).setZd_Amt_Rate(zj_Report_Zss_List_Tx.get(i).getZdNew()/zj_Report_Zss_List_Tx.get(i).getCdry_Zd_Target());
            }
            if(zj_Report_Zss_List_Tx.get(i).getCdry_Zd_Target().equals(Double.valueOf(0))){
                if(zj_Report_Zss_List_Tx.get(i).getZdNew()==0){
                    zj_Report_Zss_List_Tx.get(i).setZd_Amt_Rate((double) 0);
                }
                if(zj_Report_Zss_List_Tx.get(i).getZdNew()!=0){
                    zj_Report_Zss_List_Tx.get(i).setZd_Amt_Rate((double) 1);
                }
            }
            zj_Report_Zss_List_Tx.get(i).setCom_Rate(zj_Report_Zss_List_Tx.get(i).getCdma_Amt_Rate()*0.2+zj_Report_Zss_List_Tx.get(i).getBb_Amt_Rate()*0.3
                    +zj_Report_Zss_List_Tx.get(i).getGt_Amt_Rate()*0.3+zj_Report_Zss_List_Tx.get(i).getZd_Amt_Rate()*0.2);

        }

        //排序
        for(int i=0;i<zj_Report_Zss_List_Tx.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<zj_Report_Zss_List_Tx.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(zj_Report_Zss_List_Tx.get(j).getCom_Rate() > zj_Report_Zss_List_Tx.get(j + 1).getCom_Rate()) {
                    zj_Report_Zss_Zj temp= zj_Report_Zss_List_Tx.get(j);
                    zj_Report_Zss_List_Tx.set(j, zj_Report_Zss_List_Tx.get(j + 1));zj_Report_Zss_List_Tx.set(j + 1, temp);
                }
            }
        }
        //加入排名
        for(int i=0;i<zj_Report_Zss_List_Tx.size();i++){
            zj_Report_Zss_List_Tx.get(i).setCom_Rank(zj_Report_Zss_List_Tx.size()-i);
        }

        //按照指定顺序排序
        List regulationOrder = Arrays.asList("支局长","支局长助理","客户经理","网格经理","营业员","智家工程师","支局其他人员");
        zj_Report_Zss_List_Tx.sort(Comparator.comparing(e -> regulationOrder.indexOf(e.getZj_Name())));

        return zj_Report_Zss_List_Tx;

    }


    public static List<zj_Report_Zss_Zj> report_Zss_Zj_DoDetail_Leader(List<zj_Report_Zss_Zj> zj_Report_Zss_List_Leader)  {
        //处理完成率
        for(int i=0;i<zj_Report_Zss_List_Leader.size();i++){
            zj_Report_Zss_List_Leader.get(i).setCdma_Amt_Rate(zj_Report_Zss_List_Leader.get(i).getCdmaNew()/zj_Report_Zss_List_Leader.get(i).getCdry_Cdma_Target());
            zj_Report_Zss_List_Leader.get(i).setBb_Amt_Rate(zj_Report_Zss_List_Leader.get(i).getBbNew()/zj_Report_Zss_List_Leader.get(i).getCdry_Bb_Target());
            zj_Report_Zss_List_Leader.get(i).setRh_Amt_Rate(zj_Report_Zss_List_Leader.get(i).getRhNew()/zj_Report_Zss_List_Leader.get(i).getCdry_Rh_Target());
            zj_Report_Zss_List_Leader.get(i).setGt_Amt_Rate(zj_Report_Zss_List_Leader.get(i).getGtNew()/zj_Report_Zss_List_Leader.get(i).getCdry_Gt_Target());
            zj_Report_Zss_List_Leader.get(i).setZd_Amt_Rate(zj_Report_Zss_List_Leader.get(i).getZdNew()/zj_Report_Zss_List_Leader.get(i).getCdry_Zd_Target());
            zj_Report_Zss_List_Leader.get(i).setCom_Rate(zj_Report_Zss_List_Leader.get(i).getCdma_Amt_Rate()*0.2+zj_Report_Zss_List_Leader.get(i).getBb_Amt_Rate()*0.3
                    +zj_Report_Zss_List_Leader.get(i).getGt_Amt_Rate()*0.3+zj_Report_Zss_List_Leader.get(i).getZd_Amt_Rate()*0.2);

        }
        //排序
        for(int i=0;i<zj_Report_Zss_List_Leader.size()-1;i++){//外层循环控制排序趟数
            for(int j=0;j<zj_Report_Zss_List_Leader.size()-1-i;j++){
                //内层循环控制每一趟排序多少次
                if(zj_Report_Zss_List_Leader.get(j).getCom_Rate() > zj_Report_Zss_List_Leader.get(j + 1).getCom_Rate()) {
                    zj_Report_Zss_Zj temp= zj_Report_Zss_List_Leader.get(j);
                    zj_Report_Zss_List_Leader.set(j, zj_Report_Zss_List_Leader.get(j + 1));zj_Report_Zss_List_Leader.set(j + 1, temp);
                }
            }
        }
        //加入排名
        for(int i=0;i<zj_Report_Zss_List_Leader.size();i++){
            zj_Report_Zss_List_Leader.get(i).setCom_Rank(zj_Report_Zss_List_Leader.size()-i);
        }

        //按照指定顺序排序
        List regulationOrder = Arrays.asList("黄红","鲁建盛","毛高松","邬云峰","陆黎达","郑培坚");
        zj_Report_Zss_List_Leader.sort(Comparator.comparing(e -> regulationOrder.indexOf(e.getZj_Name())));

        return zj_Report_Zss_List_Leader;
    }


    public static String report_Zss_DoDetail_Context( List<zj_Report_Zss_Zj> zj_Report_Zss_Zj_List)  {
        String context="";
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);
        zj_Report_Zss_Zj heji=zj_Report_Zss_Zj_List.get(zj_Report_Zss_Zj_List.size()-1);
        String A_Front="";
        String A_Behind = "";
        String B_Front= "";
        String B_Behind= "";
        String N_Front= "";
        String N_Behind= "";
        for(int i=0;i<zj_Report_Zss_Zj_List.size()-1;i++){
            if(zj_Report_Zss_Zj_List.get(i).getZj_Property().equals("城市A")&&(zj_Report_Zss_Zj_List.get(i).getCom_Rank()<=2)){
                A_Front=A_Front+zj_Report_Zss_Zj_List.get(i).getZj_Name()+",";
            }
            if(zj_Report_Zss_Zj_List.get(i).getZj_Property().equals("城市A")&&(zj_Report_Zss_Zj_List.get(i).getCom_Rank()>=8)){
                A_Behind=A_Behind+zj_Report_Zss_Zj_List.get(i).getZj_Name()+",";
            }
            if(zj_Report_Zss_Zj_List.get(i).getZj_Property().equals("城市B")&&(zj_Report_Zss_Zj_List.get(i).getCom_Rank()<=2)){
                B_Front=B_Front+zj_Report_Zss_Zj_List.get(i).getZj_Name()+",";
            }
            if(zj_Report_Zss_Zj_List.get(i).getZj_Property().equals("城市B")&&(zj_Report_Zss_Zj_List.get(i).getCom_Rank()>=6)){
                B_Behind=B_Behind+zj_Report_Zss_Zj_List.get(i).getZj_Name()+",";
            }
            if(zj_Report_Zss_Zj_List.get(i).getZj_Property().equals("农村")&&(zj_Report_Zss_Zj_List.get(i).getCom_Rank()<=3)){
                N_Front=N_Front+zj_Report_Zss_Zj_List.get(i).getZj_Name()+",";
            }
            if(zj_Report_Zss_Zj_List.get(i).getZj_Property().equals("农村")&&(zj_Report_Zss_Zj_List.get(i).getCom_Rank()>=8)){
                N_Behind=N_Behind+zj_Report_Zss_Zj_List.get(i).getZj_Name()+",";
            }
        }
        A_Front=DoStringDetail(A_Front);
        A_Behind=DoStringDetail(A_Behind);
        B_Front=DoStringDetail(B_Front);
        B_Behind=DoStringDetail(B_Behind);
        N_Front=DoStringDetail(N_Front);
        N_Behind=DoStringDetail(N_Behind);

        context="当天综合（含终端）得分:"+nf.format(heji.getCom_Rate())+"，移动:"+heji.getCdmaNew()+"户，宽带:"+heji.getBbNew()
                +"户，高套:"+heji.getGtNew()+"户、终端:"+heji.getZdNew()+"户。"+"\n"+
                "当天排名："+"\n" +"城市A前二:"+A_Front+";"+"\n"+"城市A后三:"+A_Behind+";"+"\n"+"城市B前二:"+B_Front+";"+"\n"+"城市B后三:"+B_Behind+";"+"\n"
                +"农村前三:"+N_Front+";"+"\n"+"农村后三:"+N_Behind+";"+"\n";

        return context;
    }
    public static String DoStringDetail( String s)  {
        s=s.substring(0,s.length()-1);
        s=s.replace("鄞州","");
        s=s.replace("支局","");
        s=s.replace("综合","");
        return s;
    }


}
