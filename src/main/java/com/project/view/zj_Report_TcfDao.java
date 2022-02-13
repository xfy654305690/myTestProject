package com.project.view;


import com.project.model.zj_Report_Tcf_Itv_Data;
import com.project.model.zj_Report_Tcf_Kd_Data;
import com.project.model.zj_Report_Tcf_Zj;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_TcfDao {

    public List<zj_Report_Tcf_Zj> selectZj_Report_Tcf_Kd_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Tcf_Zj> selectZj_Report_Tcf_Itv_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Tcf_Kd_Data> selectZj_Report_Tcf_Kd_Data (@Param("startDate") Date startDate , @Param("endDate")Date endDate, @Param("zj_name")String zj_name);

    public List<zj_Report_Tcf_Itv_Data> selectZj_Report_Tcf_Itv_Data (@Param("startDate") Date startDate , @Param("endDate")Date endDate, @Param("zj_name")String zj_name);

}
