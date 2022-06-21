package com.project.test;

import java.math.BigDecimal;

public class Person {

    public String a1;
    public int a2;
    public int a3;
    public Double a4;

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

    public int getA3() {
        return a3;
    }

    public void setA3(int a3) {
        this.a3 = a3;
    }

    public Double getA4() {
        return a4;
    }

    public void setA4(Double a4) {
        this.a4 = a4;
    }

    @Override
    public String toString() {
        return  a1+" "+a2+" "+a3+" "+String.format("%.2f", a4);
    }
}
