package com.project.model;

public class zj_Report_Zss_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //C网新增
    public  Integer cdmaNew;
    //宽带新增
    public  Integer bbNew;
    //融合新增
    public  Integer rhNew;
    //高套新增
    public  Integer gtNew;
    //ITV新增
    public  Integer itvNew;
    //终端新增
    public  Integer zdNew;
    //C网指标
    public  Double cdry_Cdma_Target;
    //宽带指标
    public  Double cdry_Bb_Target;
    //高套指标
    public  Double cdry_Gt_Target;
    //融合指标
    public  Double cdry_Rh_Target;
    //终端指标
    public  Double cdry_Zd_Target;
    //C网新增日均完成率
    public  Double  cdma_Amt_Rate;
    //宽带新增日均完成率
    public  Double  bb_Amt_Rate;
    //融合新增日均完成率
    public  Double  rh_Amt_Rate;
    //融合新增日均完成率
    public  Double  gt_Amt_Rate;
    //终端日均完成率
    public  Double  zd_Amt_Rate;
    //综合完成率
    public  Double  com_Rate;
    //支局属性
    public  String  zj_Property;
    //综合排名
    public  Integer  com_Rank;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getCdmaNew() {
        return cdmaNew;
    }

    public void setCdmaNew(Integer cdmaNew) {
        this.cdmaNew = cdmaNew;
    }

    public Integer getBbNew() {
        return bbNew;
    }

    public void setBbNew(Integer bbNew) {
        this.bbNew = bbNew;
    }

    public Integer getRhNew() {
        return rhNew;
    }

    public void setRhNew(Integer rhNew) {
        this.rhNew = rhNew;
    }

    public Integer getGtNew() {
        return gtNew;
    }

    public void setGtNew(Integer gtNew) {
        this.gtNew = gtNew;
    }

    public Integer getItvNew() {
        return itvNew;
    }

    public void setItvNew(Integer itvNew) {
        this.itvNew = itvNew;
    }

    public Integer getZdNew() {
        return zdNew;
    }

    public void setZdNew(Integer zdNew) {
        this.zdNew = zdNew;
    }

    public Double getCdry_Cdma_Target() {
        return cdry_Cdma_Target;
    }

    public void setCdry_Cdma_Target(Double cdry_Cdma_Target) {
        this.cdry_Cdma_Target = cdry_Cdma_Target;
    }

    public Double getCdry_Bb_Target() {
        return cdry_Bb_Target;
    }

    public void setCdry_Bb_Target(Double cdry_Bb_Target) {
        this.cdry_Bb_Target = cdry_Bb_Target;
    }

    public Double getCdry_Gt_Target() {
        return cdry_Gt_Target;
    }

    public void setCdry_Gt_Target(Double cdry_Gt_Target) {
        this.cdry_Gt_Target = cdry_Gt_Target;
    }

    public Double getCdry_Rh_Target() {
        return cdry_Rh_Target;
    }

    public void setCdry_Rh_Target(Double cdry_Rh_Target) {
        this.cdry_Rh_Target = cdry_Rh_Target;
    }

    public Double getCdry_Zd_Target() {
        return cdry_Zd_Target;
    }

    public void setCdry_Zd_Target(Double cdry_Zd_Target) {
        this.cdry_Zd_Target = cdry_Zd_Target;
    }

    public Double getCdma_Amt_Rate() {
        return cdma_Amt_Rate;
    }

    public void setCdma_Amt_Rate(Double cdma_Amt_Rate) {
        this.cdma_Amt_Rate = cdma_Amt_Rate;
    }

    public Double getBb_Amt_Rate() {
        return bb_Amt_Rate;
    }

    public void setBb_Amt_Rate(Double bb_Amt_Rate) {
        this.bb_Amt_Rate = bb_Amt_Rate;
    }

    public Double getRh_Amt_Rate() {
        return rh_Amt_Rate;
    }

    public void setRh_Amt_Rate(Double rh_Amt_Rate) {
        this.rh_Amt_Rate = rh_Amt_Rate;
    }

    public Double getGt_Amt_Rate() {
        return gt_Amt_Rate;
    }

    public void setGt_Amt_Rate(Double gt_Amt_Rate) {
        this.gt_Amt_Rate = gt_Amt_Rate;
    }

    public Double getZd_Amt_Rate() {
        return zd_Amt_Rate;
    }

    public void setZd_Amt_Rate(Double zd_Amt_Rate) {
        this.zd_Amt_Rate = zd_Amt_Rate;
    }

    public Double getCom_Rate() {
        return com_Rate;
    }

    public void setCom_Rate(Double com_Rate) {
        this.com_Rate = com_Rate;
    }

    public Integer getCom_Rank() {
        return com_Rank;
    }

    public void setCom_Rank(Integer com_Rank) {
        this.com_Rank = com_Rank;
    }

    public String getZj_Property() {
        return zj_Property;
    }

    public void setZj_Property(String zj_Property) {
        this.zj_Property = zj_Property;
    }

    @Override
    public String toString() {
        return "zj_Report_Zss_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", cdmaNew=" + cdmaNew +
                ", bbNew=" + bbNew +
                ", rhNew=" + rhNew +
                ", gtNew=" + gtNew +
                ", itvNew=" + itvNew +
                ", zdNew=" + zdNew +
                ", cdry_Cdma_Target=" + cdry_Cdma_Target +
                ", cdry_Bb_Target=" + cdry_Bb_Target +
                ", cdry_Gt_Target=" + cdry_Gt_Target +
                ", cdry_Rh_Target=" + cdry_Rh_Target +
                ", cdry_Zd_Target=" + cdry_Zd_Target +
                ", cdma_Amt_Rate=" + cdma_Amt_Rate +
                ", bb_Amt_Rate=" + bb_Amt_Rate +
                ", rh_Amt_Rate=" + rh_Amt_Rate +
                ", gt_Amt_Rate=" + gt_Amt_Rate +
                ", zd_Amt_Rate=" + zd_Amt_Rate +
                ", com_Rate=" + com_Rate +
                ", zj_Property=" + zj_Property +
                ", com_Rank=" + com_Rank +
                '}';
    }
}
