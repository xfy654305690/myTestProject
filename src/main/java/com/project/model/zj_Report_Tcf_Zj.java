package com.project.model;

public class zj_Report_Tcf_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //新增宽带数量
    public  Integer tcf_Amt;
    //其中调测费收取的数量
    public  Integer tcf_amt_cha;
    //其中调测费未收取的数量
    public  Integer tcf_amt_uncha;
    //其中调测费收取率
    public  Double tcf_amt_rate;
    //其中调测费缺口
    public  Double tcf_amt_gap;
    //其中调测费奖扣
    public  Double reward;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getTcf_Amt() {
        return tcf_Amt;
    }

    public void setTcf_Amt(Integer tcf_Amt) {
        this.tcf_Amt = tcf_Amt;
    }

    public Integer getTcf_amt_cha() {
        return tcf_amt_cha;
    }

    public void setTcf_amt_cha(Integer tcf_amt_cha) {
        this.tcf_amt_cha = tcf_amt_cha;
    }

    public Integer getTcf_amt_uncha() {
        return tcf_amt_uncha;
    }

    public void setTcf_amt_uncha(Integer tcf_amt_uncha) {
        this.tcf_amt_uncha = tcf_amt_uncha;
    }

    public Double getTcf_amt_rate() {
        return tcf_amt_rate;
    }

    public void setTcf_amt_rate(Double tcf_amt_rate) {
        this.tcf_amt_rate = tcf_amt_rate;
    }

    public Double getTcf_amt_gap() {
        return tcf_amt_gap;
    }

    public void setTcf_amt_gap(Double tcf_amt_gap) {
        this.tcf_amt_gap = tcf_amt_gap;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "zj_Report_Tcf_Kd_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", tcf_Amt=" + tcf_Amt +
                ", tcf_amt_cha=" + tcf_amt_cha +
                ", tcf_amt_uncha=" + tcf_amt_uncha +
                ", tcf_amt_rate=" + tcf_amt_rate +
                ", tcf_amt_gap=" + tcf_amt_gap +
                ", reward=" + reward +
                '}';
    }
}
