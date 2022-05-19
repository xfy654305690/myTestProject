package com.project.model;

public class zj_Report_Kd_Qz_Temp_Gis {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //千兆当前数量
    public  Integer  bb_amt;
    //千兆之前数量
    public  Integer  bb_Qz_Num;
    //千兆完成率
    public  Double  bb_Qz_Rate;

    public  Integer  XGPON_Num;

    public  Double  XGPON_Rate;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getBb_amt() {
        return bb_amt;
    }

    public void setBb_amt(Integer bb_amt) {
        this.bb_amt = bb_amt;
    }

    public Integer getBb_Qz_Num() {
        return bb_Qz_Num;
    }

    public void setBb_Qz_Num(Integer bb_Qz_Num) {
        this.bb_Qz_Num = bb_Qz_Num;
    }

    public Double getBb_Qz_Rate() {
        return bb_Qz_Rate;
    }

    public void setBb_Qz_Rate(Double bb_Qz_Rate) {
        this.bb_Qz_Rate = bb_Qz_Rate;
    }

    public Integer getXGPON_Num() {
        return XGPON_Num;
    }

    public void setXGPON_Num(Integer XGPON_Num) {
        this.XGPON_Num = XGPON_Num;
    }

    public Double getXGPON_Rate() {
        return XGPON_Rate;
    }

    public void setXGPON_Rate(Double XGPON_Rate) {
        this.XGPON_Rate = XGPON_Rate;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Qz_Temp_Gis{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_amt=" + bb_amt +
                ", bb_Qz_Num=" + bb_Qz_Num +
                ", bb_Qz_Rate=" + bb_Qz_Rate +
                ", XGPON_Num=" + XGPON_Num +
                ", XGPON_Rate=" + XGPON_Rate +
                '}';
    }

}
