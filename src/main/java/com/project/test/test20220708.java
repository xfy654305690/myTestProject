package com.project.test;

import java.util.*;


public class test20220708 {

    public static void main(final String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        List<String>list=new ArrayList<String>();
        while (scanner.hasNext()) {
            String string= scanner.nextLine();
            list.add(string);
            char[] c=string.toCharArray();
            for(int j=0;j<c.length;j++){
                System.out.println(c[j]);
            }
        }


    }
}

