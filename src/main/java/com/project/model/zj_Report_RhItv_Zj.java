package com.project.model;

public class zj_Report_RhItv_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //新增融合数量
    public  Integer rh_Amt;
    //其中融合添加ITV数量
    public  Integer rh_Add_Amt;
    //其中融合未添加ITV数量
    public  Integer rh_Unadd_Amt;
    //其中融合渗透率
    public  Integer rh_Add_rate;
    //奖扣
    public  Integer rh_Reword;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getRh_Amt() {
        return rh_Amt;
    }

    public void setRh_Amt(Integer rh_Amt) {
        this.rh_Amt = rh_Amt;
    }

    public Integer getRh_Add_Amt() {
        return rh_Add_Amt;
    }

    public void setRh_Add_Amt(Integer rh_Add_Amt) {
        this.rh_Add_Amt = rh_Add_Amt;
    }

    public Integer getRh_Unadd_Amt() {
        return rh_Unadd_Amt;
    }

    public void setRh_Unadd_Amt(Integer rh_Unadd_Amt) {
        this.rh_Unadd_Amt = rh_Unadd_Amt;
    }

    public Integer getRh_Add_rate() {
        return rh_Add_rate;
    }

    public void setRh_Add_rate(Integer rh_Add_rate) {
        this.rh_Add_rate = rh_Add_rate;
    }

    public Integer getRh_Reword() {
        return rh_Reword;
    }

    public void setRh_Reword(Integer rh_Reword) {
        this.rh_Reword = rh_Reword;
    }

    @Override
    public String toString() {
        return "zj_Report_RhItv_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", rh_Amt=" + rh_Amt +
                ", rh_Add_Amt=" + rh_Add_Amt +
                ", rh_Unadd_Amt=" + rh_Unadd_Amt +
                ", rh_Add_rate=" + rh_Add_rate +
                ", rh_Reword=" + rh_Reword +
                '}';
    }
}
