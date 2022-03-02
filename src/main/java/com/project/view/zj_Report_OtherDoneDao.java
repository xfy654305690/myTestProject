package com.project.view;

import com.project.model.zj_Report_OtherDone_Cdma_Data;
import com.project.model.zj_Report_OtherDone_Gt_Data;
import com.project.model.zj_Report_OtherDone_Kd_Data;
import com.project.model.zj_Report_OtherDone_Zd_Data;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_OtherDoneDao {

    public List<zj_Report_OtherDone_Cdma_Data> selectZj_Report_OtherDone_Cdma_Data (@Param("endDate")Date endDate , @Param("zj_name")String zj_name);

    public List<zj_Report_OtherDone_Kd_Data> selectZj_Report_OtherDone_Kd_Data (@Param("endDate")Date endDate , @Param("zj_name")String zj_name);

    public List<zj_Report_OtherDone_Gt_Data> selectZj_Report_OtherDone_Gt_Data (@Param("endDate")Date endDate , @Param("zj_name")String zj_name);

    public List<zj_Report_OtherDone_Zd_Data> selectZj_Report_OtherDone_Zd_Data (@Param("endDate")Date endDate , @Param("zj_name")String zj_name);

}
