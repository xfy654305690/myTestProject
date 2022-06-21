package com.project.test;

import java.util.*;

public class test0 {
    public static void main(String[] args)  {

       Scanner scanner=new Scanner(System.in);
       int n=scanner.nextInt();
       int i=0;
       deal(n,i);


    }
    public static  void deal(int n,int i){
        if(n%2==0){
            System.out.println(n+",");
            n=n/2;
            deal(n,i);
        }else{
            System.out.println(n+",");
            if(n==1){
                i=i+1;
                if(i==2){
                    return;
                }
            }

                n=3*n+1;
                deal(n,i);

        }
    }
}
