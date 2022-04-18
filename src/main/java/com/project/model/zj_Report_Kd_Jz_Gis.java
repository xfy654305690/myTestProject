package com.project.model;

public class zj_Report_Kd_Jz_Gis {
    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //GIS_ID
    public  String GIS_ID;
    //GIS_ID
    public  String YXWGMC;
    //宽带现在到达
    public  Integer  nowNum;
    //宽带上季度到达
    public  Integer  lastNum;
    //宽带净增到达
    public  Integer  jzNum;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public String getGIS_ID() {
        return GIS_ID;
    }

    public void setGIS_ID(String GIS_ID) {
        this.GIS_ID = GIS_ID;
    }

    public String getYXWGMC() {
        return YXWGMC;
    }

    public void setYXWGMC(String YXWGMC) {
        this.YXWGMC = YXWGMC;
    }

    public Integer getNowNum() {
        return nowNum;
    }

    public void setNowNum(Integer nowNum) {
        this.nowNum = nowNum;
    }

    public Integer getLastNum() {
        return lastNum;
    }

    public void setLastNum(Integer lastNum) {
        this.lastNum = lastNum;
    }

    public Integer getJzNum() {
        return jzNum;
    }

    public void setJzNum(Integer jzNum) {
        this.jzNum = jzNum;
    }

    @Override
    public String toString() {
        return "zj_Report_Kd_Jz_Gis{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", GIS_ID='" + GIS_ID + '\'' +
                ", YXWGMC='" + YXWGMC + '\'' +
                ", nowNum=" + nowNum +
                ", lastNum=" + lastNum +
                ", jzNum=" + jzNum +
                '}';
    }
}
