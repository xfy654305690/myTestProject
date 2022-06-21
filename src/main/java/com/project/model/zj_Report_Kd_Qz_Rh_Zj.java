package com.project.model;

public class zj_Report_Kd_Qz_Rh_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //宽带新增数量
    public  Double  rh_Amt;
    //宽带新增日均数量
    public  Double  bb_Amt_Avg;
    //宽带千兆数量
    public  Integer  rh_Qz_Num;
    //宽带千兆指标
    public  Integer  rh_Qz_Tar;
    //千兆日均指标
    public  Double  rh_Qz_Avg_Tar;
    //宽带新增千兆占比
    public  Double  Qz_Rate;
    //千兆缺口
    public  Integer  Qz_Gap;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Double getRh_Amt() {
        return rh_Amt;
    }

    public void setRh_Amt(Double rh_Amt) {
        this.rh_Amt = rh_Amt;
    }

    public Double getBb_Amt_Avg() {
        return bb_Amt_Avg;
    }

    public void setBb_Amt_Avg(Double bb_Amt_Avg) {
        this.bb_Amt_Avg = bb_Amt_Avg;
    }

    public Integer getRh_Qz_Num() {
        return rh_Qz_Num;
    }

    public void setRh_Qz_Num(Integer rh_Qz_Num) {
        this.rh_Qz_Num = rh_Qz_Num;
    }

    public Integer getRh_Qz_Tar() {
        return rh_Qz_Tar;
    }

    public void setRh_Qz_Tar(Integer rh_Qz_Tar) {
        this.rh_Qz_Tar = rh_Qz_Tar;
    }

    public Double getRh_Qz_Avg_Tar() {
        return rh_Qz_Avg_Tar;
    }

    public void setRh_Qz_Avg_Tar(Double rh_Qz_Avg_Tar) {
        this.rh_Qz_Avg_Tar = rh_Qz_Avg_Tar;
    }

    public Double getQz_Rate() {
        return Qz_Rate;
    }

    public void setQz_Rate(Double qz_Rate) {
        Qz_Rate = qz_Rate;
    }

    public Integer getQz_Gap() {
        return Qz_Gap;
    }

    public void setQz_Gap(Integer qz_Gap) {
        Qz_Gap = qz_Gap;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Qz_Rh_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", rh_Amt=" + rh_Amt +
                ", bb_Amt_Avg=" + bb_Amt_Avg +
                ", rh_Qz_Num=" + rh_Qz_Num +
                ", rh_Qz_Tar=" + rh_Qz_Tar +
                ", rh_Qz_Avg_Tar=" + rh_Qz_Avg_Tar +
                ", Qz_Rate=" + Qz_Rate +
                ", Qz_Gap=" + Qz_Gap +
                '}';
    }
}
