package com.project.view;


import com.project.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_ZtDao {

    public List<zj_Report_Zt_Kd_Zj> selectZj_Report_Zt_Kd_Zj (@Param("lastStartDate") Date lastStartDate , @Param("lastEndDate")Date lastEndDate, @Param("nowStartDate")Date nowStartDate,@Param("nowEndDate")Date nowEndDate);

    public List<zj_Report_Zt_Cdma_Zj> selectZj_Report_Zt_Cmda_Zj (@Param("lastStartDate") Date lastStartDate , @Param("lastEndDate")Date lastEndDate, @Param("nowStartDate")Date nowStartDate,@Param("nowEndDate")Date nowEndDate);

    public List<zj_Report_Zt_Itv_Zj> selectZj_Report_Zt_Itv_Zj (@Param("lastStartDate") Date lastStartDate , @Param("lastEndDate")Date lastEndDate, @Param("nowStartDate")Date nowStartDate,@Param("nowEndDate")Date nowEndDate);

    public List<zj_Report_Zt_Data>selectZj_Report_Zt_Data(@Param("startDate") Date startDate , @Param("endDate")Date endDate,@Param("zj_name")String zj_name);



}
