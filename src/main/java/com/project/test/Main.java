package com.project.test;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<Integer,Integer> map=new HashMap<>();
        List<Integer>list=new ArrayList<>();

        Scanner s=new Scanner(System.in);
        String ss=s.next();
        Scanner s1=new Scanner(ss);
        while(s1.hasNextInt()){
            list.add(s1.nextInt());
        }
        for(int i=0;i<list.size();i++){
            if(map.get(list.get(i))==null){
                map.put(list.get(i),1);
            }else{
                map.put(list.get(i),map.get(list.get(i)+1));
            }
        }
        List<Map.Entry<Integer,Integer>> entrys=new ArrayList<>(map.entrySet());
        Collections.sort(entrys,new Comparator<Map.Entry<Integer,Integer>>(){
            @Override
            public int compare(Map.Entry<Integer,Integer> o1,Map.Entry<Integer,Integer> o2){
                return o2.getValue()-o1.getValue();
            }
        });


    }
}


