package com.project.model;

public class zj_Report_Kj_Rh_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //融合新增数量
    public  Double  rh_Amt;
    //融合WIFI数量
    public  Integer  rh_kj_Num;
    //WIFI占比
    public  Double  kj_Rate;
    //千兆缺口
    public  Integer  kj_Gap;

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

    public Integer getRh_kj_Num() {
        return rh_kj_Num;
    }

    public void setRh_kj_Num(Integer rh_kj_Num) {
        this.rh_kj_Num = rh_kj_Num;
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
        return "zj_Report_Kj_Rh_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", rh_Amt=" + rh_Amt +
                ", rh_kj_Num=" + rh_kj_Num +
                ", kj_Rate=" + kj_Rate +
                ", kj_Gap=" + kj_Gap +
                '}';
    }
}
