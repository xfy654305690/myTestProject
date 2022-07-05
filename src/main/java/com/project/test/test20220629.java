package com.project.test;

import com.project.controller.zj_Report_Kd_Qz_Controller;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;

public class test20220629 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int seed = scanner.nextInt();
            Random random = new Random(seed);
            System.out.println(random.nextInt(6)+1);
            System.out.println(random.nextInt(6) + 1); //随机生成1-6地数字


        }
    }

}
