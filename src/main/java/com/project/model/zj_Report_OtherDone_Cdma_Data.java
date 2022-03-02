package com.project.model;

public class zj_Report_OtherDone_Cdma_Data {

    public String branch_area_name;

    public String accs_nbr;

    public String cpl_dt;

    public String mkt_employee_name;

    public String mkt_dept_name;

    public String getBranch_area_name() {
        return branch_area_name;
    }

    public void setBranch_area_name(String branch_area_name) {
        this.branch_area_name = branch_area_name;
    }

    public String getAccs_nbr() {
        return accs_nbr;
    }

    public void setAccs_nbr(String accs_nbr) {
        this.accs_nbr = accs_nbr;
    }

    public String getCpl_dt() {
        return cpl_dt;
    }

    public void setCpl_dt(String cpl_dt) {
        this.cpl_dt = cpl_dt;
    }

    public String getMkt_employee_name() {
        return mkt_employee_name;
    }

    public void setMkt_employee_name(String mkt_employee_name) {
        this.mkt_employee_name = mkt_employee_name;
    }

    public String getMkt_dept_name() {
        return mkt_dept_name;
    }

    public void setMkt_dept_name(String mkt_dept_name) {
        this.mkt_dept_name = mkt_dept_name;
    }

    @Override
    public String toString() {
        return "zj_Report_OtherDone_Cdma_Data{" +
                "branch_area_name='" + branch_area_name + '\'' +
                ", accs_nbr='" + accs_nbr + '\'' +
                ", cpl_dt='" + cpl_dt + '\'' +
                ", mkt_employee_name='" + mkt_employee_name + '\'' +
                ", mkt_dept_name='" + mkt_dept_name + '\'' +
                '}';
    }
}
