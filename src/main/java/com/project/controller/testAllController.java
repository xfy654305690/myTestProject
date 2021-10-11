package com.project.controller;


import com.project.test.testAll;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class testAllController {



    // 通过静态方法创建ScheduledExecutorService的实例
    public static ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);


    public static void main(String[] args){

        // 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                System.out.println("一次");

                testAll testAll=new testAll();
                try {
                    testAll.excleDoDetail();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                try {
                    testAll.emailDoDetail();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(180000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

}
