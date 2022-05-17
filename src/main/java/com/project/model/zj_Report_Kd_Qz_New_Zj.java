package com.project.model;

public class zj_Report_Kd_Qz_New_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //宽带新增数量
    public  Integer  bb_Amt;
    //宽带千兆数量
    public  Integer  bb_Qz_Num;
    //宽带新增千兆占比
    public  Double  Qz_Rate;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getBb_Amt() {
        return bb_Amt;
    }

    public void setBb_Amt(Integer bb_Amt) {
        this.bb_Amt = bb_Amt;
    }

    public Integer getBb_Qz_Num() {
        return bb_Qz_Num;
    }

    public void setBb_Qz_Num(Integer bb_Qz_Num) {
        this.bb_Qz_Num = bb_Qz_Num;
    }

    public Double getQz_Rate() {
        return Qz_Rate;
    }

    public void setQz_Rate(Double qz_Rate) {
        Qz_Rate = qz_Rate;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Qz_New_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_Amt=" + bb_Amt +
                ", bb_Qz_Num=" + bb_Qz_Num +
                ", Qz_Rate=" + Qz_Rate +
                '}';
    }
}
