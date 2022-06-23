package com.project.model;

public class zj_Report_Znzw_Kd_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //融合新增数量
    public  Double  bb_Amt;
    //融合WIFI数量
    public  Integer  bb_Znzw_Num;
    //宽带新增千兆占比
    public  Double  znzw_Rate;
    //千兆缺口
    public  Integer  znzw_Gap;

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

    public Integer getBb_Znzw_Num() {
        return bb_Znzw_Num;
    }

    public void setBb_Znzw_Num(Integer bb_Znzw_Num) {
        this.bb_Znzw_Num = bb_Znzw_Num;
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
        return "zj_Report_Znzw_Kd_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_Amt=" + bb_Amt +
                ", bb_Znzw_Num=" + bb_Znzw_Num +
                ", znzw_Rate=" + znzw_Rate +
                ", znzw_Gap=" + znzw_Gap +
                '}';
    }
}
