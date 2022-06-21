package com.project.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class test20220606 {

    public  void testnumber(int n) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.print("1~100以内的质数有：");
        for (int i=2;i<=n;i++){
            boolean flagtest=true;
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flagtest=false;
                    break;
                }
            }
            if(flagtest){
                System.out.println(i+"");
            }
        }

    }

    public void exchange(int n, char a, char b, char c){
        if(n == 1){
            System.out.println("The " + n + " from " + a + " to " + c);
        }else{
            exchange(n-1, a, c, b);
            System.out.println("The " + n + " from " + a + " to " + c);
            exchange(n-1, b, a, c);
        }
    }
    public void testshuixianhua(){

        for(int l=100;l<=999;l++){
            int i=l/100;
            int j=l/10%10;
            int k=l%10;
            if(l == i * i * i + j * j * j + k * k * k){
                System.out.println(l);
            }
        }
    }

    public static String printMaxChild(String str1,String str2){
        int flag = 0;
        String MaxStr = str1.length() > str2.length() ? str1 : str2;
        String MinStr = str1.length() > str2.length() ? str2 : str1;

        int minStrLen = MinStr.length();
        String[] strs = new String[minStrLen];
        for(int i = 0; i < minStrLen; i++){
            for(int x = 0, y = minStrLen - i;  y <= minStrLen;  x++, y++){

                String subStr = MinStr.substring(x,y);

                if(MaxStr.contains(subStr)){
                    return subStr;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        String str1 = "qpalzmwoskxn";
        String str2 = "alzmqwpo";

        int flag = 0;
        String MaxStr = str1.length() > str2.length() ? str1 : str2;
        String MinStr = str1.length() > str2.length() ? str2 : str1;

        int minStrLen = MinStr.length();
        String strs = new String();
        for(int i = 0; i < minStrLen; i++){
            for(int x = 0, y = minStrLen - i;  y <= minStrLen;  x++, y++){

                String subStr = MinStr.substring(x,y);

                if(MaxStr.contains(subStr)){
                    System.out.println(subStr);
                    return;
                }
            }
        }

    }
}
