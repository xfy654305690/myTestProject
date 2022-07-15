package com.project.model;

public class zj_Report_Zcy_Zss_Zj {

    public  String dcld;
    //支局全程 Zj_Abbr_Name
    public  String zj;
    //移动日均指标
    public  Double ydrj;
    //宽带日均指标
    public  Double  kdrj;
    //高套日均指标
    public  Integer  gtrj;
    //终端日均指标
    public  Double  zdrj;
    //移动完成
    public  Integer  ydsj;
    //宽带完成
    public  Double  kdsj;
    //高套完成
    public  Double  gtsj;
    //终端完成
    public  Double  zdsj;
    //得分
    public  Double  zjdf;

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public Double getYdrj() {
        return ydrj;
    }

    public void setYdrj(Double ydrj) {
        this.ydrj = ydrj;
    }

    public Double getKdrj() {
        return kdrj;
    }

    public void setKdrj(Double kdrj) {
        this.kdrj = kdrj;
    }

    public Integer getGtrj() {
        return gtrj;
    }

    public void setGtrj(Integer gtrj) {
        this.gtrj = gtrj;
    }

    public Double getZdrj() {
        return zdrj;
    }

    public void setZdrj(Double zdrj) {
        this.zdrj = zdrj;
    }

    public Integer getYdsj() {
        return ydsj;
    }

    public void setYdsj(Integer ydsj) {
        this.ydsj = ydsj;
    }

    public Double getKdsj() {
        return kdsj;
    }

    public void setKdsj(Double kdsj) {
        this.kdsj = kdsj;
    }

    public Double getGtsj() {
        return gtsj;
    }

    public void setGtsj(Double gtsj) {
        this.gtsj = gtsj;
    }

    public Double getZdsj() {
        return zdsj;
    }

    public void setZdsj(Double zdsj) {
        this.zdsj = zdsj;
    }

    public Double getZjdf() {
        return zjdf;
    }

    public void setZjdf(Double zjdf) {
        this.zjdf = zjdf;
    }

    public String getDcld() {
        return dcld;
    }

    public void setDcld(String dcld) {
        this.dcld = dcld;
    }

    @Override
    public String toString() {
        return "zj_Report_Zcy_Zss_Zj{" +
                "dcld='" + dcld + '\'' +
                ", zj='" + zj + '\'' +
                ", ydrj=" + ydrj +
                ", kdrj=" + kdrj +
                ", gtrj=" + gtrj +
                ", zdrj=" + zdrj +
                ", ydsj=" + ydsj +
                ", kdsj=" + kdsj +
                ", gtsj=" + gtsj +
                ", zdsj=" + zdsj +
                ", zjdf=" + zjdf +
                '}';
    }
}
