package com.project.model;

public class zj_Report_Public {

    //支局缩写
    public  String Zj_Full_Name;
    //支局全程
    public  String Zj_Abbr_Name;
    //支局长名称
    public  String Zj_Per_In_Cha;
    //支局长邮箱
    public  String Zj_Per_In_Cha_Ema;
    //支局属性
    public  String ZJ_PROPERTY;
    //动车组领导名字
    public  String ZJ_LEAD_NAME;

    public String getZj_Full_Name() {
        return Zj_Full_Name;
    }

    public void setZj_Full_Name(String zj_Full_Name) {
        Zj_Full_Name = zj_Full_Name;
    }

    public String getZj_Abbr_Name() {
        return Zj_Abbr_Name;
    }

    public void setZj_Abbr_Name(String zj_Abbr_Name) {
        Zj_Abbr_Name = zj_Abbr_Name;
    }

    public String getZj_Per_In_Cha() {
        return Zj_Per_In_Cha;
    }

    public void setZj_Per_In_Cha(String zj_Per_In_Cha) {
        Zj_Per_In_Cha = zj_Per_In_Cha;
    }

    public String getZj_Per_In_Cha_Ema() {
        return Zj_Per_In_Cha_Ema;
    }

    public void setZj_Per_In_Cha_Ema(String zj_Per_In_Cha_Ema) {
        Zj_Per_In_Cha_Ema = zj_Per_In_Cha_Ema;
    }


    public String getZJ_PROPERTY() {
        return ZJ_PROPERTY;
    }

    public void setZJ_PROPERTY(String ZJ_PROPERTY) {
        this.ZJ_PROPERTY = ZJ_PROPERTY;
    }

    public String getZJ_LEAD_NAME() {
        return ZJ_LEAD_NAME;
    }

    public void setZJ_LEAD_NAME(String ZJ_LEAD_NAME) {
        this.ZJ_LEAD_NAME = ZJ_LEAD_NAME;
    }

    @Override
    public String toString() {
        return "zj_Report_Public{" +
                "Zj_Full_Name='" + Zj_Full_Name + '\'' +
                ", Zj_Abbr_Name='" + Zj_Abbr_Name + '\'' +
                ", Zj_Per_In_Cha='" + Zj_Per_In_Cha + '\'' +
                ", Zj_Per_In_Cha_Ema='" + Zj_Per_In_Cha_Ema + '\'' +
                ", ZJ_PROPERTY='" + ZJ_PROPERTY + '\'' +
                ", ZJ_LEAD_NAME='" + ZJ_LEAD_NAME + '\'' +
                '}';
    }
}
