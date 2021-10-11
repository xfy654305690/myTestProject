package com.project.view;

import com.project.model.student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface studentDao {

     List<student> selectStduent (@Param("start")Date start ,@Param("end")Date end);

}
