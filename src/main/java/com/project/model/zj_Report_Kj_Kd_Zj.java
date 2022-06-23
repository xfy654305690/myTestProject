package com.project.model;

public class zj_Report_Kj_Kd_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //融合新增数量
    public  Double  bb_Amt;
    //融合WIFI数量
    public  Integer  bb_Kj_Num;
    //宽带新增千兆占比
    public  Double  kj_Rate;
    //千兆缺口
    public  Integer  kj_Gap;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Double getBb_Amt() {
        return bb_Amt;
    }

    public void setBb_Amt(Double bb_Amt) {
        this.bb_Amt = bb_Amt;
    }

    public Integer getBb_Kj_Num() {
        return bb_Kj_Num;
    }

    public void setBb_Kj_Num(Integer bb_Kj_Num) {
        this.bb_Kj_Num = bb_Kj_Num;
    }

    public Double getKj_Rate() {
        return kj_Rate;
    }

    public void setKj_Rate(Double kj_Rate) {
        this.kj_Rate = kj_Rate;
    }

    public Integer getKj_Gap() {
        return kj_Gap;
    }

    public void setKj_Gap(Integer kj_Gap) {
        this.kj_Gap = kj_Gap;
    }

    @Override
    public String toString() {
        return "zj_Report_Kj_Kd_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_Amt=" + bb_Amt +
                ", bb_Kj_Num=" + bb_Kj_Num +
                ", kj_Rate=" + kj_Rate +
                ", kj_Gap=" + kj_Gap +
                '}';
    }
}
