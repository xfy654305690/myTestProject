package com.project.model;

public class zj_Report_OtherDone_Gt_Data {

    public String branch_area_name;

    public String order_id;

    public String accs_nbr_zk;

    public String cpl_dt;

    public String prom_name;

    public String mkt_employee_name;

    public String sales_dept_name;

    public String getBranch_area_name() {
        return branch_area_name;
    }

    public void setBranch_area_name(String branch_area_name) {
        this.branch_area_name = branch_area_name;
    }

    public String getProm_name() {
        return prom_name;
    }

    public void setProm_name(String prom_name) {
        this.prom_name = prom_name;
    }

    public String getMkt_employee_name() {
        return mkt_employee_name;
    }

    public void setMkt_employee_name(String mkt_employee_name) {
        this.mkt_employee_name = mkt_employee_name;
    }

    public String getSales_dept_name() {
        return sales_dept_name;
    }

    public void setSales_dept_name(String sales_dept_name) {
        this.sales_dept_name = sales_dept_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAccs_nbr_zk() {
        return accs_nbr_zk;
    }

    public void setAccs_nbr_zk(String accs_nbr_zk) {
        this.accs_nbr_zk = accs_nbr_zk;
    }

    public String getCpl_dt() {
        return cpl_dt;
    }

    public void setCpl_dt(String cpl_dt) {
        this.cpl_dt = cpl_dt;
    }

    @Override
    public String toString() {
        return "zj_Report_OtherDone_Gt_Data{" +
                "branch_area_name='" + branch_area_name + '\'' +
                ", order_id='" + order_id + '\'' +
                ", accs_nbr_zk='" + accs_nbr_zk + '\'' +
                ", cpl_dt='" + cpl_dt + '\'' +
                ", prom_name='" + prom_name + '\'' +
                ", mkt_employee_name='" + mkt_employee_name + '\'' +
                ", sales_dept_name='" + sales_dept_name + '\'' +
                '}';
    }
}
