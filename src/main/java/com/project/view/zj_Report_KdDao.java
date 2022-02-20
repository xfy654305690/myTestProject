package com.project.view;


import com.project.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_KdDao {

    public List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_New_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_Zj (@Param("tableName") String tableName);

    public List<zj_Report_Kd_Jz_Data> selectZj_Report_Kd_Jz_Data (@Param("tableName") String tableName,@Param("zjName") String zjName);



}
