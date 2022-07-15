package com.project.test;

import java.util.*;


public class test20220708 {

    public static void main(final String[] args) throws Exception {
        Scanner scanner  = new Scanner(System.in);
        String name = scanner.next();
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "Amy");
        map.put(2, "Joe");
        map.put(3, "Tom");
        map.put(4, "Susan");

        for(int i:map.keySet()) {
            System.out.println(i + ":" + map.get(i));
        }
        System.out.println();
        for(int i=0;i<map.keySet().size();i++){
            System.out.println(map.keySet()+ ":" + map.get(i));

        }
        map.put(5,name); // 添加
        map.remove(4);  // 删除
        map.replace(3,"Tommy"); // 修改

        for(int i : map.keySet()) {
            System.out.println(i + ":" + map.get(i));
        }



    }

}
