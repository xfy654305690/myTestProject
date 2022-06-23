package com.project.model;

public class zj_Report_Kj_New_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //看家数量
    public  Double kj_Amt;
    //看家新增日均数量
    public  Double  kj_Amt_Avg;
    //看家新增指标
    public  Integer  kj_Amt_Tar;
    //看家新增日均指标
    public  Double  kj_Amt_Avg_Tar;
    //看家新增缺口
    public  Integer  kj_Amt_Gap;
    //看家新增日均缺口
    public  Double  kj_Amt_Avg_Gap;
    //看家新增日均完成率
    public  Double  kj_Amt_Rate;
    //看家公众版新增指标(非明厨亮灶)
    public  Integer  kj_Pub_Amt;
    //看家乡镇版
    public  Integer  kj_Pub_Xzb_Amt;
    //看家公众版占比
    public  Double  kj_Pub_Rate;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Double getKj_Amt() {
        return kj_Amt;
    }

    public void setKj_Amt(Double kj_Amt) {
        this.kj_Amt = kj_Amt;
    }

    public Double getKj_Amt_Avg() {
        return kj_Amt_Avg;
    }

    public void setKj_Amt_Avg(Double kj_Amt_Avg) {
        this.kj_Amt_Avg = kj_Amt_Avg;
    }

    public Integer getKj_Amt_Tar() {
        return kj_Amt_Tar;
    }

    public void setKj_Amt_Tar(Integer kj_Amt_Tar) {
        this.kj_Amt_Tar = kj_Amt_Tar;
    }

    public Double getKj_Amt_Avg_Tar() {
        return kj_Amt_Avg_Tar;
    }

    public void setKj_Amt_Avg_Tar(Double kj_Amt_Avg_Tar) {
        this.kj_Amt_Avg_Tar = kj_Amt_Avg_Tar;
    }

    public Integer getKj_Amt_Gap() {
        return kj_Amt_Gap;
    }

    public void setKj_Amt_Gap(Integer kj_Amt_Gap) {
        this.kj_Amt_Gap = kj_Amt_Gap;
    }

    public Double getKj_Amt_Avg_Gap() {
        return kj_Amt_Avg_Gap;
    }

    public void setKj_Amt_Avg_Gap(Double kj_Amt_Avg_Gap) {
        this.kj_Amt_Avg_Gap = kj_Amt_Avg_Gap;
    }

    public Double getKj_Amt_Rate() {
        return kj_Amt_Rate;
    }

    public void setKj_Amt_Rate(Double kj_Amt_Rate) {
        this.kj_Amt_Rate = kj_Amt_Rate;
    }

    public Integer getKj_Pub_Amt() {
        return kj_Pub_Amt;
    }

    public void setKj_Pub_Amt(Integer kj_Pub_Amt) {
        this.kj_Pub_Amt = kj_Pub_Amt;
    }

    public Double getKj_Pub_Rate() {
        return kj_Pub_Rate;
    }

    public void setKj_Pub_Rate(Double kj_Pub_Rate) {
        this.kj_Pub_Rate = kj_Pub_Rate;
    }

    public Integer getKj_Pub_Xzb_Amt() {
        return kj_Pub_Xzb_Amt;
    }

    public void setKj_Pub_Xzb_Amt(Integer kj_Pub_Xzb_Amt) {
        this.kj_Pub_Xzb_Amt = kj_Pub_Xzb_Amt;
    }

    @Override
    public String toString() {
        return "zj_Report_Kj_New_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", kj_Amt=" + kj_Amt +
                ", kj_Amt_Avg=" + kj_Amt_Avg +
                ", kj_Amt_Tar=" + kj_Amt_Tar +
                ", kj_Amt_Avg_Tar=" + kj_Amt_Avg_Tar +
                ", kj_Amt_Gap=" + kj_Amt_Gap +
                ", kj_Amt_Avg_Gap=" + kj_Amt_Avg_Gap +
                ", kj_Amt_Rate=" + kj_Amt_Rate +
                ", kj_Pub_Amt=" + kj_Pub_Amt +
                ", kj_Pub_Xzb_Amt=" + kj_Pub_Xzb_Amt +
                ", kj_Pub_Rate=" + kj_Pub_Rate +
                '}';
    }
}
