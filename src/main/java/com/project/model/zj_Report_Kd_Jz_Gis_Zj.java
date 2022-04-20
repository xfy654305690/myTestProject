package com.project.model;

public class zj_Report_Kd_Jz_Gis_Zj {
    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //GIS数量
    public  Integer  GIS_NUM;
    //负净增数量
    public  Integer  FJZ_NUM;
    //负净增率
    public  Double  FJZ_RATE;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getGIS_NUM() {
        return GIS_NUM;
    }

    public void setGIS_NUM(Integer GIS_NUM) {
        this.GIS_NUM = GIS_NUM;
    }

    public Integer getFJZ_NUM() {
        return FJZ_NUM;
    }

    public void setFJZ_NUM(Integer FJZ_NUM) {
        this.FJZ_NUM = FJZ_NUM;
    }

    public Double getFJZ_RATE() {
        return FJZ_RATE;
    }

    public void setFJZ_RATE(Double FJZ_RATE) {
        this.FJZ_RATE = FJZ_RATE;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Jz_Gis_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", GIS_NUM=" + GIS_NUM +
                ", FJZ_NUM=" + FJZ_NUM +
                ", FJZ_RATE=" + FJZ_RATE +
                '}';
    }
}
