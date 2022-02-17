package com.project.model;

public class zj_Report_Kd_Jz_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //宽带净增数量
    public  Integer bb_Amt_Jz;
    //宽带净增日均数量
    public  Double  bb_Amt_Avg_Jz;
    //宽带净增指标
    public  Double  bb_Amt_Tar_Jz;
    //宽宽带净增日均指标
    public  Double  bb_Amt_Avg_Tar_Jz;
    //宽带净增缺口
    public  Integer  bb_Amt_Gap_Jz;
    //宽带净增日均缺口
    public  Double  bb_Amt_Avg_Gap_Jz;
    //宽带净增日均完成率
    public  Double  bb_Amt_Rate_Jz;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getBb_Amt_Jz() {
        return bb_Amt_Jz;
    }

    public void setBb_Amt_Jz(Integer bb_Amt_Jz) {
        this.bb_Amt_Jz = bb_Amt_Jz;
    }

    public Double getBb_Amt_Avg_Jz() {
        return bb_Amt_Avg_Jz;
    }

    public void setBb_Amt_Avg_Jz(Double bb_Amt_Avg_Jz) {
        this.bb_Amt_Avg_Jz = bb_Amt_Avg_Jz;
    }

    public Double getBb_Amt_Tar_Jz() {
        return bb_Amt_Tar_Jz;
    }

    public void setBb_Amt_Tar_Jz(Double bb_Amt_Tar_Jz) {
        this.bb_Amt_Tar_Jz = bb_Amt_Tar_Jz;
    }

    public Double getBb_Amt_Avg_Tar_Jz() {
        return bb_Amt_Avg_Tar_Jz;
    }

    public void setBb_Amt_Avg_Tar_Jz(Double bb_Amt_Avg_Tar_Jz) {
        this.bb_Amt_Avg_Tar_Jz = bb_Amt_Avg_Tar_Jz;
    }

    public Integer getBb_Amt_Gap_Jz() {
        return bb_Amt_Gap_Jz;
    }

    public void setBb_Amt_Gap_Jz(Integer bb_Amt_Gap_Jz) {
        this.bb_Amt_Gap_Jz = bb_Amt_Gap_Jz;
    }

    public Double getBb_Amt_Avg_Gap_Jz() {
        return bb_Amt_Avg_Gap_Jz;
    }

    public void setBb_Amt_Avg_Gap_Jz(Double bb_Amt_Avg_Gap_Jz) {
        this.bb_Amt_Avg_Gap_Jz = bb_Amt_Avg_Gap_Jz;
    }

    public Double getBb_Amt_Rate_Jz() {
        return bb_Amt_Rate_Jz;
    }

    public void setBb_Amt_Rate_Jz(Double bb_Amt_Rate_Jz) {
        this.bb_Amt_Rate_Jz = bb_Amt_Rate_Jz;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Jz_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_Amt_Jz=" + bb_Amt_Jz +
                ", bb_Amt_Avg_Jz=" + bb_Amt_Avg_Jz +
                ", bb_Amt_Tar_Jz=" + bb_Amt_Tar_Jz +
                ", bb_Amt_Avg_Tar_Jz=" + bb_Amt_Avg_Tar_Jz +
                ", bb_Amt_Gap_Jz=" + bb_Amt_Gap_Jz +
                ", bb_Amt_Avg_Gap_Jz=" + bb_Amt_Avg_Gap_Jz +
                ", bb_Amt_Rate_Jz=" + bb_Amt_Rate_Jz +
                '}';
    }
}
