package com.project.model;

public class zj_Report_Wyj_Zj {

    //支局全程 Zj_Abbr_Name
    public  String Zj_Name;
    //使用金额 Amt_Com
    public  Integer Amt_Com;
    //剩余可使用金额 Amt_UnCom
    public  Integer Amt_UnCom;
    //使用率 broadband_Completion_Rate
    public  Double Amt_Rate;
    //额度 Amt_Rate_Target
    public  Integer Amt_Rate_Target;
    //预计奖扣  reward
    public  Integer reward;

    public String getZj_Name() {
        return Zj_Name;
    }

    public void setZj_Name(String zj_Name) {
        Zj_Name = zj_Name;
    }

    public Integer getAmt_Com() {
        return Amt_Com;
    }

    public void setAmt_Com(Integer amt_Com) {
        Amt_Com = amt_Com;
    }

    public Integer getAmt_UnCom() {
        return Amt_UnCom;
    }

    public void setAmt_UnCom(Integer amt_UnCom) {
        Amt_UnCom = amt_UnCom;
    }

    public Double getAmt_Rate() {
        return Amt_Rate;
    }

    public void setAmt_Rate(Double amt_Rate) {
        Amt_Rate = amt_Rate;
    }

    public Integer getAmt_Rate_Target() {
        return Amt_Rate_Target;
    }

    public void setAmt_Rate_Target(Integer amt_Rate_Target) {
        Amt_Rate_Target = amt_Rate_Target;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "zj_Report_Wyj_Zj{" +
                "Zj_Name='" + Zj_Name + '\'' +
                ", Amt_Com=" + Amt_Com +
                ", Amt_UnCom=" + Amt_UnCom +
                ", Amt_Rate=" + Amt_Rate +
                ", Amt_Rate_Target=" + Amt_Rate_Target +
                ", reward=" + reward +
                '}';
    }
}
