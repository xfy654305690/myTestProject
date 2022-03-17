package com.project.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testDoJobMain {

    public static void main(String[] args) {
        //初始化容器时，执行Myjob
        new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

}
