package com.project.business;

import com.project.model.zj_Report_Public;

import com.project.view.zj_Report_Public_Dao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

public class zj_Report_Public_Business {

    public static  final  String config="mybatis.xml";

    public static List<zj_Report_Public> zj_Report_Public_Business() throws IOException {

        InputStream in= Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession=factory.openSession();

        zj_Report_Public_Dao Zj_Report_Public_Dao = sqlSession.getMapper(zj_Report_Public_Dao.class);

        //公共数据
        List<zj_Report_Public> zj_Report_Public_List =
                Zj_Report_Public_Dao.selectZj_Report_Public();

        sqlSession.close();
        return zj_Report_Public_List;

    }


}
