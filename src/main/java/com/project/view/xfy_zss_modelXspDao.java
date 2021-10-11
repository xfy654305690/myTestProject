package com.project.view;




import com.project.model.xfy_zss_modelXsp;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface xfy_zss_modelXspDao {

    List<xfy_zss_modelXsp> selectXfy_zss_modelXsp(@Param("start") Date start, @Param("end") Date end);


}
