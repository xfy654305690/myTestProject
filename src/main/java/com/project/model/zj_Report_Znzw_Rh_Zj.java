package com.project.model;

public class zj_Report_Znzw_Rh_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //融合新增数量
    public  Double  rh_Amt;
    //融合WIFI数量
    public  Integer  rh_Znzw_Num;
    //WIFI占比
    public  Double  znzw_Rate;
    //千兆缺口
    public  Integer  znzw_Gap;

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

    public Integer getRh_Znzw_Num() {
        return rh_Znzw_Num;
    }

    public void setRh_Znzw_Num(Integer rh_Znzw_Num) {
        this.rh_Znzw_Num = rh_Znzw_Num;
    }

    public Double getZnzw_Rate() {
        return znzw_Rate;
    }

    public void setZnzw_Rate(Double znzw_Rate) {
        this.znzw_Rate = znzw_Rate;
    }

    public Integer getZnzw_Gap() {
        return znzw_Gap;
    }

    public void setZnzw_Gap(Integer znzw_Gap) {
        this.znzw_Gap = znzw_Gap;
    }

    @Override
    public String toString() {
        return "zj_Report_Znzw_Rh_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", rh_Amt=" + rh_Amt +
                ", rh_Znzw_Num=" + rh_Znzw_Num +
                ", znzw_Rate=" + znzw_Rate +
                ", znzw_Gap=" + znzw_Gap +
                '}';
    }
}
