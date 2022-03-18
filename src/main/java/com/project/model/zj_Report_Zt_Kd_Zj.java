package com.project.model;

public class zj_Report_Zt_Kd_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //上月宽带数量
    public  Double bb_Last_Amt;
    //上月新增宽带活跃数量
    public  Double bb_Las_amt_act;
    //上月新增宽带不活跃数量
    public  Double bb_Las_amt_unact;
    //上月新增宽带活跃
    public  Double bb_Las_Amt_act_rate;
    //当月新增宽带数量
    public  Double bb_Now_Amt;
    //当月新增宽带活跃数量
    public  Double bb_Now_amt_act;
    //当月新增宽带不活跃数量
    public  Double bb_Now_amt_unact;
    //当月新增宽带活跃
    public  Double bb_Now_Amt_act_rate;
    //上月新增宽带奖励
    public  Double bb_Last_Reward;
    //当月新增宽带分数
    public  Double bb_Now_Grade;
    //当月新增宽带分数
    public  Double bb_Last_Grade;
    //两月新增宽带分数
    public  Double bb_Grade;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Double getBb_Last_Reward() {
        return bb_Last_Reward;
    }

    public void setBb_Last_Reward(Double bb_Last_Reward) {
        this.bb_Last_Reward = bb_Last_Reward;
    }

    public Double getBb_Now_Grade() {
        return bb_Now_Grade;
    }

    public void setBb_Now_Grade(Double bb_Now_Grade) {
        this.bb_Now_Grade = bb_Now_Grade;
    }

    public Double getBb_Last_Grade() {
        return bb_Last_Grade;
    }

    public void setBb_Last_Grade(Double bb_Last_Grade) {
        this.bb_Last_Grade = bb_Last_Grade;
    }

    public Double getBb_Last_Amt() {
        return bb_Last_Amt;
    }

    public void setBb_Last_Amt(Double bb_Last_Amt) {
        this.bb_Last_Amt = bb_Last_Amt;
    }

    public Double getBb_Las_amt_act() {
        return bb_Las_amt_act;
    }

    public void setBb_Las_amt_act(Double bb_Las_amt_act) {
        this.bb_Las_amt_act = bb_Las_amt_act;
    }

    public Double getBb_Las_amt_unact() {
        return bb_Las_amt_unact;
    }

    public void setBb_Las_amt_unact(Double bb_Las_amt_unact) {
        this.bb_Las_amt_unact = bb_Las_amt_unact;
    }

    public Double getBb_Las_Amt_act_rate() {
        return bb_Las_Amt_act_rate;
    }

    public void setBb_Las_Amt_act_rate(Double bb_Las_Amt_act_rate) {
        this.bb_Las_Amt_act_rate = bb_Las_Amt_act_rate;
    }

    public Double getBb_Now_Amt() {
        return bb_Now_Amt;
    }

    public void setBb_Now_Amt(Double bb_Now_Amt) {
        this.bb_Now_Amt = bb_Now_Amt;
    }

    public Double getBb_Now_amt_act() {
        return bb_Now_amt_act;
    }

    public void setBb_Now_amt_act(Double bb_Now_amt_act) {
        this.bb_Now_amt_act = bb_Now_amt_act;
    }

    public Double getBb_Now_amt_unact() {
        return bb_Now_amt_unact;
    }

    public void setBb_Now_amt_unact(Double bb_Now_amt_unact) {
        this.bb_Now_amt_unact = bb_Now_amt_unact;
    }

    public Double getBb_Now_Amt_act_rate() {
        return bb_Now_Amt_act_rate;
    }

    public void setBb_Now_Amt_act_rate(Double bb_Now_Amt_act_rate) {
        this.bb_Now_Amt_act_rate = bb_Now_Amt_act_rate;
    }

    public Double getBb_Grade() {
        return bb_Grade;
    }

    public void setBb_Grade(Double bb_Grade) {
        this.bb_Grade = bb_Grade;
    }

    @Override
    public String toString() {
        return "zj_Report_Zt_Kd_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", bb_Last_Amt=" + bb_Last_Amt +
                ", bb_Las_amt_act=" + bb_Las_amt_act +
                ", bb_Las_amt_unact=" + bb_Las_amt_unact +
                ", bb_Las_Amt_act_rate=" + bb_Las_Amt_act_rate +
                ", bb_Now_Amt=" + bb_Now_Amt +
                ", bb_Now_amt_act=" + bb_Now_amt_act +
                ", bb_Now_amt_unact=" + bb_Now_amt_unact +
                ", bb_Now_Amt_act_rate=" + bb_Now_Amt_act_rate +
                ", bb_Last_Reward=" + bb_Last_Reward +
                ", bb_Now_Grade=" + bb_Now_Grade +
                ", bb_Last_Grade=" + bb_Last_Grade +
                ", bb_Grade=" + bb_Grade +
                '}';
    }
}
