package com.project.model;

public class zj_Report_Kd_Qz_Jz_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //千兆当前数量
    public  Integer  Now_Qz;
    //千兆之前数量
    public  Integer  Old_Qz;
    //千兆净增数量
    public  Integer  Qz_Num;
    //千兆指标
    public  Integer  Zj_Kd_Qz_Jz_Tar;
    //千兆日均指标
    public  Double  Zj_Kd_Qz_Jz_Avg_Tar;
    //千兆缺口
    public  Integer  Qz_Gap;
    //千兆日均缺口
    public  Double  Qz_Avg_Gap;
    //千兆日均完成率
    public  Double  Qz_Rate;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getNow_Qz() {
        return Now_Qz;
    }

    public void setNow_Qz(Integer now_Qz) {
        Now_Qz = now_Qz;
    }

    public Integer getOld_Qz() {
        return Old_Qz;
    }

    public void setOld_Qz(Integer old_Qz) {
        Old_Qz = old_Qz;
    }

    public Integer getQz_Num() {
        return Qz_Num;
    }

    public void setQz_Num(Integer qz_Num) {
        Qz_Num = qz_Num;
    }

    public Integer getZj_Kd_Qz_Jz_Tar() {
        return Zj_Kd_Qz_Jz_Tar;
    }

    public void setZj_Kd_Qz_Jz_Tar(Integer zj_Kd_Qz_Jz_Tar) {
        Zj_Kd_Qz_Jz_Tar = zj_Kd_Qz_Jz_Tar;
    }

    public Double getZj_Kd_Qz_Jz_Avg_Tar() {
        return Zj_Kd_Qz_Jz_Avg_Tar;
    }

    public void setZj_Kd_Qz_Jz_Avg_Tar(Double zj_Kd_Qz_Jz_Avg_Tar) {
        Zj_Kd_Qz_Jz_Avg_Tar = zj_Kd_Qz_Jz_Avg_Tar;
    }

    public Integer getQz_Gap() {
        return Qz_Gap;
    }

    public void setQz_Gap(Integer qz_Gap) {
        Qz_Gap = qz_Gap;
    }

    public Double getQz_Avg_Gap() {
        return Qz_Avg_Gap;
    }

    public void setQz_Avg_Gap(Double qz_Avg_Gap) {
        Qz_Avg_Gap = qz_Avg_Gap;
    }

    public Double getQz_Rate() {
        return Qz_Rate;
    }

    public void setQz_Rate(Double qz_Rate) {
        Qz_Rate = qz_Rate;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Qz_Jz_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", Now_Qz=" + Now_Qz +
                ", Old_Qz=" + Old_Qz +
                ", Qz_Num=" + Qz_Num +
                ", Zj_Kd_Qz_Jz_Tar=" + Zj_Kd_Qz_Jz_Tar +
                ", Zj_Kd_Qz_Jz_Avg_Tar=" + Zj_Kd_Qz_Jz_Avg_Tar +
                ", Qz_Gap=" + Qz_Gap +
                ", Qz_Avg_Gap=" + Qz_Avg_Gap +
                ", Qz_Rate=" + Qz_Rate +
                '}';
    }
}
