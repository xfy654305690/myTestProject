package com.project.view;



import com.project.model.zj_Report_Znzw_Kd_Zj;
import com.project.model.zj_Report_Znzw_New_Zj;
import com.project.model.zj_Report_Znzw_Rh_Zj;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface zj_Report_Znzw_Dao {

    public List<zj_Report_Znzw_New_Zj> selectZj_Report_Znzw_New_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Znzw_Rh_Zj> selectZj_Report_Znzw_Rh_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Znzw_Kd_Zj> selectZj_Report_Znzw_Kd_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public String selectZj_Report_Znzw_Kd_MaxTime();


}
