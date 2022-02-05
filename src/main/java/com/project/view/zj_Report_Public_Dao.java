package com.project.view;


import com.project.model.zj_Report_Public;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface zj_Report_Public_Dao {

    public List<zj_Report_Public> selectZj_Report_Public ();

}
