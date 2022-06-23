package com.project.model;

public class zj_Report_Znzw_New_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //智能组网数量
    public  Double znzw_Amt;
    //智能组网新增日均数量
    public  Double  znzw_Amt_Avg;
    //智能组网新增指标
    public  Integer  znzw_Amt_Tar;
    //智能组网新增日均指标
    public  Double  znzw_Amt_Avg_Tar;
    //智能组网新增缺口
    public  Integer  znzw_Amt_Gap;
    //智能组网新增日均缺口
    public  Double  znzw_Amt_Avg_Gap;
    //智能组网新增日均完成率
    public  Double  znzw_Amt_Rate;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Double getZnzw_Amt() {
        return znzw_Amt;
    }

    public void setZnzw_Amt(Double znzw_Amt) {
        this.znzw_Amt = znzw_Amt;
    }

    public Double getZnzw_Amt_Avg() {
        return znzw_Amt_Avg;
    }

    public void setZnzw_Amt_Avg(Double znzw_Amt_Avg) {
        this.znzw_Amt_Avg = znzw_Amt_Avg;
    }

    public Integer getZnzw_Amt_Tar() {
        return znzw_Amt_Tar;
    }

    public void setZnzw_Amt_Tar(Integer znzw_Amt_Tar) {
        this.znzw_Amt_Tar = znzw_Amt_Tar;
    }

    public Double getZnzw_Amt_Avg_Tar() {
        return znzw_Amt_Avg_Tar;
    }

    public void setZnzw_Amt_Avg_Tar(Double znzw_Amt_Avg_Tar) {
        this.znzw_Amt_Avg_Tar = znzw_Amt_Avg_Tar;
    }

    public Integer getZnzw_Amt_Gap() {
        return znzw_Amt_Gap;
    }

    public void setZnzw_Amt_Gap(Integer znzw_Amt_Gap) {
        this.znzw_Amt_Gap = znzw_Amt_Gap;
    }

    public Double getZnzw_Amt_Avg_Gap() {
        return znzw_Amt_Avg_Gap;
    }

    public void setZnzw_Amt_Avg_Gap(Double znzw_Amt_Avg_Gap) {
        this.znzw_Amt_Avg_Gap = znzw_Amt_Avg_Gap;
    }

    public Double getZnzw_Amt_Rate() {
        return znzw_Amt_Rate;
    }

    public void setZnzw_Amt_Rate(Double znzw_Amt_Rate) {
        this.znzw_Amt_Rate = znzw_Amt_Rate;
    }

    @Override
    public String toString() {
        return "zj_Report_Znzw_New_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", znzw_Amt=" + znzw_Amt +
                ", znzw_Amt_Avg=" + znzw_Amt_Avg +
                ", znzw_Amt_Tar=" + znzw_Amt_Tar +
                ", znzw_Amt_Avg_Tar=" + znzw_Amt_Avg_Tar +
                ", znzw_Amt_Gap=" + znzw_Amt_Gap +
                ", znzw_Amt_Avg_Gap=" + znzw_Amt_Avg_Gap +
                ", znzw_Amt_Rate=" + znzw_Amt_Rate +
                '}';
    }
}
