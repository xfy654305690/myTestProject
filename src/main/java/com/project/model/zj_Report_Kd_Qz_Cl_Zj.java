package com.project.model;

public class zj_Report_Kd_Qz_Cl_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //宽带新增数量
    public  Integer  cl_Amt;
    //宽带新增日均数量
    public  Double  cl_Amt_Avg;
    //宽带千兆指标
    public  Integer  cl_Qz_Tar;
    //千兆日均指标
    public  Double  cl_Qz_Avg_Tar;
    //千兆缺口
    public  Integer  cl_Gap;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getCl_Amt() {
        return cl_Amt;
    }

    public void setCl_Amt(Integer cl_Amt) {
        this.cl_Amt = cl_Amt;
    }

    public Integer getCl_Qz_Tar() {
        return cl_Qz_Tar;
    }

    public void setCl_Qz_Tar(Integer cl_Qz_Tar) {
        this.cl_Qz_Tar = cl_Qz_Tar;
    }

    public Double getCl_Qz_Avg_Tar() {
        return cl_Qz_Avg_Tar;
    }

    public void setCl_Qz_Avg_Tar(Double cl_Qz_Avg_Tar) {
        this.cl_Qz_Avg_Tar = cl_Qz_Avg_Tar;
    }

    public Integer getCl_Gap() {
        return cl_Gap;
    }

    public void setCl_Gap(Integer cl_Gap) {
        this.cl_Gap = cl_Gap;
    }

    public Double getCl_Amt_Avg() {
        return cl_Amt_Avg;
    }

    public void setCl_Amt_Avg(Double cl_Amt_Avg) {
        this.cl_Amt_Avg = cl_Amt_Avg;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Qz_Cl_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", cl_Amt=" + cl_Amt +
                ", cl_Amt_Avg=" + cl_Amt_Avg +
                ", cl_Qz_Tar=" + cl_Qz_Tar +
                ", cl_Qz_Avg_Tar=" + cl_Qz_Avg_Tar +
                ", cl_Gap=" + cl_Gap +
                '}';
    }
}
