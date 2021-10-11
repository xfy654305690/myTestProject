package com.project.test;

import sun.rmi.runtime.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class testTiming  {


    // 通过静态方法创建ScheduledExecutorService的实例
    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);


    public void onCreate() {

        // 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("一次");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

    }

    public static void main(String[] args){

        testTiming tes=new testTiming();
        tes.onCreate();

    }



}
