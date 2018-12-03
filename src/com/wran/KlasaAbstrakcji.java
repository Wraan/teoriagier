package com.wran;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KlasaAbstrakcji {
    int numerGracza;
    List<Integer> numeryStrategii = new ArrayList();
    Set<Stan> stany;

    public KlasaAbstrakcji() {
        numeryStrategii = new ArrayList<>();
    }

    public KlasaAbstrakcji(int numerGracza, Set<Stan> stany,int str1, int str2) {
        this.numerGracza = numerGracza;
        this.stany = stany;
        this.numeryStrategii.add(str1);
        this.numeryStrategii.add(str2);
    }

    public KlasaAbstrakcji(int numerGracza, Set<Stan> stany,int str1) {
        this.numerGracza = numerGracza;
        this.stany = stany;
        this.numeryStrategii.add(str1);
    }

    public int getNumerGracza() {
        return numerGracza;
    }

    public void setNumerGracza(int numerGracza) {
        this.numerGracza = numerGracza;
    }

    public List<Integer> getNumeryStrategii() {
        return numeryStrategii;
    }

    public void setNumeryStrategii(List<Integer> numeryStrategii) {
        this.numeryStrategii = numeryStrategii;
    }

    public Set<Stan> getStany() {
        return stany;
    }

    public void setStany(Set<Stan> stany) {
        this.stany = stany;
    }

    @Override
    public String toString() {
        String nrGracza = Integer.toString(numerGracza);
        String numeryStrat="";
        String stanString="";
        for (int i: numeryStrategii) {
            numeryStrat += Integer.toString(i) + ",";
        }
        for (Stan s: stany) {
            stanString += s.toString();
        }

        return "Gracz: "+ nrGracza + " Strategie: " + numeryStrat+ " Stany: " + stanString;
    }
}
