package com.project.model;

public class zj_Report_Kd_Jz_Data {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //账号
    public  String DATA_USER_NAME;
    //优惠
    public  String CDSC_NAME;
    //状态
    public  String STAT_NAME;
    //资产号
    public  String ASSET_ROW_ID;
    //客户编码
    public  String CACCT_ID;
    //计费标志
    public  String BIL_FLG;
    //时间
    public  String bilTime;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public String getDATA_USER_NAME() {
        return DATA_USER_NAME;
    }

    public void setDATA_USER_NAME(String DATA_USER_NAME) {
        this.DATA_USER_NAME = DATA_USER_NAME;
    }

    public String getCDSC_NAME() {
        return CDSC_NAME;
    }

    public void setCDSC_NAME(String CDSC_NAME) {
        this.CDSC_NAME = CDSC_NAME;
    }

    public String getSTAT_NAME() {
        return STAT_NAME;
    }

    public void setSTAT_NAME(String STAT_NAME) {
        this.STAT_NAME = STAT_NAME;
    }

    public String getASSET_ROW_ID() {
        return ASSET_ROW_ID;
    }

    public void setASSET_ROW_ID(String ASSET_ROW_ID) {
        this.ASSET_ROW_ID = ASSET_ROW_ID;
    }

    public String getCACCT_ID() {
        return CACCT_ID;
    }

    public void setCACCT_ID(String CACCT_ID) {
        this.CACCT_ID = CACCT_ID;
    }

    public String getBIL_FLG() {
        return BIL_FLG;
    }

    public void setBIL_FLG(String BIL_FLG) {
        this.BIL_FLG = BIL_FLG;
    }

    public String getBilTime() {
        return bilTime;
    }

    public void setBilTime(String bilTime) {
        this.bilTime = bilTime;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Jz_Data{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", DATA_USER_NAME='" + DATA_USER_NAME + '\'' +
                ", CDSC_NAME='" + CDSC_NAME + '\'' +
                ", STAT_NAME='" + STAT_NAME + '\'' +
                ", ASSET_ROW_ID='" + ASSET_ROW_ID + '\'' +
                ", CACCT_ID='" + CACCT_ID + '\'' +
                ", BIL_FLG='" + BIL_FLG + '\'' +
                ", bilTime='" + bilTime + '\'' +
                '}';
    }
}
