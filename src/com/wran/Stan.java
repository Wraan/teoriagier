package com.wran;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stan {

    private int strG1;
    private int strG2;
    private int strG3;

    private double wyplG1;
    private double wyplG2;
    private double wyplG3;

    private String nazwaKlasy1 ="";
    private String nazwaKlasy2 ="";
    private String nazwaKlasy3 ="";

    public Stan(int strG1, int strG2, int strG3, double wyplG1, double wyplG2, double wyplG3) {
        this.strG1 = strG1;
        this.strG2 = strG2;
        this.strG3 = strG3;
        this.wyplG1 = wyplG1;
        this.wyplG2 = wyplG2;
        this.wyplG3 = wyplG3;
    }
    public Stan(){}


    public int getStrG1() {
        return strG1;
    }
    public void setStrG1(int strG1) {
        this.strG1 = strG1;
    }
    public int getStrG2() {
        return strG2;
    }
    public void setStrG2(int strG2) {
        this.strG2 = strG2;
    }
    public int getStrG3() {
        return strG3;
    }
    public void setStrG3(int strG3) {
        this.strG3 = strG3;
    }
    public double getWyplG1() {
        return wyplG1;
    }
    public void setWyplG1(double wyplG1) {
        this.wyplG1 = wyplG1;
    }
    public double getWyplG2() {
        return wyplG2;
    }
    public void setWyplG2(double wyplG2) {
        this.wyplG2 = wyplG2;
    }
    public double getWyplG3() {
        return wyplG3;
    }
    public void setWyplG3(double wyplG3) {
        this.wyplG3 = wyplG3;
    }
    public String getNazwaKlasy1() {
        return nazwaKlasy1;
    }
    public void setNazwaKlasy1(String nazwaKlasy1) {
        this.nazwaKlasy1 = nazwaKlasy1;
    }
    public String getNazwaKlasy2() {
        return nazwaKlasy2;
    }
    public void setNazwaKlasy2(String nazwaKlasy2) {
        this.nazwaKlasy2 = nazwaKlasy2;
    }
    public String getNazwaKlasy3() {
        return nazwaKlasy3;
    }
    public void setNazwaKlasy3(String nazwaKlasy3) {
        this.nazwaKlasy3 = nazwaKlasy3;
    }
    public double getWyplata(int i){
        if(i == 1)
            return wyplG1;
        else if(i == 2)
            return wyplG2;
        else
            return  wyplG3;
    }

    @Override
    public String toString() {
        return "{" + strG1 + ", " + strG2 + ", " + strG3 + "} -> {" + wyplG1 + ", " + wyplG2 + ", " + wyplG3 + "}";
    }
    public boolean equals(Stan obj) {
        if(strG1 == obj.strG1 && strG2 == obj.strG2 && strG3 == obj.strG3
                &&  wyplG1 == obj.wyplG1 && wyplG1 == obj.wyplG1 && wyplG1 == obj.wyplG1)
            return true;
        return false;
    }
    public static boolean maTakieSameWyplaty(Stan s1, Stan s2){
        if(s1.getWyplG1() == s2.getWyplG1() && s1.getWyplG2() == s2.getWyplG2() && s1.getWyplG3() == s2.getWyplG3())
            return true;
        else
            return false;
    }
    public static Set<Stan> znajdzStany(Set<Stan> stany, int strG1, int strG2, int strG3){
        Set<Stan> output = new HashSet<>();
        for(Stan s : stany){
            if(s.getStrG1() == strG1 && s.getStrG2() == strG2 && s.getStrG3() == strG3)
                output.add(s);
        }
        return output;
    }
    public static Stan znajdzStan(Set<Stan> stany, int strG1, int strG2, int strG3){
        for(Stan s : stany){
            if(s.getStrG1() == strG1 && s.getStrG2() == strG2 && s.getStrG3() == strG3)
                return s;
        }
        return null;
    }

    public String drukujStanyKlasAbstrakcji(){
        return "{" + nazwaKlasy1 + ", " + nazwaKlasy2 + ", " + nazwaKlasy3 + "} -> {" + wyplG1 + ", " + wyplG2 + ", " + wyplG3 + "}";
    }
    public String drukujWyplaty(){
        return "{" + wyplG1 + ", " + wyplG2 + ", " + wyplG3 + "}";
    }
}
