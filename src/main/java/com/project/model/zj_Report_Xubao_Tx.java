package com.project.model;

import java.math.BigDecimal;

public class zj_Report_Xubao_Tx {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //到期宽带数 broadband_Amount
    public  Integer bb_Amt;
    //已经续包宽带数 broadband_Amount_Compelete
    public  Integer bb_Amt_Com;
    //未续包宽带数 broadband_Amount_Uncompelete
    public  Integer bb_Amt_Uncom;
    //续包率 broadband_Completion_Rate
    public  Double bb_Com_Rate;
    //到期宽带收入 broadband_Income
    public  Double bb_Income;
    //已经续包宽带收入 broadband_Compelete_Income
    public  Double bb_Com_Income;
    //未续包宽带数收入 broadband_Amount_Uncompelete_Income
    public  Double bb_Amt_Uncom_Income;
    //收入完成率 broadband_Completion_Rate_Income
    public  Double bb_Com_Rate_Income;
    //缺口  broadband_Completion_Rate_gap
    public  Integer bb_Amt_gap;
    //预计奖扣  reward
    public  Integer reward;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getBb_Amt() {
        return bb_Amt;
    }

    public void setBb_Amt(Integer bb_Amt) {
        this.bb_Amt = bb_Amt;
    }

    public Integer getBb_Amt_Com() {
        return bb_Amt_Com;
    }

    public void setBb_Amt_Com(Integer bb_Amt_Com) {
        this.bb_Amt_Com = bb_Amt_Com;
    }

    public Integer getBb_Amt_Uncom() {
        return bb_Amt_Uncom;
    }

    public void setBb_Amt_Uncom(Integer bb_Amt_Uncom) {
        this.bb_Amt_Uncom = bb_Amt_Uncom;
    }

    public Double getBb_Com_Rate() {
        return bb_Com_Rate;
    }

    public void setBb_Com_Rate(Double bb_Com_Rate) {
        this.bb_Com_Rate = bb_Com_Rate;
    }

    public Double getBb_Income() {
        return bb_Income;
    }

    public void setBb_Income(Double bb_Income) {
        this.bb_Income = bb_Income;
    }

    public Double getBb_Com_Income() {
        return bb_Com_Income;
    }

    public void setBb_Com_Income(Double bb_Com_Income) {
        this.bb_Com_Income = bb_Com_Income;
    }

    public Double getBb_Amt_Uncom_Income() {
        return bb_Amt_Uncom_Income;
    }

    public void setBb_Amt_Uncom_Income(Double bb_Amt_Uncom_Income) {
        this.bb_Amt_Uncom_Income = bb_Amt_Uncom_Income;
    }

    public Double getBb_Com_Rate_Income() {
        return bb_Com_Rate_Income;
    }

    public void setBb_Com_Rate_Income(Double bb_Com_Rate_Income) {
        this.bb_Com_Rate_Income = bb_Com_Rate_Income;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getBb_Amt_gap() {
        return bb_Amt_gap;
    }

    public void setBb_Amt_gap(Integer bb_Amt_gap) {
        this.bb_Amt_gap = bb_Amt_gap;
    }

    @Override
    public String toString() {
        return "zj_Report_Xubao_Tx{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_Amt=" + bb_Amt +
                ", bb_Amt_Com=" + bb_Amt_Com +
                ", bb_Amt_Uncom=" + bb_Amt_Uncom +
                ", bb_Com_Rate=" + bb_Com_Rate +
                ", bb_Income=" + bb_Income +
                ", bb_Com_Income=" + bb_Com_Income +
                ", bb_Amt_Uncom_Income=" + bb_Amt_Uncom_Income +
                ", bb_Com_Rate_Income=" + bb_Com_Rate_Income +
                ", reward=" + reward +
                '}';
    }
}
