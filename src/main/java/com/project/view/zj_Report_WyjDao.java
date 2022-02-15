package com.project.view;


import com.project.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_WyjDao {

    public List<zj_Report_Wyj_Zj> selectZj_Report_Wyj_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Wyj_Data> selectZj_Report_Wyj_Data (@Param("startDate") Date startDate , @Param("endDate")Date endDate, @Param("zj_name")String zj_name);

    public String selectZj_Report_Wyj_MaxTime ();

}
