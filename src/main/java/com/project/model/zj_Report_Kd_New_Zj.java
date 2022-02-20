package com.project.model;

public class zj_Report_Kd_New_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //宽带新增数量
    public  Double bb_Amt;
    //宽带新增日均数量
    public  Double  bb_Amt_Avg;
    //宽带新增指标
    public  Integer  bb_Amt_Tar;
    //宽带新增日均指标
    public  Double  bb_Amt_Avg_Tar;
    //宽带新增缺口
    public  Integer  bb_Amt_Gap;
    //宽带新增日均缺口
    public  Double  bb_Amt_Avg_Gap;
    //宽带新增日均完成率
    public  Double  bb_Amt_Rate;

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

    public Double getBb_Amt_Avg() {
        return bb_Amt_Avg;
    }

    public void setBb_Amt_Avg(Double bb_Amt_Avg) {
        this.bb_Amt_Avg = bb_Amt_Avg;
    }

    public Integer getBb_Amt_Tar() {
        return bb_Amt_Tar;
    }

    public void setBb_Amt_Tar(Integer bb_Amt_Tar) {
        this.bb_Amt_Tar = bb_Amt_Tar;
    }

    public Double getBb_Amt_Avg_Tar() {
        return bb_Amt_Avg_Tar;
    }

    public void setBb_Amt_Avg_Tar(Double bb_Amt_Avg_Tar) {
        this.bb_Amt_Avg_Tar = bb_Amt_Avg_Tar;
    }

    public Integer getBb_Amt_Gap() {
        return bb_Amt_Gap;
    }

    public void setBb_Amt_Gap(Integer bb_Amt_Gap) {
        this.bb_Amt_Gap = bb_Amt_Gap;
    }

    public Double getBb_Amt_Avg_Gap() {
        return bb_Amt_Avg_Gap;
    }

    public void setBb_Amt_Avg_Gap(Double bb_Amt_Avg_Gap) {
        this.bb_Amt_Avg_Gap = bb_Amt_Avg_Gap;
    }

    public Double getBb_Amt_Rate() {
        return bb_Amt_Rate;
    }

    public void setBb_Amt_Rate(Double bb_Amt_Rate) {
        this.bb_Amt_Rate = bb_Amt_Rate;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_New_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_Amt=" + bb_Amt +
                ", bb_Amt_Avg=" + bb_Amt_Avg +
                ", bb_Amt_Tar=" + bb_Amt_Tar +
                ", bb_Amt_Avg_Tar=" + bb_Amt_Avg_Tar +
                ", bb_Amt_Gap=" + bb_Amt_Gap +
                ", bb_Amt_Avg_Gap=" + bb_Amt_Avg_Gap +
                ", bb_Amt_Rate=" + bb_Amt_Rate +
                '}';
    }

}
