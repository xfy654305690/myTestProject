package com.project.test;

import java.util.Scanner;

public class test20220615 {

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int i=0;
        math(n,i);

    }
    public static void math(int n,int i){
        if(n%2==0){
            System.out.println(n);
            n=n/2;
            math(n,i);
        }else {
            System.out.println(n);
            if(n==1){i=i+1;}
            if(i==2){return;}
            n=n*3+1;
            math(n,i);
        }
    }


}
