package com.project.model;

public class zj_Report_Public {

    //支局缩写
    public  String Zj_Full_Name;
    //支局全程
    public  String Zj_Abbr_Name;
    //支局长名称
    public  String Zj_Person_In_Charge;
    //支局长邮箱
    public  String Zj_Person_In_Charge_Email;

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

    public String getZj_Person_In_Charge() {
        return Zj_Person_In_Charge;
    }

    public void setZj_Person_In_Charge(String zj_Person_In_Charge) {
        Zj_Person_In_Charge = zj_Person_In_Charge;
    }

    public String getZj_Person_In_Charge_Email() {
        return Zj_Person_In_Charge_Email;
    }

    public void setZj_Person_In_Charge_Email(String zj_Person_In_Charge_Email) {
        Zj_Person_In_Charge_Email = zj_Person_In_Charge_Email;
    }

    @Override
    public String toString() {
        return "zj_Report_Public{" +
                "Zj_Full_Name='" + Zj_Full_Name + '\'' +
                ", Zj_Abbr_Name='" + Zj_Abbr_Name + '\'' +
                ", Zj_Person_In_Charge='" + Zj_Person_In_Charge + '\'' +
                ", Zj_Person_In_Charge_Email='" + Zj_Person_In_Charge_Email + '\'' +
                '}';
    }
}
