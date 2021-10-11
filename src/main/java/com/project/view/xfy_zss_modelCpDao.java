package com.project.view;



import com.project.model.xfy_zss_modelCp;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface xfy_zss_modelCpDao {

     List<xfy_zss_modelCp> selectXfy_zss_modelCp (@Param("start") Date start , @Param("end")Date end);


}
