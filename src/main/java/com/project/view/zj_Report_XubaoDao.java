package com.project.view;


import com.project.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface zj_Report_XubaoDao {

    public List<zj_Report_Xubao_Zj> selectZj_Report_Xubao_Zj (@Param("endDate") Date endDate , @Param("nowMonth")String nowMonth);

    public List<zj_Report_Xubao_Xf> selectZj_Report_Xubao_Xf (@Param("endDate") Date endDate , @Param("nowMonth")String nowMonth);

    public List<zj_Report_Xubao_Tx> selectZj_Report_Xubao_Tx (@Param("endDate") Date endDate , @Param("nowMonth")String nowMonth);

    public List<zj_Report_Xubao_Data> selectZj_Report_Xubao_Data ( @Param("nowMonth")String nowMonth,@Param("zj_name")String zj_name);

    public String selectZj_Report_Xubao_MaxTime ();


}
