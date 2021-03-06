package com.project.view;


import com.project.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_KdDao {

    public List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_New_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_New_Js_Month_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_Js_Month_Zj (@Param("tableNameOld") String tableNameOld,@Param("tableNameNew") String tableNameNew);

    public List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_Zj (@Param("tableName") String tableName);

    public List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_Year_Zj ();

    public List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_Year_Zj_Js (@Param("tableNameNew") String tableNameNew);

    public List<zj_Report_Kd_Jz_Zj> selectZj_Report_Kd_Jz_Zj_Js (@Param("tableNameOld") String tableNameOld,@Param("tableNameNew") String tableNameNew);

    public List<zj_Report_Kd_Jz_Data> selectZj_Report_Kd_Jz_Data (@Param("tableName") String tableName,@Param("zjName") String zjName);

    public List<zj_Report_Kd_New_Zj> selectZj_Report_Kd_Jz_Zj_New_Hy (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Zt_Data>selectZj_Report_Kd_Jz_Data_New_Hy(@Param("startDate") Date startDate , @Param("endDate")Date endDate,@Param("zj_name")String zj_name);

    public List<zj_Report_Kd_Jz_Gis> selectZj_Report_Kd_Jz_Gis (@Param("tableName") String tableName);

    public List<zj_Report_Kd_Jz_Gis_Zj> selectZj_Report_Kd_Jz_Gis_Zj (@Param("tableName") String tableName);

    public String selectZj_Report_Kd_Jz_MaxTime ();

}
