package com.project.view;




import com.project.model.zj_Report_Zss_Zj;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_ZssDao {

    public List<zj_Report_Zss_Zj> selectZj_Report_Zss_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Zss_Zj> selectZj_Report_Zss_Tx (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Zss_Zj> selectZj_Report_Zss_Leader (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public Date selectZj_Report_Zss_MaxTime ();

}
