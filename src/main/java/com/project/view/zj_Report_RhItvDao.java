package com.project.view;


import com.project.model.zj_Report_RhItv_Data;
import com.project.model.zj_Report_RhItv_Zj;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface zj_Report_RhItvDao {

    public List<zj_Report_RhItv_Zj> select_zj_Report_RhItv_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_RhItv_Data> select_Zj_Report_RhItv_Data (@Param("startDate") Date startDate , @Param("endDate")Date endDate, @Param("zj_name")String zj_name);

    public List<zj_Report_RhItv_Zj> select_zj_Report_RhItv_Tx (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public String select_zj_Report_RhItv_Zj_MaxTime ();

}
