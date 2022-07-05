package com.project.view;


import com.project.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_Kd_QzDao {

    public List<zj_Report_Kd_Qz_Jz_Zj> selectZj_Report_Kd_Qz_Jz_Zj (@Param("tableName") String tableName);

    public List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_Zj (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Qz_New_Zj> selectZj_Report_Kd_Qz_New_Tx (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Qz_Cl_Zj> selectZj_Report_Kd_Qz_Cl_Tx (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Qz_Rh_Zj> selectZj_Report_Kd_Qz_Rh_Tx (@Param("startDate") Date startDate , @Param("endDate")Date endDate);

    public List<zj_Report_Kd_Qz_Temp_Gis> selectZj_Report_Kd_Qz_Temp_Gis ();

    public String selectZj_Report_Kd_Qz_Gis_TEMP_MaxTime ();

    public String selectZj_Report_Kd_Qz_MaxTime ();

    public String selectZj_Report_Kd_Qz_Cl_MaxTime();

    public String selectZj_Report_Kd_Qz_Gis_XGPON_TEMP_MaxTime ();

    public List<zj_Report_Kd_Qz_Jz_Zj> selectZj_Report_Kd_Qz_Jz_Zj_JS (@Param("tableNameOld") String tableNameOld,@Param("tableNameNew") String tableNameNew);

}
